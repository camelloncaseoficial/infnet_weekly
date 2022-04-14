package com.camelloncase.infnetweekly.api

import com.camelloncase.infnetweekly.model.Notification
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.*

interface NotificationApi {

    @GET("weeks/040db846-4aec-406c-9ab6-a9188c011746")
    suspend fun getNotification() : Response<Notification>

    @GET("weeks/{weekUUID}")
    suspend fun getNotificationById(
        @Path("weekUUID") uuid: UUID
    ) : Response<Notification>

//    ?start_date=2022-04-11&end_date=2022-04-16
    @GET("days")
    suspend fun getNotificationByCurrentWeek(
        @Query("start_date") begin: String,
        @Query("end_date") finish: String
    ) : Response<List<Notification>>

    @GET("weeks")
    suspend fun getAllNotificationByYear(
        @Query("yearNumber") number: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ) : Response<List<Notification>>

    @GET("weeks")
    suspend fun getAllNotificationByQueryMap(
        @Query("yearNumber") number: Int,
        @QueryMap options: Map<String, String>
    ) : Response<List<Notification>>

}