package com.example.coding10

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.ItemMainBinding

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    interface ItemClick{
        fun onClick(position: Int)
    }

    var itemClick: ItemClick? = null
    private val list = ArrayList<MainItems>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClick?.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainItems) {
            binding.item = item
        }
    }

    fun addItems(items: List<MainItems>){
        list.addAll(items)
        notifyDataSetChanged()
    }
}