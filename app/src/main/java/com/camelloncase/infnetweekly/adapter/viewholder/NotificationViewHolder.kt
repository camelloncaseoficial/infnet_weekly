package com.camelloncase.infnetweekly.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.infnetweekly.databinding.NotificationItemBinding
import com.camelloncase.infnetweekly.model.Notification

class NotificationViewHolder (private var binding: NotificationItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(notification: Notification) {
        binding.weekAliasTextView.text = notification.weekAlias
        binding.observationsTextView.text = notification.weekStart
        binding.createDateTextView.text = notification.weekCreateDate
        binding.updateDateTextView.text = notification.weekUpdateDate
    }
}