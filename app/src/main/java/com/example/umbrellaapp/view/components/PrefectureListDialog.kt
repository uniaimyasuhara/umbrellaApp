package com.example.umbrellaapp.view.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.umbrellaapp.common.Prefecture
import com.example.umbrellaapp.view.viewmodel.SettingInfoViewModel

@Composable
fun PrefectureList(
    label : String,
    menuItems :List<Prefecture>,
    viewModel: SettingInfoViewModel = hiltViewModel()
) {
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
                text =  viewModel.prefecture,
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
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .width(250.dp)
                    .fillMaxHeight()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(
                            color = Color.White,
                        )
                        .clip(RoundedCornerShape(4.dp))
                        .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(4.dp))
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    menuItems.forEach { selectionOption ->
                        Column(modifier = Modifier.clickable {
                            viewModel.prefecture = selectionOption.jp
                            expanded.value = false
                        }) {
                            Text(
                                text = selectionOption.jp,
                                modifier = Modifier.padding(10.dp,20.dp),
                            )
                            Divider(modifier = Modifier.padding(horizontal = 15.dp))
                        }
                    }
                }
            }
        }
        }
}