package com.android.contectapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
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
    //notification 설정
    fun createNotification(title: String, content: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channel_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.boy)
    }

    fun showNotification(notificationId: Int, notificationBuilder: NotificationCompat.Builder) {
        val notificationManager = getManager()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

}

