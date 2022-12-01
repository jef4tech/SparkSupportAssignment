package com.jef4tech.sparksupportassignment.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jef4tech.sparksupportassignment.model.DashBoardResponse
import com.jef4tech.sparksupportassignment.network.RestApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashBoardViewModel: ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var dashBoardData = MutableLiveData<DashBoardResponse>()


    fun getDashBoardData(authHeader:String) {
        viewModelScope.launch {
            val response = RestApiImpl.getdashboardData("Bearer  $authHeader")
            withContext(Dispatchers.Main) {
                Log.i("needresponse", response.toString())
                if (response.isSuccessful) {
                    try{
                        Log.i("japan", "loginUser: ${response.body()}")
                        dashBoardData.postValue(response.body())
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