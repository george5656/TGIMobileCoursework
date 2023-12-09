package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminSendNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_send_notification)
    }

    fun loadNotificationCreator(view: View){
        var notifcationMakerLoad: Intent = Intent(this, AdminSendPromotions::class.java)
        startActivity(notifcationMakerLoad)
    }
}