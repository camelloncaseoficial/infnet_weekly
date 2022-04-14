package com.camelloncase.infnetweekly.adapter.viewholder

import android.graphics.Color
import android.text.TextUtils.isEmpty
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.infnetweekly.databinding.NotificationItemBinding
import com.camelloncase.infnetweekly.model.Notification

class NotificationViewHolder (private var binding: NotificationItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(notification: Notification) {
        binding.dayTextView.text = notification.day
        when (notification.observations.isEmpty()) {
            true -> binding.observationsTextView.text = "Observações..."
            else -> binding.observationsTextView.text = notification.observations
        }

        when (notification.schoolDay) {
            "NR" -> binding.schoolDayTextView.text = "Normal"
            "RE" -> {
                binding.schoolDayTextView.text = "Recesso"
                binding.notificationCardView.setCardBackgroundColor(Color.rgb(244,204,204))
            }
            "VA" -> {
                binding.schoolDayTextView.text = "Férias"
                binding.notificationCardView.setCardBackgroundColor(Color.rgb(234,153,153))
            }
            "HO" -> {
                binding.schoolDayTextView.text = "Feriado"
                binding.notificationCardView.setCardBackgroundColor(Color.rgb(234,153,153))
            }
        }

        when(notification.course) {
            "GR" -> binding.courseTextView.text = "Graduação"
            "PG" -> binding.courseTextView.text = "Pós Graduação"
            "MI" -> binding.courseTextView.text = "MIT"
            "MB" -> binding.courseTextView.text = "MBA"
        }
    }

}

