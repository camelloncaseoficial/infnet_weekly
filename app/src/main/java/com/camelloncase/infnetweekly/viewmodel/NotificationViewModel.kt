package com.camelloncase.infnetweekly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camelloncase.infnetweekly.model.Notification
import com.camelloncase.infnetweekly.repository.NotificationApiRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class NotificationViewModel(private val repository: NotificationApiRepository): ViewModel() {

    val allNotificationsByWeek: MutableLiveData<Response<List<Notification>>> = MutableLiveData()

    fun getNotificationByWeek(begin: String, finish: String) {
        viewModelScope.launch {
            val response = repository.getNotificationByWeek(begin, finish)
            allNotificationsByWeek.value = response
        }
    }

}