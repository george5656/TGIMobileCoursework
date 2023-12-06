package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
    }

    fun login(view: View){
        var loginIntent : Intent = Intent(this, AdminHomePage::class.java)
startActivity(loginIntent)
    }
    fun cancel(view: View){
        var cancelIntent : Intent = Intent(this, MainActivity::class.java)
    }



}