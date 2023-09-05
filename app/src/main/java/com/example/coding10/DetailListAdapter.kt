package com.example.coding10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.ItemDetailBinding

class DetailListAdapter : RecyclerView.Adapter<DetailListAdapter.ViewHolder>() {

    private val list = ArrayList<DetailItems>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemDetailBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailItems) = with(binding) {
            itemTvTest2.text = item.text
        }
    }

    fun addItems(items: List<DetailItems>) {
        list.addAll(items)
        notifyDataSetChanged()
    }
}