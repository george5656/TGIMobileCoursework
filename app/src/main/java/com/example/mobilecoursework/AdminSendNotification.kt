package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class AdminSendNotification : AppCompatActivity() {
   var data = arrayOf("test1","test2","test3")
    var adapter = ArrayAdapter(this,R.layout.admin_user_list,R.id.txtNotficationUsername,data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_send_notification)
        //findViewById<ListView>(R.id.lvUsername).adapter= adapter
    }

    fun loadNotificationCreator(view: View){
        var notifcationMakerLoad: Intent = Intent(this, AdminSendPromotions::class.java)
        startActivity(notifcationMakerLoad)
    }
}