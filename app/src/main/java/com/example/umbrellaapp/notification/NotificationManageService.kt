package com.example.umbrellaapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.umbrellaapp.MainActivity
import java.util.*

class NotificationManageService : Service(){
    // クラス定数
    companion object {
        private const val CHANNEL_ID = "notificationManagerService_notification_channel"
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    // サービスの初期化時に実行する処理
    // <- 親クラスのonCreate()メソッドの呼び出しは不要
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        // 通知チャネル名
        val name = "傘通知"

        // 通知チャネルの重要度
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        // 通知チャネル
        val channel = NotificationChannel(CHANNEL_ID, name, importance)

        // NotificationManagerオブジェクトの取得
        val manager = getSystemService(NotificationManager::class.java)

        // 通知チャネルをセット
        manager.createNotificationChannel(channel)

        // Notificationオブジェクトを生成するBuilderオブジェクト
//        val builder = NotificationCompat.Builder(this@NotificationManageService, CHANNEL_ID)
//
//        // 通知ドロワーに表示するタイトル
//        builder.setContentTitle("傘お知らせ通知")
//
//        // 通知ドロワーに表示するメッセージ
//        builder.setContentText("今日傘要るで")
//
//        // 起動先アクティビティを指定するIntentオブジェクトの生成
//        val intent = Intent(this@NotificationManageService, MainActivity::class.java)
//
//        // アクティビティを起動するPendingIntentオブジェクト
//        val stopServiceIntent = PendingIntent.getActivity(
//            this@NotificationManageService,
//            0,
//            intent,
//            PendingIntent.FLAG_CANCEL_CURRENT
//        )
//
//        // BuilderにPendingIntentオブジェクトをセット
//        builder.setContentIntent(stopServiceIntent)
//
//        // タップされた通知メッセージを自動的に消去
//        builder.setAutoCancel(true)
//
//        // Notificationオブジェクトの生成
//        val notification = builder.build()
//
//        // サービスのフォアグラウンド実行
//        startForeground(200, notification)
//
//        stopSelf()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // 通知チャネル名
        val name = "傘通知"

        // 通知チャネルの重要度
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        // 通知チャネル
        val channel = NotificationChannel(CHANNEL_ID, name, importance)

        // NotificationManagerオブジェクトの取得
        val manager = getSystemService(NotificationManager::class.java)

//        // 通知チャネルをセット
        manager.createNotificationChannel(channel)


        val builder = NotificationCompat.Builder(this@NotificationManageService, CHANNEL_ID)

        // 通知エリアに表示するアイコン
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
        // 通知ドロワーに表示するタイトル
        builder.setContentTitle("傘お知らせ通知")

        // 通知ドロワーに表示するメッセージ
        builder.setContentText("今日傘要るで")

        // 起動先アクティビティを指定するIntentオブジェクトの生成
        val intent = Intent(this@NotificationManageService, MainActivity::class.java)

        // アクティビティを起動するPendingIntentオブジェクト
        val stopServiceIntent = PendingIntent.getActivity(
            this@NotificationManageService,
            200,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        // BuilderにPendingIntentオブジェクトをセット
        builder.setContentIntent(stopServiceIntent)

        // タップされた通知メッセージを自動的に消去
        builder.setAutoCancel(true)

        // Notificationオブジェクトの生成
        val notification = builder.build()

        manager.notify(200, notification)

        // サービスのフォアグラウンド実行
        startForeground(200, notification)

//        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }



}