package com.jef4tech.sparksupportassignment.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jef4tech.sparksupportassignment.model.RegisterResponse
import com.jef4tech.sparksupportassignment.model.UserData
import com.jef4tech.sparksupportassignment.network.RestApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel: ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var userData = MutableLiveData<RegisterResponse>()




    fun addUser(userData: UserData) {
        viewModelScope.launch {
            val response = RestApiImpl.registerUser(userData)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    try{
                        Log.i("japan", "loginUser: ${response.body()}")
                        this@RegisterViewModel.userData.postValue(response.body())
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