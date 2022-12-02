package com.jef4tech.sparksupportassignment.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jef4tech.sparksupportassignment.model.LoginResponse
import com.jef4tech.sparksupportassignment.network.RestApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject





class LoginViewModel : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var userData = MutableLiveData<LoginResponse>()




    fun loginUser(userName: String, pwd: String) {
        viewModelScope.launch {
            val response = RestApiImpl.loginUser(userName,pwd)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    try{
                        Log.i("responseinfo", "loginUser: ${response.body()}")
                        userData.postValue(response.body())
                        loading.value = false
                    } catch (e: Exception) {
                        Log.i("Exception", "getList: ${e.message}")
                        loading.value = true
                    }
                } else {

                    onError("Error ${response.message()}")
                }
            }
        }

    }
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }


}