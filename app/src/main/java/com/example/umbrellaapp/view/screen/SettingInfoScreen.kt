package com.example.umbrellaapp.view.screen

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.*
import com.example.umbrellaapp.MyWorker
import com.example.umbrellaapp.R
import com.example.umbrellaapp.common.Constants
import com.example.umbrellaapp.common.Prefecture
import com.example.umbrellaapp.common.Week
import com.example.umbrellaapp.view.components.CitiesDialog
import com.example.umbrellaapp.view.components.PrefectureList
import com.example.umbrellaapp.view.components.WeekList
import com.example.umbrellaapp.view.viewmodel.SettingInfoViewModel
import java.util.*
import java.util.concurrent.TimeUnit


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SettingInfoScreen(
    context: Context,
    viewModel: SettingInfoViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color.Gray.copy(alpha = 0.1f))
        )
        Column {
            //通知
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "通知設定",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = viewModel.notification,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .scale(scale = 1.5f),
                    onCheckedChange = {
                        viewModel.updateNotification(it)
                        if(it) {
                            val delay = 1000L
                            val workManager = WorkManager.getInstance()

                            val inputData = Data.Builder()
                                .putBoolean("firstTime",true)
                                .build()

                            val constraints = Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build()

                            val myWorkerRequest =
                                OneTimeWorkRequest.Builder(MyWorker::class.java)
                                    .setConstraints(constraints)
                                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                                    .setInputData(inputData)
                                    .build()

                            workManager.enqueue(myWorkerRequest)
                        }else{
                            Log.d("キャンセル","キャンセル")
                            WorkManager.getInstance(context).cancelAllWork()
                        }
                    }
                )
            }

            Divider(color = Color.LightGray)

            //都道府県
            val prefectures = Prefecture.values().toList()
            PrefectureList(
                label = "都道府県",
                menuItems = prefectures,
                viewModel = viewModel,
                locateType = Constants.LOCATE_TYPE_PREFECTURE
            )

            val cities = Prefecture.values()
                .filter { prefecture ->
                    prefecture.jp == viewModel.prefecture
                }.flatMap { prefecture ->
                    prefecture.cities
                }

            CitiesDialog(
                label = "市",
                menuItems = cities,
                viewModel = viewModel,
            )

            Divider(color = Color.LightGray)

            //曜日→時間
            val week = Week.values().toList()
            WeekList(
                label = "通知日時",
                menuItems = week,
                fixedOptionText = "通知日時",
                context = context,
                viewModel = viewModel
            )

            Divider(color = Color.LightGray)

            //画像
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.umbrella),
                    contentDescription = "A umbrella image"
                )
            }
        }
    }
}
