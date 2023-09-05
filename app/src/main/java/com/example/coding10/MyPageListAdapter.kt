package com.example.coding10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.ItemMypageBinding

class MyPageListAdapter : RecyclerView.Adapter<MyPageListAdapter.ViewHolder>() {

    private val list = ArrayList<MyPageItems>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMypageBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemMypageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyPageItems) = with(binding) {
            itemTvTest2.text = item.text
        }
    }

    fun addItems(items: List<MyPageItems>) {
        list.addAll(items)
        notifyDataSetChanged()
    }
}