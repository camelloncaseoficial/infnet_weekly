package com.camelloncase.infnetweekly.model

import com.google.gson.annotations.SerializedName

public class Notification(

//    @SerializedName("week_id")
//    val weekId: String,
//
//    @SerializedName("week_alias")
//    val weekAlias: String,
//
//    @SerializedName("year")
//    val year: Int,
//
//    @SerializedName("week_start")
//    val weekStart: String,
//
//    @SerializedName("week_observations")
//    val weekObservations: String,
//
//    @SerializedName("week_regular_typical_activities")
//    val weekRegularTypicalActivities: String,
//
//    @SerializedName("week_project_typical_activities")
//    val weekProjectTypicalActivities: String,
//
//    @SerializedName("week_create_date")
//    val weekCreateDate: String,
//
//    @SerializedName("week_update_date")
//    val weekUpdateDate: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("day")
    val day: String,

    @SerializedName("school_day")
    val schoolDay: String,

    @SerializedName("observations")
    val observations: String,

    @SerializedName("regular_activity")
    val regularActivity: String,

    @SerializedName("project_activity")
    val projectActivity: String,

    @SerializedName("course")
    val course: String,

    @SerializedName("create_date")
    val createDate: String,

    @SerializedName("update_date")
    val updateDate: String,
)