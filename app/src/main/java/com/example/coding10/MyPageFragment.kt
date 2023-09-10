package com.example.coding10

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import com.example.coding10.databinding.FragmentMypageBinding
import com.google.android.material.snackbar.Snackbar
import java.io.InputStream

class MyPageFragment : Fragment() {

    lateinit var binding: FragmentMypageBinding
    private var hashMap = HashMap<String, String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        with(binding) {
            myPageEtBlood.addTextChangedListener(createBloodTypeTextWatcher(myPageEtBlood))
            myPageEtNumber.addTextChangedListener(createNumberTextWatcher(myPageEtNumber))
            myPageEtEmail.addTextChangedListener(createMailTextWatcher(myPageEtEmail))
        }
    }


    private fun initView() = with(binding) {

        binding.myPageMainImage.setOnClickListener { // 이미지 클릭시
            if (binding.myPageEditButton.isInvisible) {

                val intent = Intent()
                // 기기 기본 갤러리 접근
                intent.type = MediaStore.Images.Media.CONTENT_TYPE
                // 구글 갤러리 접근
                // intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(intent, 101)

            }
        }
        // 수정하기 버튼을 누른 경우
        binding.myPageEditButton.setOnClickListener {

            init(1)
            infoProcess("edit")

        }
        // 취소하기 버튼을 누른 경우
        binding.myPageCancelButton.setOnClickListener {

            init(2)
            infoProcess("cancel")

        }

        // 완료하기 버튼을 누른 경우
        binding.myPageCompleteButton.setOnClickListener {

            if (myPageTvBloodTypeError.visibility == View.INVISIBLE &&
                myPageTvNumberError.visibility == View.INVISIBLE &&
                myPageTvEmailError.visibility == View.INVISIBLE
            ) {
                init(2)
                infoProcess("edit")
            } else {
                Snackbar.make(binding.root, "입력 값을 확인해주세요.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 101 && resultCode == RESULT_OK) {
            try {
                val inputStream: InputStream? =
                    requireContext().contentResolver.openInputStream(data?.data!!)
                val bm = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
                binding.myPageMainImage.setImageBitmap(bm)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (requestCode == 101 && resultCode == RESULT_CANCELED) {

        }
    }

    private fun init(switch: Int) = with(binding) {

        when (switch) {
            1 -> {

                myPageEtBlood.isEnabled = true
                myPageEtEmail.isEnabled = true
                myPageEtNumber.isEnabled = true
                myPageEtMemo.isEnabled = true
                myPageEditButton.visibility = View.INVISIBLE
                myPageCancelButton.visibility = View.VISIBLE
                myPageCompleteButton.visibility = View.VISIBLE

            }

            2 -> {

                myPageEtBlood.isEnabled = false
                myPageEtEmail.isEnabled = false
                myPageEtNumber.isEnabled = false
                myPageEtMemo.isEnabled = false
                myPageCancelButton.visibility = View.INVISIBLE
                myPageCompleteButton.visibility = View.INVISIBLE
                myPageEditButton.visibility = View.VISIBLE

            }

            else -> {

            }
        }
    }

    private fun infoProcess(data: String) = with(binding) {

        when (data) {
            "edit" -> {

                hashMap["혈액형"] = myPageEtBlood.text.toString()
                hashMap["번호"] = myPageEtNumber.text.toString()
                hashMap["이메일"] = myPageEtEmail.text.toString()
                hashMap["메모"] = myPageEtMemo.text.toString()

//                hashMap["혈액형"]?.let { Log.d("test", it) }
            }

            "cancel" -> {

                myPageEtBlood.text = Editable.Factory.getInstance().newEditable(hashMap["혈액형"])
                myPageEtNumber.text = Editable.Factory.getInstance().newEditable(hashMap["번호"])
                myPageEtEmail.text = Editable.Factory.getInstance().newEditable(hashMap["이메일"])
                myPageEtMemo.text = Editable.Factory.getInstance().newEditable(hashMap["메모"])

            }

            else -> {

            }
        }
    }

    private fun createBloodTypeTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {

                    val id = s.toString()
                    val regexPatternLength = Regex("^.{1,2}\$")
                    val inputText = Regex("[ABOabo]+")

                    if (id.isNotEmpty() && inputText.matches(id) && regexPatternLength.matches(id)) {
                        myPageTvBloodTypeError.visibility = View.INVISIBLE
                    } else {
                        myPageTvBloodTypeError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    private fun createNumberTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {
                    val regexPatternLength = Regex("^.{1,13}\$")
                    val id = s.toString()
                    val regexPattern1 = Regex("^\\d{3}-\\d{4}-\\d{4}$")
                    val regexPattern2 = Regex("^[A-Za-z]")
                    if (regexPattern1.matches(id) && id.isNotEmpty() && regexPatternLength.matches(id)) {
                        myPageTvNumberError.visibility = View.INVISIBLE
                    } else if (regexPatternLength.matches(id)){
                        myPageTvNumberError.visibility = View.INVISIBLE
                    } else if (regexPattern2.matches(id)){
                        myPageTvNumberError.visibility = View.VISIBLE
                    } else{
                        myPageTvNumberError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    private fun createMailTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                with(binding) {

                    val id = s.toString()
                    val emailRegexPattern = Regex("^[A-Za-z0-9+_.-]{1,25}+@(.+)\$")
                    val regexPatternLength = Regex("^.{1,13}\$")

                    if (id.isNotEmpty() && emailRegexPattern.matches(id) ) {
                        myPageTvEmailError.visibility = View.INVISIBLE
                    } else {
                        myPageTvEmailError.visibility = View.VISIBLE
                    }
                }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }
}
