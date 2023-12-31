package com.example.mobilecoursework

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.InputValdiation
import java.util.ArrayList

class AdminSendNotification : AppCompatActivity() {
    var from: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_send_notifications)
    }

    override fun onStart() {
        super.onStart()
        from = intent.getStringExtra("from")
        //var arrayOfString:ArrayList<String> = intent.getSerializableExtra("selected") as ArrayList<String>
        // Toast.makeText(this, arrayOfString.get(0), Toast.LENGTH_SHORT).show()
    }

    fun sendButton(view: View) {
        var db = DatabaseHelper(this)
        var messageTitle = findViewById<EditText>(R.id.etNotficationTitle).text.toString()
        var messageBody = findViewById<EditText>(R.id.etNotficationBody).text.toString()

        var counter = 0;
        var specificCustomer: Int = 0
        var notificationIntent: Intent
        var cv = ContentValues()
        var validation = InputValdiation()
        var errorMessage = ""
        var titleError = validation.stringMessageValidaiton(messageTitle)
        var bodyError = validation.stringMessageValidaiton(messageBody)

        if (titleError != "") {
            errorMessage = "title " + titleError
        } else if (bodyError != "") {
            errorMessage = "body " + bodyError
        }

        if (errorMessage == "") {

            if (from == "sendToAll") {
                var allCustomers = db.getAllCustomer()
                if (allCustomers.moveToFirst()) {
                    do {
                        cv.put("title", messageTitle)
                        cv.put("messeage", messageBody)
                        cv.put("sent", 0)
                        cv.put("cusId", allCustomers.getInt(0).toInt())
                        db.createNotification(cv)
                    } while (allCustomers.moveToNext())
                }
            } else if (from == "sendToChosen") {
                var arrayOfString: ArrayList<String>? =
                    intent.getSerializableExtra("selected") as ArrayList<String>
                while (arrayOfString!!.size != counter) {
                    var selectedCustomer =
                        db.getCustomerThatMatchesUserName(arrayOfString.get(counter))
                    selectedCustomer.moveToFirst()

                    specificCustomer = selectedCustomer.getInt(0)
                    cv.put("title", messageTitle)
                    cv.put("messeage", messageBody)
                    cv.put("sent", 0)
                    cv.put("cusId", specificCustomer)
                    db.createNotification(cv)
                    counter = counter + 1
                }
            }
            if (intent.getStringExtra("return") == "orders") {
                notificationIntent = Intent(this, AdminIncomingOrders::class.java)

            } else {
                notificationIntent = Intent(this, AdminListCustomers::class.java)

                counter = 0
            }
            startActivity(notificationIntent)

        } else {
            var error = findViewById<TextView>(R.id.txtNotificcationError)
            error.isVisible = true
            error.text = errorMessage
        }

    }

    fun back(view: View) {
        var origins = intent.getStringExtra("origins")
        var loadHome: Intent = Intent(this, AdminHomePage::class.java)
        if (origins == "io") {
            loadHome = Intent(this, AdminIncomingOrders::class.java)
        } else if (origins == "sn") {
            loadHome = Intent(this, AdminListCustomers::class.java)
        }
        startActivity(loadHome)
    }
}
