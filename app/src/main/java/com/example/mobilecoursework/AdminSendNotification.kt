package com.example.mobilecoursework

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.mobilecoursework.model.AdminUserUserNameList

class AdminSendNotification : AppCompatActivity() {
   var data = arrayOf("test1","test2","test3")
    var adapter = AdminUserUserNameList(this, data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_send_notification)
   var list = findViewById<ListView>(R.id.lvUserUsernames)
        list.adapter = adapter
    }

    fun loadNotificationCreator(view: View){
        var notifcationMakerLoad: Intent = Intent(this, AdminSendPromotions::class.java)
        startActivity(notifcationMakerLoad)
    }
}