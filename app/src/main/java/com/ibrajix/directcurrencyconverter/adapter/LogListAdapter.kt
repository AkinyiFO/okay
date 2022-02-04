package com.ibrajix.directcurrencyconverter.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibrajix.directcurrencyconverter.data.SingleLogEntity
import com.ibrajix.directcurrencyconverter.data.getFormattedPrice
import com.ibrajix.directcurrencyconverter.databinding.ListItemBinding

class LogListAdapter(private val onItemClicked: (SingleLogEntity) -> Unit) :
    ListAdapter<SingleLogEntity, LogListAdapter.LogViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        return LogViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class LogViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(singleLogEntity: SingleLogEntity) {
            binding.amount.text = singleLogEntity.getFormattedPrice()
            binding.from.text = singleLogEntity.from
            binding.to.text = singleLogEntity.to
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<SingleLogEntity>() {
            override fun areItemsTheSame(oldItem: SingleLogEntity, newItem: SingleLogEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: SingleLogEntity, newItem: SingleLogEntity): Boolean {
                return oldItem.amount == newItem.amount
            }
        }
    }
}
