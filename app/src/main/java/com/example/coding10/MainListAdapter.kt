package com.example.coding10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coding10.databinding.ItemMainBinding
import com.example.coding10.databinding.ItemMainCategoryBinding
import com.example.coding10.databinding.ItemMainTitleBinding

class MainListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick{
        fun onClick(position: Int)
    }

    var itemClick: ItemClick? = null
    private val list = ArrayList<CommonItems>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BFMainListViewType.Title -> {
                MainTitleViewHolder(
                    ItemMainTitleBinding.inflate(LayoutInflater.from(parent.context))
                )
            }
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
            is MainTitleViewHolder -> {
                holder.bind(item)
            }
            is MainCategoryViewHolder -> {
                holder.bind(item)
            }
            else -> {
                (holder as MainItemViewHolder).bind(item)
                holder.itemView.setOnClickListener {
                    itemClick?.onClick(position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is MainTitle -> {
                BFMainListViewType.Title
            }
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

    class MainItemViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommonItems) {
            binding.item = item as MainItems
        }
    }

    class MainTitleViewHolder(private val binding: ItemMainTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommonItems) {
            binding.item = item as MainTitle
        }
    }

    class MainCategoryViewHolder(private val binding: ItemMainCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommonItems) {
            binding.item = item as MainCategory
        }
    }

    fun addItems(items: List<CommonItems>){
        list.addAll(items)
        notifyDataSetChanged()
    }
}

object BFMainListViewType {
    const val Title = 0
    const val Category = 1
    const val Item = 2
}