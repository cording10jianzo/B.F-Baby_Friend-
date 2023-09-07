package com.example.coding10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.ItemMainBinding
import com.example.coding10.databinding.ItemMainCategoryBinding


class MainListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick {
        fun onClick(position: Int)
        fun onLongClick(position: Int)
    }

    var itemClick: ItemClick? = null
    private val list = ArrayList<CommonItems>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BFMainListViewType.Category -> {
                MainCategoryViewHolder(
                    ItemMainCategoryBinding.inflate(LayoutInflater.from(parent.context))
                )
            }
            else -> {
                MainItemViewHolder(
                    ItemMainBinding.inflate(LayoutInflater.from(parent.context))
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]

        when (holder) {
            is MainCategoryViewHolder -> {
                holder.bind(item)
            }
            else -> {
                (holder as MainItemViewHolder).bind(item)
                holder.itemView.setOnClickListener {
                    itemClick?.onClick(position)
                }
                holder.itemView.setOnLongClickListener {
                    itemClick?.onLongClick(position)
                    true
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is MainCategory -> {
                BFMainListViewType.Category
            }
            else -> {
                BFMainListViewType.Item
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItem(): ArrayList<CommonItems> {
        return list
    }

    class MainItemViewHolder(val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommonItems) {
            binding.item = item as MainItems
        }
    }

    class MainCategoryViewHolder(private val binding: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommonItems) {
            binding.item = item as MainCategory
        }
    }

    fun addItems(items: List<CommonItems>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}

object BFMainListViewType {
    const val Category = 1
    const val Item = 2
}