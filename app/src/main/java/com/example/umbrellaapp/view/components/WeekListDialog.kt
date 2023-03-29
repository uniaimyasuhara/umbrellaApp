package com.example.umbrella.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.umbrellaapp.common.Week


@Composable
fun getWeekList(
    label:String,
    menuItems :List<Week>,
    fixedOptionText:String
){
    val expanded = remember { mutableStateOf(false) }
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
        if (expanded.value) {
            Dialog(
                onDismissRequest = { expanded.value = false },
            ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { expanded.value = !expanded.value }
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.background(
                                color = White,
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(4.dp))
                                .fillMaxWidth()
                        ) {
                            menuItems.forEach { selectionOption ->
                                var text by remember { mutableStateOf("") }

                                Row(
                                    modifier = Modifier.background(
                                        color = White,
                                    )
                                        .padding(15.dp,20.dp)
                                        .fillMaxWidth()
                                ) {

                                    Text(
                                        text = selectionOption.jp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    OutlinedTextField(
                                        value = text,
                                        onValueChange = { text = it },
                                        modifier =
                                        Modifier
                                            .padding(5.dp)
                                            .width(150.dp)
                                            .height(20.dp)
                                    )
                                }

                                Divider(modifier = Modifier.padding(horizontal = 15.dp))
                            }
                        }
                    }
                }
            }
    }
