package com.camelloncase.infnetweekly.api

import com.camelloncase.infnetweekly.model.Notification
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.*

interface NotificationApi {

   //    ?start_date=2022-04-11&end_date=2022-04-16
    @GET("days")
    suspend fun getNotificationByCurrentWeek(
        @Query("start_date") begin: String,
        @Query("end_date") finish: String
    ) : Response<List<Notification>>

}