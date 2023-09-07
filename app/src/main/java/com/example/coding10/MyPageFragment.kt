package com.example.coding10

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.provider.MediaStore
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


    private fun initView() = with(binding){
        binding.myPageMainImage.setOnClickListener { // 이미지 클릭시
            if(binding.myPageEditButton.isInvisible){

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

            binding.myPageEtBlood.isEnabled = true
            binding.myPageEtEmail.isEnabled = true
            binding.myPageEtNumber.isEnabled = true
            binding.myPageEtMemo.isEnabled = true
            binding.myPageEditButton.visibility = View.INVISIBLE
            binding.myPageCancelButton.visibility = View.VISIBLE
            binding.myPageCompleteButton.visibility = View.VISIBLE

        }
        // 취소하기 버튼을 누른 경우
        binding.myPageCancelButton.setOnClickListener {

            binding.myPageEtBlood.isEnabled = false
            binding.myPageEtEmail.isEnabled = false
            binding.myPageEtNumber.isEnabled = false
            binding.myPageEtMemo.isEnabled = false
            binding.myPageCancelButton.visibility = View.INVISIBLE
            binding.myPageCompleteButton.visibility = View.INVISIBLE
            binding.myPageEditButton.visibility = View.VISIBLE

        }
        // 완료하기 버튼을 누른 경우
        binding.myPageCompleteButton.setOnClickListener {

            binding.myPageEtBlood.isEnabled = false
            binding.myPageEtEmail.isEnabled = false
            binding.myPageEtNumber.isEnabled = false
            binding.myPageEtMemo.isEnabled = false
            binding.myPageCancelButton.visibility = View.INVISIBLE
            binding.myPageCompleteButton.visibility = View.INVISIBLE
            binding.myPageEditButton.visibility = View.VISIBLE

            val message = "수정을 완료했어요 ^_^"
            (activity as BaseActivity).showtoast(message)

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 101 && resultCode == RESULT_OK){
            try{
                val inputStream : InputStream? = requireContext().contentResolver.openInputStream(data?.data!!)
                val bm = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
                binding.myPageMainImage.setImageBitmap(bm)

            }catch (e: Exception){
                e.printStackTrace()
            }
        }else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
            Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show()
        }
    }
}