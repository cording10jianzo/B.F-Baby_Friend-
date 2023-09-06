package com.example.coding10

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    private val listAdapter by lazy {
        MainListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecyclerview.adapter = listAdapter
        binding.mainRecyclerview.addItemDecoration(StickyHeaderItemDecoration(stickyHeaderInterface))
        listAdapter.addItems(displayData())
        listAdapter.itemClick = object : MainListAdapter.ItemClick{
            override fun onClick(position: Int) {

                val i = Intent(activity, DetailActivity::class.java).apply {
                    putExtra("DATA", dataList[position] as MainItems)
                }
                startActivity(i)
            }
        }
    }
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {

            }
        }

    private val stickyHeaderInterface = object :StickyHeaderItemDecoration.StickyHeaderInterface {
        override fun getHeaderPositionForItem(itemPosition: Int): Int {
            for(i in itemPosition downTo 0){
                if (isHeader(i)) {
                    return i
                }
            }
            return 0
        }

        override fun getHeaderLayout(headerPosition: Int): Int {
            return R.layout.item_main_category
        }

        override fun bindHeaderData(header: View?, headerPosition: Int) {
            if(headerPosition != RecyclerView.NO_POSITION && isHeader(headerPosition)){
                header?.findViewById<TextView>(R.id.tvName)
                    ?.text = ((binding.mainRecyclerview.adapter as MainListAdapter).getItem()[headerPosition] as MainCategory).str
            }
        }

        override fun isHeader(itemPosition: Int): Boolean {
            return if(itemPosition != RecyclerView.NO_POSITION){
                (binding.mainRecyclerview.adapter as MainListAdapter).getItemViewType(itemPosition) == BFMainListViewType.Category
            } else {
                false
            }
        }
    }
}