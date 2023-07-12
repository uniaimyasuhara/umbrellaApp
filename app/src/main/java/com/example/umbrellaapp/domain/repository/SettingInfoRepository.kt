package com.example.umbrellaapp.domain.repository

import com.example.umbrellaapp.SettingInfo

interface SettingInfoRepository {

    suspend fun first(){}

    /*
    * 通知
    */
    suspend fun setNotification(notification: Boolean){}

    /*
    * 都道府県
    */
    suspend fun setPrefecture(prefecture: String){}

    /*
    * 市を設定
    */
    suspend fun setCity(city : String){}

    /*
    * 月曜日の設定時間
    */
    suspend fun setTimeOfMonday(time : String){}

    /*
    * 火曜日の設定時間
    */
    suspend fun setTimeOfTuesday(time : String){}

    /*
    * 水曜日の設定時間
    */
    suspend fun setTimeOfWednesday(time : String){}

    /*
    * 木曜日の設定時間
    */
    suspend fun setTimeOfThursday(time : String){}

    /*
    * 金曜日の設定時間
    */
    suspend fun setTimeOfFriday(time : String){}

    /*
    * 土曜日の設定時間
    */
    suspend fun setTimeOfSaturday(time : String){}

    /*
    * 日曜日の設定時間
    */
    suspend fun setTimeOfSunday(time : String){}
}