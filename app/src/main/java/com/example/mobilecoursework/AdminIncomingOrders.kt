package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminIncomingOrders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_incoming_orders)
    }

    fun backButton(view: View){
        var backPage : Intent = Intent(this, AdminHomePage::class.java)
        startActivity((backPage))
    }

}