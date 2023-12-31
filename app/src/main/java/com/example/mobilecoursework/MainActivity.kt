package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun adminLogin(view: View) {
        var intent: Intent = Intent(this, AdminLogin::class.java)
        startActivity(intent)
    }

    fun customerLogin(view: View) {
        var intent: Intent = Intent(this, CustomerLogin::class.java)
        startActivity(intent)
    }

    fun customerRegister(view: View) {
        var intent: Intent = Intent(this, CustomerAccountRegister::class.java)
        startActivity(intent)
    }
}