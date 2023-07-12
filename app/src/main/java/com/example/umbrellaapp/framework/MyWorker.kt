package com.example.umbrellaapp.framework

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.umbrellaapp.common.Prefecture
import com.example.umbrellaapp.data.repository.SettingInfoRepository
import com.example.umbrellaapp.data.utile.LocationUtile
import com.example.umbrellaapp.data.utile.WeatherUtile
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val weatherUtile: WeatherUtile,
    private val locationUtile: LocationUtile,
    ) : Worker(ctx,params) {

    private val notificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    // カテゴリー名（通知設定画面に表示される情報）
    private val name = "通知のタイトル情報を設定"
    // システムに登録するChannelのID
    private val id = "umbrella_chanel"
    // 通知の詳細情報（通知設定画面に表示される情報）
    private val notifyDescription = "この通知の詳細情報を設定します"

    private val repository = SettingInfoRepository()

    companion object {
        var nid = 1
        private const val DELAY_MARGIN = 100L // 通知が正確に実行されるように、少し余裕を持たせる
        const val WORK_NAME = "com.example.umbrellaapp.framework.MyWorker"
    }

    init {
        // Channelの取得と生成
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.getNotificationChannel(id) == null
            val mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            mChannel.apply {
                description = notifyDescription
            }
            notificationManager.createNotificationChannel(mChannel)
        }
    }


    override fun doWork(): Result {

        try{

            var firstTimeFlag = inputData.getBoolean("firstTime",false)
            val notificationTimes = mutableListOf<Calendar>()
            val currentDate = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            Log.d("currentDate",currentDate.get(Calendar.MONTH).toString())

            // 現在時刻を取得
            val toDay = Calendar.getInstance()
            val toDayOfWeek = toDay.get(Calendar.DAY_OF_WEEK)
            var targetDayOfWeek:Int
            var targetDate:Calendar
            // 現在と同じ曜日のデータと比較
            runBlocking {
                targetDate = getDateFromWeekOfDay(toDayOfWeek)
            }
            var isToday = false

            if(toDay > targetDate){
                toDay.add(Calendar.DAY_OF_MONTH,1)
                targetDayOfWeek = toDay.get(Calendar.DAY_OF_WEEK)
                runBlocking {
                    targetDate = getDateFromWeekOfDay(targetDayOfWeek)
                }
                isToday = true
            }
            if(!firstTimeFlag) {
                // DataStoreから都道府県・市を取得
                val settingInfo = runBlocking {
                    repository.first()
                }
                var prefecture = settingInfo.prefecture
                var city = settingInfo.city
                val prefectureData = Prefecture.values().find { it.jp == prefecture }
                val prefectureEn = prefectureData?.name
                val cityEn = prefectureData?.cities?.firstOrNull { it.second == city }?.first

                if(prefectureEn == null || cityEn == null){
                    return Result.failure()
                }

                // 都道府県・市から緯度経度を取得
                val location = runBlocking{
                    locationUtile.getLocation(prefecture = prefectureEn, city = cityEn)
                }

                val lat = location["lat"]?.toDouble()
                val lon = location["lon"]?.toDouble()
                if(lat == null || lon == null) return Result.failure()
                // 通知メッセージを取得
                val message = runBlocking {
                    weatherUtile.getMessage(isToday,lat,lon)
                }

                val notification = NotificationCompat.Builder(applicationContext, id).apply {
                    setContentTitle("傘お知らせ")
                    setContentText(message)
                    setSmallIcon(android.R.drawable.ic_dialog_info)
                }
                // 通知
                notificationManager.notify(nid, notification.build())
            }

            // 通知時間をセット
            notificationTimes.add(targetDate)

            // 今の時刻から次の通知の時間までの時間を計算
            var minDiff = Long.MAX_VALUE
            for (time in notificationTimes) {
                val diff = time.timeInMillis - currentDate.timeInMillis
                Log.d("diff", "time:${time}　currentDate:${currentDate}　diff:$diff")
                if (diff in 1 until minDiff) {
                    minDiff = diff
                }
            }

            // 次の通知の時間までの待機時間を設定
            if (minDiff < Long.MAX_VALUE) {
                val notificationDelay = minDiff + DELAY_MARGIN
                val delayWorkRequest =
                    OneTimeWorkRequestBuilder<MyWorker>()
                        .setInitialDelay(notificationDelay, TimeUnit.MILLISECONDS)
                        .addTag("weatherNotification")
                        .build()
                WorkManager.getInstance().enqueue(delayWorkRequest)
            }

            Log.d("weatherNotification", "Task executed by " + Thread.currentThread().name);
            return Result.success()
        }catch (e:Exception){
            Log.d("doWorkError",e.toString())
            return Result.failure()
        }
    }

    private suspend fun getDateFromWeekOfDay(dayOfWeek: Int) : Calendar = coroutineScope {

        val data = withContext(IO) {
            repository.first()
        }

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        var time : String

        when (dayOfWeek) {
            Calendar.MONDAY -> {
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY)
                time = data.timeMonday
            }
            Calendar.TUESDAY -> {
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY)
                time = data.timeTuesday
            }
            Calendar.WEDNESDAY -> {
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY)
                time = data.timeWednesday
            }
            Calendar.THURSDAY -> {
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY)
                time = data.timeThursday
            }
            Calendar.FRIDAY -> {
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY)
                time = data.timeFriday
            }
            Calendar.SATURDAY -> {
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY)
                time = data.timeSaturday
            }
            Calendar.SUNDAY -> {
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY)
                time = data.timeSunday
            }
            else -> {
                time = "00:00"
            }
        }
        val year  = calendar.get(Calendar.YEAR)
        val month  = calendar.get(Calendar.MONTH)
        val day  = calendar.get(Calendar.DATE)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val targetTime = timeFormat.parse(time)
        calendar.time = targetTime
        calendar.set(Calendar.YEAR,year)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.DATE,day)
        return@coroutineScope calendar
    }
}