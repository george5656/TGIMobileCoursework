package com.example.mobilecoursework

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.Hash
import com.example.mobilecoursework.model.InputValdiation

class CustomerAccountRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_account_register)
    }

    fun back(view: View) {
        var homeIntent: Intent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }

    fun clear(view: View) {
        var userName = findViewById<EditText>(R.id.etCusUsernameR)
        var password = findViewById<EditText>(R.id.etCusPasswordR)
        var fullName = findViewById<EditText>(R.id.etCusFullnameR)
        var email = findViewById<EditText>(R.id.etCusEmailR)
        var phoneNo = findViewById<EditText>(R.id.etCusPhoneR)
        var error = findViewById<TextView>(R.id.txtCusErrorR)

        userName.text.clear()
        password.text.clear()
        fullName.text.clear()
        email.text.clear()
        phoneNo.text.clear()
        error.isVisible = false
        error.text = ""
    }

    fun register(view: View) {
        var db = DatabaseHelper(this)
        var userName = findViewById<EditText>(R.id.etCusUsernameR)
        var password = findViewById<EditText>(R.id.etCusPasswordR)
        var fullName = findViewById<EditText>(R.id.etCusFullnameR)
        var email = findViewById<EditText>(R.id.etCusEmailR)
        var phoneNo = findViewById<EditText>(R.id.etCusPhoneR)
        var error = findViewById<TextView>(R.id.txtCusErrorR)
        var validation = InputValdiation()
        var errorMessage = ""
        var existingUsers = db.getSpecificCustomer(userName.text.toString())

        if (existingUsers.moveToFirst() && userName.text.toString() != "") {
            errorMessage = "username all ready taken"

        } else if (validation.stringValidaiton(fullName!!.text.toString()) != "") {
            errorMessage = "full name" + validation.stringValidaiton(fullName.text.toString())

        } else if (validation.stringValidaiton(userName!!.text.toString()) != "") {
            errorMessage = "username" + validation.stringValidaiton(userName.text.toString())

        } else if (validation.stringValidaiton(password!!.text.toString()) != "") {
            errorMessage = "password" + validation.stringValidaiton(password.text.toString())

        } else if (validation.phoneNumberValidation(phoneNo!!.text.toString()) != "") {
            errorMessage = "phone number" + validation.phoneNumberValidation(phoneNo.text.toString())

        } else if (validation.emailValidation(email!!.text.toString()) != "") {
            errorMessage = "email" + validation.emailValidation(email.text.toString())

        }

        if (errorMessage == "") {
            var pass = Hash().hashMessage(password!!.text.toString())
            var cv = ContentValues()
            cv.put("cusFullName", fullName!!.text.toString())
            cv.put("cusEmail", email!!.text.toString())
            cv.put("cusPhoneNo", phoneNo!!.text.toString())
            cv.put("cusUserName", userName!!.text.toString())
            cv.put("cusPassword", pass)
            cv.put("cusIsActive", 1)
            db.createCustomer(cv)

            var logIn: Intent = Intent(this, CustomerLogin::class.java)
            startActivity(logIn)

        } else {
            error.isVisible = true
            error.text = errorMessage
        }

    }
}