package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.StringFormat
import com.example.mobilecoursework.model.inputValdiation


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
    var validation = inputValdiation()
    var errorMessage = ""
    var beforeDateValdiation = validation.dateValdiation(beforeDate)
    var afterDateValdiation = validation.dateValdiation(afterDate)
    var beforeTimeValdiation = validation.timeValdiaiton(beforeTime)
    var afterTimeValdiation = validation.timeValdiaiton(afterTime)
    var format = StringFormat()
    if(beforeDateValdiation!=""){
        errorMessage = "before data " + beforeDateValdiation
    } else if(afterDateValdiation!=""){
        errorMessage = "after data " + afterDateValdiation
    }else if(beforeTimeValdiation!=""){
        errorMessage = "before Time " + beforeTimeValdiation
    }else if(afterTimeValdiation!=""){
        errorMessage = "after Time " + afterTimeValdiation
    }
    if(errorMessage=="") {
        var notificationIntent: Intent = Intent(this, AdminIncomingOrders::class.java)
        notificationIntent.putExtra("status", status)
        notificationIntent.putExtra("afterDate", format.DateFormat(afterDate))
        notificationIntent.putExtra("beforeDate", format.DateFormat(beforeDate))
        notificationIntent.putExtra("beforeTime", format.timeFormat(beforeTime))
        notificationIntent.putExtra("afterTime", format.timeFormat(afterTime))
        notificationIntent.putExtra("from", "filter")
        startActivity(notificationIntent)
    } else{
        var error = findViewById<TextView>(R.id.txtOrderFilterError)
        error.isVisible = true
        error.text = errorMessage
    }
}
fun back(view:View){
    var orderIntent:Intent = Intent(this,AdminIncomingOrders::class.java)
    startActivity(orderIntent)
}
}