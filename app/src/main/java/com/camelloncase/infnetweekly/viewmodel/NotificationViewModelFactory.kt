package com.camelloncase.infnetweekly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.camelloncase.infnetweekly.repository.NotificationApiRepository

class NotificationViewModelFactory(
    private val repository: NotificationApiRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationViewModel(repository) as T
    }
}