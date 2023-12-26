package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton

class AdminNotificationFilter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_notification_filter)
    }

fun applyButton(view:View){
var accountStatus :String = ""

if (findViewById<RadioButton>(R.id.rbNotificationFilterYes).isChecked){
    accountStatus = "yes"
}else if(findViewById<RadioButton>(R.id.rbNotificationFilterNO).isChecked){
    accountStatus = "no"
}
var lob = findViewById<EditText>(R.id.etBeforeDate).text.toString()
var loa = findViewById<EditText>(R.id.etAfterDate).text.toString()
    var notificationIntent:Intent = Intent(this,AdminSendNotification::class.java)
    notificationIntent.putExtra("from","filter")
    notificationIntent.putExtra("status",accountStatus)
    notificationIntent.putExtra("lob",lob)
    notificationIntent.putExtra("loa",loa)
    startActivity(notificationIntent)

}
    fun back(view:View){
        var notificationIntent:Intent = Intent(this,AdminSendNotification::class.java)
        startActivity(notificationIntent)
    }
}