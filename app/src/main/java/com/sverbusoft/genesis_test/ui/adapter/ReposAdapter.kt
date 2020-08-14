package com.sverbusoft.genesis_test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sverbusoft.genesis_test.data.features.repos.model.ReposResponseItem
import com.sverbusoft.genesis_test.databinding.ItemReposBinding

class ReposAdapter(/*private val listener: onItemClickListener*/) :
    PagedListAdapter<ReposResponseItem, ReposAdapter.ViewHolder>(
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
                //listener.onItemClick(item)
            }, item)
        }
    }

    companion object {
        var diffCallbak: DiffUtil.ItemCallback<ReposResponseItem> =
            object : DiffUtil.ItemCallback<ReposResponseItem>() {
                override fun areItemsTheSame(oldItem: ReposResponseItem, newItem: ReposResponseItem): Boolean {
                    return true;
                }

                override fun areContentsTheSame(oldItem: ReposResponseItem, newItem: ReposResponseItem): Boolean {
                    return true;
                }

            }
    }

    class ViewHolder(private val binding: ItemReposBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: ReposResponseItem) {
            binding.apply {
                clickListener = listener
                model = item
                //Picasso.get().load(item.avatarUrl).into(ciUserPhoto)
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(repos: ReposResponseItem)
    }
}