package com.jef4tech.sparksupportassignment

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.jef4tech.sparksupportassignment.databinding.ActivityMainBinding
import com.jef4tech.sparksupportassignment.model.LoginResponse
import com.jef4tech.sparksupportassignment.utils.Extensions
import com.jef4tech.sparksupportassignment.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "user"
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if (sharedPref.getBoolean("loggedstate",false)){
            val homeIntent = Intent(this, DashBoardActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
        else{
            setContentView(binding.root)
        }
//        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.login.setOnClickListener{
            login()
        }
        binding.signup.setOnClickListener {
            goSignIn()
        }

        loginViewModel.userData.observe(this, androidx.lifecycle.Observer {

            if(it!=null){
                saveData(it)
            }
        })
        loginViewModel.errorMessage.observe(this, androidx.lifecycle.Observer { it ->

            if(it!=null){
                Extensions.alertDialog(this,it,"ERROR MESSAGE")
            }
        })
    }

    private fun login() {
        val userName = binding.edUserName.editText?.text.toString().trim()
        val pwd = binding.edPassword.editText?.text.toString().trim()
        loginViewModel.loginUser(userName,pwd)
    }
    private fun saveData(body: LoginResponse?) {
        val editor=sharedPref.edit()
        editor.putBoolean("loggedstate",true)
        editor.putString("firstname", body?.firstname)
        editor.putString("lastname",body?.lastname)
        editor.putString("username",body?.username)
        editor.putString("email",body?.email)
        editor.putString("access",body?.access)
        editor.putString("refresh",body?.refresh)
        editor.apply()
        val homeIntent = Intent(this, DashBoardActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
    private fun goSignIn(){
        val homeIntent = Intent(this, RegisterActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
}