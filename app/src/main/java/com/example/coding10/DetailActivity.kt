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
import androidx.core.content.res.ResourcesCompat
import com.example.coding10.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showtoast("상세페이지로 이동합니다.")

        val data = intent.getParcelableExtra<MainItems>("DATA")
        binding.detailPageMainImage.setImageURI(data?.aIcon2)
        binding.detailPageMainTextName2.text = data?.aName
        binding.detailPageMainTextAge2.text = data?.aAge
        binding.detailPageTextBlood2.text = data?.aBloodType
        binding.detailPageTextNumber2.text = data?.aNumber
        binding.detailPageTextEmail2.text = data?.aEmail
        binding.detailPageTextMemo2.text = data?.aMemo

        binding.detailPageButtonCall.setOnClickListener {
            val input = binding.detailPageTextNumber2.text.toString()
            val myUri = Uri.parse("tel:${input}")
            val myIntent = Intent(Intent.ACTION_DIAL, myUri)
            startActivity(myIntent)
        }

        binding.detailPageButtonMessage.setOnClickListener {
            val input = binding.detailPageTextNumber2.text.toString()
            val message = "- 이 문자는 Baby Friend에서 보내는 문자입니다.-\n 안녕하세요. 저랑 친하게 지내요 >.<"
            val sendmessage = Intent(Intent.ACTION_SENDTO)
            sendmessage.data = Uri.parse("smsto:$input")
            sendmessage.putExtra("B.F\n", message)
            startActivity(sendmessage)
        }

        binding.detailPageTopVector.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            finish()
        }

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
        finish()
    }
}


