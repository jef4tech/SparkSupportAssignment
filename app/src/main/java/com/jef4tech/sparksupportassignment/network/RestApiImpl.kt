package com.jef4tech.sparksupportassignment.network

import android.util.Log
import com.jef4tech.sparksupportassignment.model.UserData


object RestApiImpl {
//    suspend fun registerUser() = RetrofitClientFactory.restApis.addUser()
    suspend fun loginUser(userName: String, pwd: String) = RetrofitClientFactory.restApis.getUser(userName,pwd)
    suspend fun registerUser(userData: UserData) = RetrofitClientFactory.restApis.addUser(userData)
    suspend fun getdashboardData(authHeader:String) = RetrofitClientFactory.restApis.getDashBoardData(authHeader)

}