package com.example.umbrellaapp.view.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.umbrellaapp.common.Week
import com.example.umbrellaapp.view.viewmodel.SettingInfoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectTimeDialog(
    expanded: MutableState<Boolean>,
    hours: List<Int>,
    minutes:List<Int>,
    targetHour: MutableState<Int>,
    targetMinute: MutableState<Int>,
    week: String,
    viewModel: SettingInfoViewModel = hiltViewModel(),
){
    if(expanded.value){
        Dialog(onDismissRequest = {
            expanded.value = false
        }) {

        Column(
            modifier = Modifier.width(300.dp)
                .clip(RoundedCornerShape(7.dp))

        ) {
            val scrollHourState = rememberLazyListState()
            val scrollMinuteState = rememberLazyListState()
            var selectedHour by remember { mutableStateOf(targetHour.value) }
            var selectedMinute by remember { mutableStateOf(targetMinute.value) }
            var itemHeight by remember { mutableStateOf(60) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(
                        color = Color.White,
                    )
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        RoundedCornerShape(4.dp)
                    )
                    .height(70.dp)
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                LaunchedEffect(selectedHour) {
                    scrollHourState.scrollToItem(index = if(selectedHour == -1 || selectedHour == 0) 0 else  selectedHour)
                }

                LaunchedEffect(selectedMinute) {
                    scrollMinuteState.scrollToItem(index = if(selectedMinute == -1 || selectedMinute == 0) 0 else  selectedMinute)
                }

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight()
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        state = scrollHourState
                    ) {
                        items(hours) { hour ->
                            var index = hours.indexOf(hour)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = selectedHour == index,
                                        onClick = {
                                            selectedHour = index
                                            targetHour.value = selectedHour
                                        }
                                    )
                                    .height(itemHeight.dp),
                                    contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = hour.toString(),
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
                
                Text(text = ":", fontSize = 30.sp, modifier = Modifier.padding(5.dp))

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight()
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        state = scrollMinuteState
                    ) {
                        items(minutes) { minute ->
                            var index = minutes.indexOf(minute)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = selectedMinute == index,
                                        onClick = {
                                            selectedMinute = index
                                            targetMinute.value = selectedMinute
                                        }
                                    )
                                    .height(itemHeight.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = minute.toString(),
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = Color.White,
                    )
                    .padding(10.dp, 10.dp)
                    .fillMaxWidth()
            ) {
                Button(onClick = { expanded.value = false },Modifier.width(130.dp)) {
                    Text(text = "キャンセル")
                }
                    Spacer(modifier = Modifier.width(20.dp))

                    Button(onClick = {
                        var target = "$selectedHour:$selectedMinute"
                        when (week) {
                            Week.MONDAY.name -> {
                                viewModel.updateTimeOfMonday(target)
                            }
                            Week.TUESDAY.name -> {
                                viewModel.updateTimeOfTuesDay(target)
                            }
                            Week.WEDNESDAY.name -> {
                                viewModel.updateTimeOfWednesday(target)
                            }
                            Week.THURSDAY.name -> {
                                viewModel.updateTimeOfThursday(target)
                            }
                            Week.FRIDAY.name -> {
                                viewModel.updateTimeOfFriday(target)
                            }
                            Week.SATURDAY.name -> {
                                viewModel.updateTimeOfSaturday(target)
                            }
                            Week.SUNDAY.name -> {
                                viewModel.updateTimeOfSunday(target)
                            }
                            else -> "00:00"
                        }
                        expanded.value = false
                    },Modifier.width(130.dp)) {
                    Text(text = "OK")
                }
            }
        }
        }
    }
}

