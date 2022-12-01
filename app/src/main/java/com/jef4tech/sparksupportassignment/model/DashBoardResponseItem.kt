package com.jef4tech.sparksupportassignment.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DashBoardResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_link")
    val imageLink: String
)