package com.example.userregisteractivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userregisteractivity.databinding.ActivityMainBinding
import android.text.TextUtils
import android.util.Patterns
import androidx.core.widget.doAfterTextChanged
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateErrorStates()

        binding.saveButton.setOnClickListener {
            onSaveClicked()
        }
        binding.clearButton.setOnLongClickListener {
            clearInputs()
            true
        }
    }

    private fun onSaveClicked() {
        val email = binding.emailInput.text?.toString()
        val userName = binding.usernameInput.text?.toString()
        val firstName = binding.firstNameInput.text?.toString()
        val lastName = binding.lastNameInput.text?.toString()
        val age = binding.ageInput.text?.toString()

        if (email.isNullOrEmpty()) {
            binding.emailLayout.error = "Email Is Empty"
        } else if (!isValidEmail(email)) {
            binding.emailLayout.error = "Incorrect Email"
        }

        if (userName.isNullOrBlank()) {
            binding.usernameLayout.error = "Fill UserName"
        } else if (userName.length < 10) {
            binding.usernameLayout.error = "UserName Is Short"
        }

        if (firstName.isNullOrBlank()) {
            binding.firsNameLayout.error = "Fill First Name"
        }
        if (lastName.isNullOrBlank()) {
            binding.lastNameLayout.error = "Fill Last Name"
        }

        if (age.isNullOrBlank()) {
            binding.ageLayout.error = "Fill First Age"
        } else if (!isValidAge(age)) {
            binding.ageLayout.error = "Incorrect Age"
        }

    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidAge(age: String): Boolean {
        try {
            if (age.toInt() < 0) {
                return false
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    private fun updateErrorStates() = with(binding) {
        emailInput.doAfterTextChanged {
            emailLayout.error = null
        }
        usernameInput.doAfterTextChanged {
            usernameLayout.error = null
        }
        firstNameInput.doAfterTextChanged {
            firsNameLayout.error = null
        }
        lastNameInput.doAfterTextChanged {
            lastNameLayout.error = null
        }
        ageInput.doAfterTextChanged {
            ageLayout.error = null
        }
    }

    private fun clearInputs() = with(binding) {
        emailInput.text = null
        usernameInput.text = null
        firstNameInput.text = null
        lastNameInput.text = null
        ageInput.text = null

    }
}