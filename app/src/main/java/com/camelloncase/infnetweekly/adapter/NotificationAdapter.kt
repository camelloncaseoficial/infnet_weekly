package com.camelloncase.infnetweekly.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.infnetweekly.adapter.viewholder.NotificationViewHolder
import com.camelloncase.infnetweekly.databinding.NotificationItemBinding
import com.camelloncase.infnetweekly.model.Notification

class NotificationAdapter(var onItemClickListener: OnItemClickListener): RecyclerView.Adapter<NotificationViewHolder>()  {

    private var notificationList = emptyList<Notification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemBinding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notificationList[position]

        holder.bind(notification)

        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(notification, position)
        }
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    fun setData(newList: List<Notification>) {
        notificationList = newList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(notification: Notification, position: Int)
    }
}