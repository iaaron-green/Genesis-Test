package com.sverbusoft.genesis_test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sverbusoft.genesis_test.domain.repos.model.ReposModel
import com.sverbusoft.genesis_test.databinding.ItemReposBinding

class ReposAdapter(private val listener: ItemClickListener) :
    PagedListAdapter<ReposModel, ReposAdapter.ViewHolder>(
        diffCallbak
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(View.OnClickListener {
                listener.onItemClick(item)
            }, item)
        }
    }

    companion object {
        var diffCallbak: DiffUtil.ItemCallback<ReposModel> =
            object : DiffUtil.ItemCallback<ReposModel>() {
                override fun areItemsTheSame(oldItem: ReposModel, newItem: ReposModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: ReposModel, newItem: ReposModel): Boolean {
                    return oldItem.name == newItem.name && oldItem.url == newItem.url
                }

            }
    }

    class ViewHolder(private val binding: ItemReposBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: ReposModel) {
            binding.apply {
                clickListener = listener
                model = item
            }
                binding.ivFavorite.isChecked = item.favorite
                binding.ivFavorite.isEnabled = !item.favorite
                binding.ivFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) buttonView.isEnabled = false
                }
        }
    }

    interface ItemClickListener {
        fun onItemClick(repos: ReposModel)
    }
}