import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.umbrellaapp.common.Week
import com.example.umbrellaapp.view.viewmodel.SettingInfoViewModel

@Composable
fun TimeInput(viewModel: SettingInfoViewModel,week: String,hour:MutableState<Int>,minute:MutableState<Int>) {

    var hourText by remember { mutableStateOf(hour.value.toString()) }
    var minuteText by remember { mutableStateOf(minute.value.toString()) }
    var error by remember { mutableStateOf("") }
    var target = ""

    Row(
        modifier = Modifier.height(60.dp)
    ) {
        OutlinedTextField(
            value = hourText,
            onValueChange = {
                if(it.isEmpty()){
                    hourText = "0"
                    target = "$hourText:$minuteText"
                    updateTime(viewModel, week, target)
                }else  if (it.isDigitsOnly()){
                    val value = it.toIntOrNull() ?: 0
                    if(hourText.length == 1 && hourText == "0"){
                        hourText = value.toString().replace("0","")
                    }else if (value in 0..23) {
                        hourText = it
                    }
                    target = "$hourText:$minuteText"
                    updateTime(viewModel, week, target)
                }

            },
            label = { Text("時間") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(70.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        OutlinedTextField(
            value = minuteText,
            onValueChange = {
                if(it.isEmpty()){
                    minuteText = "0"
                    target = "$hourText:$minuteText"
                    updateTime(viewModel, week, target)
                }else  if (it.isDigitsOnly()){
                    val value = it.toIntOrNull() ?: 0
                    if(minuteText.length == 1 && minuteText == "0"){
                        minuteText = value.toString().replace("0","")
                    }else if (value in 0..59) {
                        minuteText = it
                    }
                    target = "$hourText:$minuteText"
                    updateTime(viewModel, week, target)
                }
            },
            label = { Text("分") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(70.dp)
        )

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}



fun updateTime(viewModel: SettingInfoViewModel,week: String,target:String){
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
    }
}