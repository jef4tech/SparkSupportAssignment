package com.jef4tech.sparksupportassignment.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserData(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("password2")
    val password2: String,
    @SerializedName("username")
    val username: String
)