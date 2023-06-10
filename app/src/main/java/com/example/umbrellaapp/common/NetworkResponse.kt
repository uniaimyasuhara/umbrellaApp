package com.example.umbrellaapp.common

// レスポンスの状態を管理
sealed class NetworkResponse<T> (
    val data:T? = null,
    val error:String? = null
){
    // 成功
    class Success<T>(data: T?) : NetworkResponse<T>(data = data)
    // 失敗
    class Failure<T>(error: String?) : NetworkResponse<T>(error = error)
    // 読み込み中
    class Loading<T> : NetworkResponse<T>()
}