package com.jef4tech.sparksupportassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jef4tech.sparksupportassignment.databinding.ActivityRegisterBinding
import com.jef4tech.sparksupportassignment.model.UserData
import com.jef4tech.sparksupportassignment.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.buttonRegister.setOnClickListener {
            val userData = UserData(email = binding.editUserMail.toString(),
                firstName = binding.editFirstName.toString(),
                lastName = binding.editLastName.toString(),
                password = binding.editPassword.toString(),
                password2 = binding.editConfirmPassword.toString(),
                username = binding.editUserName.toString(),
            )
            registerViewModel.addUser(userData)
        }


    }
    fun emailVerify(){

    }
}