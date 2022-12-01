package com.jef4tech.sparksupportassignment.network

import com.jef4tech.sparksupportassignment.model.DashBoardResponse
import com.jef4tech.sparksupportassignment.model.LoginResponse
import com.jef4tech.sparksupportassignment.model.RegisterResponse
import com.jef4tech.sparksupportassignment.model.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface RestApis {

//    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun addUser(@Body userData: UserData): Response<RegisterResponse>


    @POST("login/")
    @FormUrlEncoded
    suspend fun getUser(@Field("username") userName: String,
                         @Field("password") password: String): Response<LoginResponse>

    @GET("dashboard/")
    suspend fun getDashBoardData(@Header("Authorization") authHeader: String?): Response<DashBoardResponse>
}