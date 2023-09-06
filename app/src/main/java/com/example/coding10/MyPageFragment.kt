package com.example.coding10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coding10.databinding.FragmentMypageBinding

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

        binding.myPageButton.setOnClickListener {
            // 수정하기 -> 수정완료로  textChange
            //
            if(binding.myPageButton.text == "수정하기"){
                binding.myPageButton.text = "수정완료"
            }else{
                binding.myPageButton.text = "수정하기"
            }
        }
    }


    private fun initView() = with(binding){

    }
}