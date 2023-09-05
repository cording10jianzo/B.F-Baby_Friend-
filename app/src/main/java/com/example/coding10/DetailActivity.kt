package com.example.coding10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.coding10.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showtoast("상세페이지로 이동합니다.")

//        binding.detailPageButtonCall.setOnClickListener{
//            val editNumber =
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telNumber))
//            startActivity(intent)
//            }

        binding.detailPageTopBell.setOnClickListener {
            notification()
        }
    }

    fun notification() {
        val manager =
            getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM).build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }

            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)

        } else {
            // 26 버전 이하
            builder = NotificationCompat.Builder(this)
        }

        // 기본 정보
        builder.run {
            setSmallIcon(R.drawable.alarm)
            setWhen(System.currentTimeMillis())
            setContentTitle("-Baby Friend-")
            setContentText("친구에게 알림을 보냈습니다!!")
        }
        manager.notify(11, builder.build())
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity((intent))
    }
}


