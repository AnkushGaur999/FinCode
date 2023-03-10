package com.example.fincode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fincode.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding:ActivityLoginBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun  initViews(){

        binding.loginButton.setOnClickListener {

            if(!isValidEmail(binding.userEmailET.text.toString())){
                Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }

            if(!isValidPassword(binding.userPasswordET.text.toString())){
                Toast.makeText(this, "Please enter valid Password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            startActivity(Intent(this, DashBoardActivity::class.java))
        }
    }

    private fun isValidEmail(email: String): Boolean {
      if(email.length<10){
          return false
      }
        if (!email.any { it.isUpperCase() }) {
            return false
        }
        return true
    }

    // Password validation function
    private fun isValidPassword(password: String): Boolean {
        if (password.length < 7) {
            return false
        }

        // Check if the password contains at least one uppercase letter
        if (!password.any { it.isUpperCase() }) {
            return false
        }

        // Check if the password contains at least one lowercase letter
        if (!password.any { it.isLowerCase() }) {
            return false
        }

        // Check if the password contains at least one digit
        if (!password.any { it.isDigit() }) {
            return false
        }

        // Check if the password contains at least one special character
        val specialChars = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '=')
        if (password.none { it in specialChars }) {
            return false
        }

        // If all checks pass, the password is valid
        return true
    }
}