package com.example.umbrellaapp.presentation.view.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.umbrellaapp.common.Constants
import com.example.umbrellaapp.common.Prefecture
import com.example.umbrellaapp.presentation.view.components.dialog.CitiesDialog
import com.example.umbrellaapp.presentation.view.components.dialog.PrefectureList
import com.example.umbrellaapp.presentation.viewmodel.SettingInfoViewModel

@Composable
fun locationSetting(
    viewModel: SettingInfoViewModel = hiltViewModel()
){

    val expanded = remember { mutableStateOf(false) }
    val prefecture = remember { mutableStateOf("") }


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
}