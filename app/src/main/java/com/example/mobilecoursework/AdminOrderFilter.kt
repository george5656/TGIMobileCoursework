package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton


class AdminOrderFilter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_order_filter)
    }
fun applyButton(view: View){
    var status : String = ""
    if (findViewById<RadioButton>(R.id.rbFilterCollect).isChecked){
        status =  "collect"
    } else if(findViewById<RadioButton>(R.id.rbFilterPreparing).isChecked){
        status = "preparing"
    }else if(findViewById<RadioButton>(R.id.rbFilterFinished).isChecked){
        status = "finished"
    }else if(findViewById<RadioButton>(R.id.rbFilterCancled).isChecked){
        status = "cancel"
    }
    var afterDate = findViewById<EditText>(R.id.etFilterDateAfter).text.toString()
    var beforeDate = findViewById<EditText>(R.id.etFilterDateBefore).text.toString()
    var beforeTime = findViewById<EditText>(R.id.etFilterBeforeTime).text.toString()
    var afterTime = findViewById<EditText>(R.id.etFilterAfterTime).text.toString()
    var notificationIntent: Intent = Intent(this,AdminIncomingOrders::class.java)
    notificationIntent.putExtra("status", status)
    notificationIntent.putExtra("afterDate", afterDate)
    notificationIntent.putExtra("beforeDate", beforeDate)
    notificationIntent.putExtra("beforeTime", beforeTime)
    notificationIntent.putExtra("afterTime", afterTime)
    notificationIntent.putExtra("from", "filter")
    startActivity(notificationIntent)
}
}