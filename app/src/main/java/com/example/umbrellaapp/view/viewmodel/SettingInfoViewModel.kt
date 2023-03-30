package com.example.umbrellaapp.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.umbrellaapp.common.Prefecture
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingInfoViewModel @Inject constructor() :ViewModel() {
    var notification by mutableStateOf(false)
    var prefecture by mutableStateOf(Prefecture.HOKKAIDO.jp)
    var timeOfMonday by mutableStateOf("00:00")
    var timeOfTuesday by mutableStateOf("00:00")
    var timeOfWednesday by mutableStateOf("00:00")
    var timeOfThursday by mutableStateOf("00:00")
    var timeOfFriday by mutableStateOf("00:00")
    var timeOfSaturday by mutableStateOf("00:00")
    var timeOfSunday by mutableStateOf("00:00")
}