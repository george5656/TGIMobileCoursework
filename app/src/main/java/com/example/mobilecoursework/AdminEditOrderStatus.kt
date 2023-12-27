package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.example.mobilecoursework.model.DatabaseHelper

class AdminEditOrderStatus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_edit_order_status)
    }

    override fun onStart() {
        super.onStart()
        var orderId = intent.getStringExtra("orderId")
        var preparing = findViewById<RadioButton>(R.id.rbPreparing)
        var collect = findViewById<RadioButton>(R.id.rbCollect)
        var cancel = findViewById<RadioButton>(R.id.rbCancel)
        var finished = findViewById<RadioButton>(R.id.rbFinish)
        var db = DatabaseHelper(this)
        var cursor = db.getSpecificOrderFromOrderId(orderId)
        cursor.moveToFirst()

        if (cursor.getString(4) == "preparing") {
            preparing.isChecked = true
        } else if (cursor.getString(4) == "collect") {
            collect.isChecked = true
        } else if (cursor.getString(4) == "cancel") {
            cancel.isChecked = true
        } else if (cursor.getString(4) == "finished") {
            finished.isChecked = true
        }

    }

    fun applyButton(view: View) {
        var db = DatabaseHelper(this)
        var newStatus: String = ""
        var preparing = findViewById<RadioButton>(R.id.rbPreparing)
        var collect = findViewById<RadioButton>(R.id.rbCollect)
        var cancel = findViewById<RadioButton>(R.id.rbCancel)
        var finished = findViewById<RadioButton>(R.id.rbFinish)
        var orderId = intent.getStringExtra("orderId")
        if (preparing.isChecked) {
            newStatus = "preparing"
        } else if (collect.isChecked) {
            newStatus = "collect"
        } else if (cancel.isChecked) {
            newStatus = "cancel"
        } else if (finished.isChecked) {
            newStatus = "finished"
        }
        db.updateOrderStatus(newStatus, orderId!!)
        var orderIntent: Intent = Intent(this, AdminIncomingOrders::class.java)
        startActivity(orderIntent)
    }

    fun backToIncomingOrders(view: View) {
        var orderIntent: Intent = Intent(this, AdminIncomingOrders::class.java)
        startActivity(orderIntent)
    }
}