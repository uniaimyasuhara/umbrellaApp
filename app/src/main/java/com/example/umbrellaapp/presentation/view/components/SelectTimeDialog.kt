package com.example.umbrellaapp.presentation.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTimePickerDialog(
    initialHour: Int,
    initialMinute: Int,
    onTimeSet: (hour: Int, minute: Int) -> Unit
) {
    var selectedHour by remember { mutableStateOf(initialHour) }
    var selectedMinute by remember { mutableStateOf(initialMinute) }

    Column {
        Text("Custom Time Picker", style = MaterialTheme.typography.h6)

        Row(Modifier.padding(16.dp)) {
            NumberPicker(selectedHour, 0, 23) { selectedHour = it }
            Spacer(Modifier.width(32.dp))
            NumberPicker(selectedMinute, 0, 59) { selectedMinute = it }
        }

        Button(onClick = { onTimeSet(selectedHour, selectedMinute) }) {
            Text("Set Time")
        }
    }
}

@Composable
fun NumberPicker(
    value: Int,
    minValue: Int,
    maxValue: Int,
    onValueChange: (Int) -> Unit
) {
    Box(
        Modifier
            .width(50.dp)
            .height(200.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            for (i in minValue..maxValue) {
                Text(
                    text = if (i < 10) "0$i" else i.toString(),
                    style = MaterialTheme.typography.body1,
                    color = if (i == value) Color.Blue else Color.Black,
                    modifier = Modifier.clickable { onValueChange(i) }
                )
            }
        }
    }
}






