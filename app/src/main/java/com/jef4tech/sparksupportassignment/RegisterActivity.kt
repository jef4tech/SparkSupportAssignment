package com.jef4tech.sparksupportassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jef4tech.sparksupportassignment.databinding.ActivityRegisterBinding
import com.jef4tech.sparksupportassignment.model.UserData
import com.jef4tech.sparksupportassignment.utils.Extensions
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
//            validation()
            val userData = UserData(email = binding.editUserMail.editText?.text.toString().trim(),
                firstName = binding.editFirstName.editText?.text.toString().trim(),
                lastName = binding.editLastName.editText?.text.toString().trim(),
                password = binding.editPassword.editText?.text.toString().trim(),
                password2 = binding.editConfirmPassword.editText?.text.toString().trim(),
                username = binding.editUserName.editText?.text.toString().trim(),
            )
            registerViewModel.addUser(userData)
        }
        binding.buttonLogin.setOnClickListener {
            goLogIn()
        }
        registerViewModel.userData.observe(this, androidx.lifecycle.Observer {

            if(it!=null){
                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()
                goLogIn()
            }
        })
        registerViewModel.errorMessage.observe(this, androidx.lifecycle.Observer { it ->

            if(it!=null){
                Extensions.alertDialog(this,it,"ERROR MESSAGE")
            }
        })

    }
    private fun goLogIn(){
        val homeIntent = Intent(this, LoginActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
    private fun validation() {
        val email = binding.editUserMail.editText?.text.toString().trim()
        val firstName = binding.editFirstName.editText?.text.toString().trim()
        val lastName = binding.editLastName.editText?.text.toString().trim()
        val password = binding.editPassword.editText?.text.toString().trim()
        val password2 = binding.editConfirmPassword.editText?.text.toString().trim()
        val username = binding.editUserName.editText?.text.toString().trim()

    }
}