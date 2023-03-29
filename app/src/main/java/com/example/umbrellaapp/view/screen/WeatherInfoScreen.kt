package com.example.umbrellaapp.view.screen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umbrella.presentation.component.getPrefectureList
import com.example.umbrella.presentation.component.getWeekList
import com.example.umbrellaapp.R
import com.example.umbrellaapp.common.Prefecture
import com.example.umbrellaapp.common.Week

@Composable
fun WeatherInfoScreen(
//    viewModel: WeatherInfoViewModel = hiltViewModel()
) {

    val checked = remember {
        mutableStateOf(true)
    }


    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            //メニュー
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "メニュー", fontSize = 20.sp)
            }

            Divider(color = Color.LightGray)

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
                    checked = checked.value,
                    modifier = Modifier.padding(end = 20.dp).scale(scale = 1.5f),
                    onCheckedChange = { checked.value = it }
                )
            }

            Divider(color = Color.LightGray)

            //都道府県
            val prefectures = Prefecture.values().toList()
            getPrefectureList(
                label = "都道府県",
                menuItems = prefectures,
            )

            Divider(color = Color.LightGray)

            //曜日→時間
            val week = Week.values().toList()
            getWeekList(
                label = "通知日時",
                menuItems = week,
                fixedOptionText = "通知日時"
            )

            Divider(color = Color.LightGray)

            //画像
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.umbrella),
                    contentDescription = "A umbrella image"
                )
            }

            Divider(color = Color.LightGray)

            //設定ボタン
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = { /* Do something */ },
                    modifier = Modifier.width(300.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(0xFF91BAFF),
                        contentColor = Color.Black,
                        disabledContentColor = Color.LightGray
                    )
                ) {
                    Text("更新")
                }
            }

        }

    }
}




//    val state = viewModel.state.value

//    Box(modifier = Modifier.fillMaxSize()){
//        when{
//            state.isLoading -> {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//            }
//            !state.error.isNullOrBlank() -> {
//                Text(
//                    text = state.error,
//                    modifier = Modifier.align(Alignment.Center),
//                    color = MaterialTheme.colors.error,
//                )
//            }
//            else -> {
//                state.weatherInfo?.let{ weatherInfo ->
//                    Text(state.weatherInfo.toString())
//
//                }
//            }
//        }
//    }
