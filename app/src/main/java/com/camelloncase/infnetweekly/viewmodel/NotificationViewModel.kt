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

    val apiResponse: MutableLiveData<Response<Notification>> = MutableLiveData()
    val notificationById: MutableLiveData<Response<Notification>> = MutableLiveData()
    val allNotificationsByYear: MutableLiveData<Response<List<Notification>>> = MutableLiveData()
    val allNotificationsByWeek: MutableLiveData<Response<List<Notification>>> = MutableLiveData()
    val allNotificationsByQueryMap: MutableLiveData<Response<List<Notification>>> = MutableLiveData()

    fun getNotification() {
        viewModelScope.launch {
            val response = repository.getNotification()
            apiResponse.value = response

        }
    }

    fun getNotificationById(uuid: UUID) {
        viewModelScope.launch {
            val response = repository.getNotificationById(uuid)
            notificationById.value = response
        }
    }

    fun getNotificationByWeek(begin: String, finish: String) {
        viewModelScope.launch {
            val response = repository.getNotificationByWeek(begin, finish)
            allNotificationsByWeek.value = response
        }
    }

    fun getAllNotificationByYear(year: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response = repository.getAllNotificationByYear(year, sort, order)
            allNotificationsByYear.value = response
        }
    }

    fun getAllNotificationByQueryMap(year: Int, options: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getAllNotificationByQueryMap(year, options)
            allNotificationsByQueryMap.value = response
        }
    }

}