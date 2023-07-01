package com.example.umbrellaapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umbrellaapp.common.Constants
import com.example.umbrellaapp.common.Prefecture
import com.example.umbrellaapp.data.repository.SettingInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingInfoViewModel @Inject constructor(
) :ViewModel() {


    private val SettingInfoRepository = SettingInfoRepository()
    var notification by mutableStateOf(false)
    var prefecture by mutableStateOf(Prefecture.HOKKAIDO.jp)
    var city by mutableStateOf(Prefecture.HOKKAIDO.cities[0].second)
    var timeOfMonday by mutableStateOf("00:00")
    var timeOfTuesday by mutableStateOf("00:00")
    var timeOfWednesday by mutableStateOf("00:00")
    var timeOfThursday by mutableStateOf("00:00")
    var timeOfFriday by mutableStateOf("00:00")
    var timeOfSaturday by mutableStateOf("00:00")
    var timeOfSunday by mutableStateOf("00:00")

    init {
        setSettingInfo()
    }

    private fun setSettingInfo() {
        viewModelScope.launch {
            val settingInfo =  SettingInfoRepository.first()
            notification = settingInfo.notification
            prefecture = settingInfo.prefecture
            city = settingInfo.city
            timeOfMonday = settingInfo.timeMonday
            timeOfTuesday = settingInfo.timeTuesday
            timeOfWednesday = settingInfo.timeWednesday
            timeOfThursday = settingInfo.timeThursday
            timeOfFriday = settingInfo.timeFriday
            timeOfSaturday = settingInfo.timeSaturday
            timeOfSunday = settingInfo.timeSunday
        }
    }

    // 通知
    fun updateNotification(value: Boolean){
        viewModelScope.launch {
//            getWeather()
            SettingInfoRepository.setNotification(value)
            notification = value
        }
    }

    // 都道府県
    fun updatePrefecture(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setPrefecture(value)
            prefecture = value
        }
    }

    // 市
    fun updateCity(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setCity(value)
            city = value
        }
    }

    // 都道府県、市
    fun updateLocate(type:String,value: String){
        viewModelScope.launch {
            if(type == Constants.LOCATE_TYPE_PREFECTURE){
                SettingInfoRepository.setPrefecture(value)
                prefecture = value
            }else if(type == Constants.LOCATE_TYPE_CITY){
                SettingInfoRepository.setCity(value)
                city = value
            }
        }
    }

    // 月曜日
    fun updateTimeOfMonday(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setTimeOfMonday(value)
            timeOfMonday = value
        }
    }

    // 火曜日
    fun updateTimeOfTuesDay(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setTimeOfTuesday(value)
            timeOfTuesday = value
        }
    }

    // 水曜日
    fun updateTimeOfWednesday(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setTimeOfWednesday(value)
            timeOfWednesday = value
        }
    }

    // 木曜日
    fun updateTimeOfThursday(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setTimeOfThursday(value)
            timeOfThursday = value
        }
    }

    // 金曜日
    fun updateTimeOfFriday(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setTimeOfFriday(value)
            timeOfFriday = value
        }
    }

    // 土曜日
    fun updateTimeOfSaturday(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setTimeOfSaturday(value)
            timeOfSaturday = value
        }
    }

    // 日曜日
    fun updateTimeOfSunday(value: String){
        viewModelScope.launch {
            SettingInfoRepository.setTimeOfSunday(value)
            timeOfSunday = value
        }
    }
}