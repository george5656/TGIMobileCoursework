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
import com.example.mobilecoursework.model.InputValdiation

class AdminNotificationFilter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_notification_filter)
    }

    fun applyButton(view: View) {
        var accountStatus: String = ""

        if (findViewById<RadioButton>(R.id.rbNotificationFilterYes).isChecked) {
            accountStatus = "yes"
        } else if (findViewById<RadioButton>(R.id.rbNotificationFilterNO).isChecked) {
            accountStatus = "no"
        }
        var lob = findViewById<EditText>(R.id.etBeforeDate).text.toString()
        var loa = findViewById<EditText>(R.id.etAfterDate).text.toString()
        var notificationIntent: Intent = Intent(this, AdminListCustomers::class.java)
        var errorMessage = ""
        var validation = InputValdiation()
        var errorMessageLob = validation.dateValdiation(lob)
        var errorMessageLoa = validation.dateValdiation(loa)
        var format = StringFormat()
        if (errorMessageLob != "") {
            errorMessage = "last order before " + errorMessageLob
        } else if (errorMessageLoa != "") {
            errorMessage = "last order after " + errorMessageLoa
        }
        if (errorMessage == "") {
            notificationIntent.putExtra("from", "filter")
            notificationIntent.putExtra("status", accountStatus)
            notificationIntent.putExtra("lob", format.DateFormat(lob))
            notificationIntent.putExtra("loa", format.DateFormat(loa))
            startActivity(notificationIntent)
        } else {
            var error = findViewById<TextView>(R.id.txtNotificationFilterError)
            error.isVisible = true
            error.text = errorMessage
        }
    }

    fun back(view: View) {
        var notificationIntent: Intent = Intent(this, AdminListCustomers::class.java)
        startActivity(notificationIntent)
    }
}