package com.camelloncase.infnetweekly.repository

import com.camelloncase.infnetweekly.api.RetrofitInstance
import com.camelloncase.infnetweekly.model.Notification
import retrofit2.Response
import java.util.*

class NotificationApiRepository {

    suspend fun getNotification(): Response<Notification> {
        return RetrofitInstance.api.getNotification()
    }

    suspend fun getNotificationById(uuid: UUID): Response<Notification> {
        return RetrofitInstance.api.getNotificationById(uuid)
    }

    suspend fun getAllNotificationByYear(year: Int, sort: String, order: String): Response<List<Notification>> {
        return RetrofitInstance.api.getAllNotificationByYear(year, sort, order)
    }

    suspend fun getAllNotificationByQueryMap(year: Int, options: Map<String, String>): Response<List<Notification>> {
        return RetrofitInstance.api.getAllNotificationByQueryMap(year, options)
    }

}