package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.Hash
import com.example.mobilecoursework.model.InputValdiation

class CustomerLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)
    }

    fun login(view: View) {
        var db = DatabaseHelper(this)
        var error = findViewById<TextView>(R.id.txtCusError)
        var username: String = findViewById<EditText>(R.id.etCusUsername).text.toString()
        var password: String = Hash().hashMessage(findViewById<EditText>(R.id.etCusPassword).text.toString())
        var errorMessage = ""

        if (errorMessage == "") {
            var results = db.getCustomerThatMatchesUserName(username)
            if (results != null) {
                if (results.moveToFirst()) {
                    do {
                        if (password.equals(results.getString(5))) {
                            var loginIntent = Intent(this, CustomerHomePage::class.java)
                            loginIntent.putExtra("cusid", results.getInt(0).toString())
                            startActivity(loginIntent)
                        } else {
                            errorMessage = "password didn't match username"
                        }
                    } while (results.moveToNext())
                } else {
                    errorMessage = "no matching username"
                }
            }
        }
        error.isVisible = true
        error.text = errorMessage

    }

    fun cancel(view: View) {
        var cancelIntent: Intent = Intent(this, MainActivity::class.java)
        startActivity(cancelIntent)
    }
}