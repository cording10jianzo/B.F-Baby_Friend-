package com.example.coding10

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import com.example.coding10.databinding.FragmentMypageBinding
import java.io.InputStream

class MyPageFragment : Fragment() {

    lateinit var binding: FragmentMypageBinding

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

            init(2)
            infoProcess("save")
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
            Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
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
        var hashMap = HashMap<String, String>()
        when (data) {
            "edit" -> {
                Toast.makeText(context, "${myPageEtBlood.text}", Toast.LENGTH_SHORT).show()
                hashMap["혈액형"] = myPageEtBlood.text.toString()
                hashMap["번호"] = myPageEtBlood.text.toString()
                hashMap["이메일"] = myPageEtBlood.text.toString()
                hashMap["메모"] = myPageEtBlood.text.toString()
//                hashMap["혈액형"]?.let { Log.d("test", it) }
            }

            "cancel" -> {
                hashMap["혈액형"]?.let { Log.d("test", it) }
//                Toast.makeText(context, "${hashMap["혈액형"]}", Toast.LENGTH_SHORT).show()
            }

            "save" -> {
//                Toast.makeText(context, "저장", Toast.LENGTH_SHORT).show()
            }

            else -> {

            }
        }
    }
}
