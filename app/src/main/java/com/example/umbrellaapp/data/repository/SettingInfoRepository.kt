package com.example.umbrellaapp.data.repository

import androidx.datastore.core.DataStore
import com.example.umbrellaapp.MyApplication
import com.example.umbrellaapp.SettingInfo
import com.example.umbrellaapp.data.datasource.local.SettingInfoDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
        commonUpdate()
    }

    /*
    * 都道府県
    */
    suspend fun setPrefecture(prefecture: String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setPrefecture(prefecture)
                .build()
        }
        commonUpdate()
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
        commonUpdate()
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
        commonUpdate()
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
        commonUpdate()
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
        commonUpdate()
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
        commonUpdate()
    }

    /*
    * 金曜日の設定時間
    */
    suspend fun setTimeOfFriday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeFriday(time)
                .build()
        }
        commonUpdate()
    }

    /*
    * 土曜日の設定時間
    */
    suspend fun setTimeOfSaturday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeSaturday(time)
                .build()
        }
        commonUpdate()

    }

    /*
    * 日曜日の設定時間
    */
    suspend fun setTimeOfSunday(time : String){
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setTimeSunday(time)
                .build()
        }
        commonUpdate()
    }

    private suspend fun setTimeOfUpdate(){
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setUpdateTime(formattedDateTime)
                .build()
        }
    }

    private suspend fun setUpdateCount(){
        var count = this.first().updateCount + 1
        settingInfoStore.updateData {currentSetting : SettingInfo ->
            currentSetting.toBuilder()
                .setUpdateCount(count)
                .build()
        }
    }

    private suspend fun commonUpdate(){
        setTimeOfUpdate()
        setUpdateCount()
    }
}