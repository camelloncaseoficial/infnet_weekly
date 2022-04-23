package com.camelloncase.infnetweekly.repository

import com.camelloncase.infnetweekly.api.RetrofitInstance
import com.camelloncase.infnetweekly.model.Notification
import com.camelloncase.infnetweekly.util.Resource
import com.camelloncase.infnetweekly.util.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

class NotificationApiRepository {

    suspend fun getNotificationByWeek(begin: String, finish: String): Response<List<Notification>> {
        return RetrofitInstance.api.getNotificationByCurrentWeek(begin, finish)
    }

}