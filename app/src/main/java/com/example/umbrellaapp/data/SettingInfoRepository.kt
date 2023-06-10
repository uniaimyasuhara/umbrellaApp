package com.example.umbrellaapp.data

import androidx.datastore.core.DataStore
import com.example.umbrellaapp.MyApplication
import com.example.umbrellaapp.SettingInfo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SettingInfoRepository @Inject constructor(

) {
    private var settingInfoStore:DataStore<SettingInfo>
    private val context = MyApplication.appContext;

    init {
        settingInfoStore = context!!.SettingInfoDataStore
    }

    // 設定情報を取得
    suspend fun first() : SettingInfo{
        return settingInfoStore.data.first()
    }

    /*
    * 通知
    */
    suspend fun setNotification(notification: Boolean){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setNotification(notification)
                .build()
        }
    }

    /*
    *　都道府県
    */
    suspend fun setPrefecture(prefecture: String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setPrefecture(prefecture)
                .build()
        }
    }

    /*
    *　市を設定
    */
    suspend fun setCity(city : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setCity(city)
                .build()
        }
    }

    /*
    *　月曜日の設定時間
    */
    suspend fun setTimeOfMonday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeMonday(time)
                .build()
        }
    }

    /*
    *　火曜日の設定時間
    */
    suspend fun setTimeOfTuesday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeTuesday(time)
                .build()
        }
    }

    /*
    *　水曜日の設定時間
    */
    suspend fun setTimeOfWednesday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeWednesday(time)
                .build()
        }
    }

    /*
    * 木曜日の設定時間
    */
    suspend fun setTimeOfThursday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeThursday(time)
                .build()
        }
    }

    /*
    *　金曜日の設定時間
    */
    suspend fun setTimeOfFriday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeFriday(time)
                .build()
        }
    }

    /*
    *　土曜日の設定時間
    */
    suspend fun setTimeOfSaturday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeSaturday(time)
                .build()
        }
    }

    /*
    *　日曜日の設定時間
    */
    suspend fun setTimeOfSunday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeSunday(time)
                .build()
        }
    }
}