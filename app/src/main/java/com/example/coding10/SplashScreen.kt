package com.example.coding10

import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.UpdateAppearance
import android.widget.TextView
import com.example.coding10.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding: SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        addOutlineToText(binding.splashTextView, Color.BLUE, Color.GREEN)

        val animation = binding.splashLottie
        animation.setAnimation("animation.json")
        animation.loop(true)
        animation.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_down_enter,R.anim.slide_down_exit)
            finish()
        }, 2800)
    }

//    class OutlineSpan(private val strokeWidth: Float, private val strokeColor: Int, private val textColor: Int) :
//        CharacterStyle(),
//        UpdateAppearance {
//
//        override fun updateDrawState(tp: TextPaint) {
//            tp.style = Paint.Style.STROKE
//            tp.strokeWidth = strokeWidth
//            tp.color = strokeColor
//            tp.maskFilter = BlurMaskFilter(0.5f, BlurMaskFilter.Blur.NORMAL)
//        }
//
//
//
//    }
//    fun addOutlineToText(textView: TextView, outlineColor: Int, textColor: Int) {
//        val inputText = textView.text.toString()
//        val spannableString = SpannableString(inputText)
//
//        // 외곽선의 두께와 색상을 여기에 설정
//        val outlineSpan = OutlineSpan(2f, outlineColor, textColor)
//
//
//        // 텍스트뷰의 모든 글자에 외곽선 스팬을 적용
//        spannableString.setSpan(outlineSpan, 0, inputText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        // 외곽선이 적용된 SpannableString을 텍스트뷰에 설정
//        textView.text = spannableString
//    }
}