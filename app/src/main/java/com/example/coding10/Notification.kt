package com.android.contectapp


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import com.example.coding10.MainActivity
import com.example.coding10.R


class Notification(base: Context?):ContextWrapper(base) {
    private val channel_ID="channelID"
    private val channel_NM="channelNM"
    init{
        //오레오 이상은 반드시 채널을 설정해줘야 Notification이 작동함
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            //채널 생성
            NewChannel()
    }
    // 채널 생성
    private fun NewChannel(){
        var channel=NotificationChannel(channel_ID,channel_NM,NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor= Color.RED
        channel.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        getManager().createNotificationChannel(channel)

    }
    //알림 매니저생성
    fun getManager():NotificationManager{
        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
//    notification 설정
//    fun createNotification(title: String, content: String): NotificationCompat.Builder {
//        return NotificationCompat.Builder(applicationContext, channel_ID)
//            .setContentTitle(title)
//            .setContentText(content)
//            .setSmallIcon(R.drawable.boy)
//    }
    fun createNotification(title: String, content: String): NotificationCompat.Builder {
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(applicationContext, channel_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.boy)
            .setContentIntent(pendingIntent) // PendingIntent 추가

        // 아래 코드로 알림을 5초 후에 받을 수 있도록 설정
        val delayMillis: Long = 5000 // 5초를 밀리초로 표현
        val futureTimeMillis = SystemClock.elapsedRealtime() + delayMillis
        notificationBuilder.setWhen(futureTimeMillis)

        return notificationBuilder
    }

    fun showNotification(notificationId: Int, notificationBuilder: NotificationCompat.Builder) {
        val notificationManager = getManager()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}

