package com.camelloncase.infnetweekly.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.camelloncase.infnetweekly.util.Constants.Companion.BASE_URL

object RetrofitInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NotificationApi by lazy {

        retrofit.create(NotificationApi::class.java)
    }
}