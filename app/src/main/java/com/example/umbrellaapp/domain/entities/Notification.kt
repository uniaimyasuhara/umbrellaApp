package com.example.umbrellaapp.domain.entities

import android.graphics.drawable.Icon

class Notification {

    private var message: String

    private var icon: Icon

    constructor(message: String, icon: Icon){
        this.message = message
        this.icon = icon
    }

    private fun getMessage(): String {
        return this.message
    }

    private fun setMessage(message:String) {
        this.message = message
    }
}