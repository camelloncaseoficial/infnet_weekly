package com.camelloncase.infnetweekly.api

import retrofit2.Retrofit
import com.camelloncase.infnetweekly.util.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object RetrofitInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val api: NotificationApi by lazy {

        retrofit.create(NotificationApi::class.java)
    }
}