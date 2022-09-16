package com.dagger.hilt.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class People(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("avatar")
    var avatar: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("jobtitle")
    var jobTitle: String? = "",
    @SerializedName("favouriteColor")
    var favouriteColor: String? = "",
    @SerializedName("createdAt")
    var createdAt: String? = ""
) : Parcelable
