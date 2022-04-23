package com.camelloncase.infnetweekly.model

import com.squareup.moshi.Json


data class Notification(

    @Json(name ="id")
    val id: String,

    @Json(name ="day")
    val day: String,

    @Json(name ="school_day")
    val schoolDay: String,

    @Json(name ="observations")
    val observations: String,

    @Json(name ="regular_activity")
    val regularActivity: String,

    @Json(name ="project_activity")
    val projectActivity: String,

    @Json(name ="course")
    val course: String,

    @Json(name ="create_date")
    val createDate: String,

    @Json(name ="update_date")
    val updateDate: String? = null,
)