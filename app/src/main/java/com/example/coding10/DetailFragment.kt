package com.example.coding10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coding10.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

    private val listAdapter by lazy {
        DetailListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        val testList = arrayListOf<DetailItems>()
        for (i in 1 .. 20) {
            testList.add(
                DetailItems(
                    "TEST DETAIL PAGE ITEM : $i"
                )
            )
        }
        listAdapter.addItems(testList)
    }


    private fun initView() = with(binding){
        detailRecyclerview.adapter = listAdapter
    }
}