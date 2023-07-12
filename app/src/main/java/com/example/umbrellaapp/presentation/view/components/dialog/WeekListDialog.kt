package com.example.umbrellaapp.presentation.view.components.dialog

import TimeInput
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.umbrellaapp.common.Week
import com.example.umbrellaapp.presentation.viewmodel.SettingInfoViewModel
import java.util.*

data class ListItem2(val time: Int, var expanded: Boolean = false)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekList(
    label:String,
    menuItems :List<Week>,
    fixedOptionText:String,
    context:Context,
    viewModel: SettingInfoViewModel = hiltViewModel(),
){

    val expanded = remember { mutableStateOf(false) }
    var minuteExpanded = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .padding(end = 10.dp)
            .fillMaxWidth()
            .height(100.dp),
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .size(250.dp, 50.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(4.dp))
                .clickable { expanded.value = !expanded.value }
                .padding(end = 10.dp),
        ) {
            Text(
                text = fixedOptionText,
                modifier = Modifier.padding(start = 10.dp)
            )
            Icon(
                Icons.Filled.ArrowDropDown,
                "contentDescription",
                Modifier.align(Alignment.CenterEnd)
            )
        }
        }

        // ダイアログ
        if (expanded.value) {
            Dialog(
                onDismissRequest = {
                    expanded.value = false
                    minuteExpanded.value = false
                },
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    data class  TimeList(val weekOfDay:String,var hour:Int,var minute:Int)
                    var timeList = mutableListOf<TimeList>()
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(
                                color = White,
                            )
                            .border(
                                BorderStroke(1.dp, Color.LightGray),
                                RoundedCornerShape(4.dp)
                            )
                            .fillMaxWidth()
                            .height(1500.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        menuItems.forEach { selectionOption ->
                            var selectedTimeExpanded = remember { mutableStateOf(false) }
                            val week = remember { mutableStateOf(selectionOption.name) }.value
                            var timeString =
                                when (week) {
                                    Week.MONDAY.name -> viewModel.timeOfMonday
                                    Week.TUESDAY.name -> viewModel.timeOfTuesday
                                    Week.WEDNESDAY.name -> viewModel.timeOfWednesday
                                    Week.THURSDAY.name -> viewModel.timeOfThursday
                                    Week.FRIDAY.name -> viewModel.timeOfFriday
                                    Week.SATURDAY.name -> viewModel.timeOfSaturday
                                    Week.SUNDAY.name -> viewModel.timeOfSunday
                                    else -> "00:00"
                                }

                            val hour = remember { mutableStateOf(0) }
                            val minute = remember { mutableStateOf(0) }

                            if (timeString.isNotEmpty()) {
                                val temp = timeString.split(":").map {
                                    if(it.isEmpty()){
                                        0
                                    }else{
                                        it.toInt()
                                    }
                                }
                                hour.value = temp[0]
                                minute.value = temp[1]
                                timeString =
                                    temp[0].toString().padStart(2, '0') + ":" + temp[1].toString().padStart(2, '0')

                            }

                            timeList.add(TimeList(week,hour.value,minute.value))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(
                                        color = White,
                                    )
                                    .padding(15.dp, 20.dp)
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    text = selectionOption.jp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                                )

                                Spacer(modifier = Modifier.weight(1f))
                                // データを保存
                                TimeInput(viewModel,week,hour,minute)
                            }
                                Divider(modifier = Modifier.padding(horizontal = 15.dp))
                        }

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(
                                    color = White,
                                )
                                .padding(15.dp, 20.dp)
                                .fillMaxWidth()
                        ){
                            Button(
                                onClick = {
                                    expanded.value = false
                                    minuteExpanded.value = false
                                },
                                modifier = Modifier.padding(10.dp)
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(text = "閉じる")
                            }
                        }
                    }
                }
            }
        }
}