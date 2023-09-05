package com.example.coding10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.ItemMainBinding

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    private val list = ArrayList<MainItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainItems) = with(binding) {
            itemTvTest1.text = item.text
        }
    }

    fun addItems(items: List<MainItems>){
        list.addAll(items)
        notifyDataSetChanged()
    }
}