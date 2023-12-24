package com.example.mobilecoursework

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.mobilecoursework.model.DatabaseHelper
import java.util.ArrayList

class AdminSendPromotions : AppCompatActivity() {
    var from: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_send_promotions)
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
        var arrayOfString:ArrayList<String> = intent.getSerializableExtra("selected") as ArrayList<String>
        var counter = 0;
        var specificCustomer: Int = 0
        var notificationIntent: Intent
        var cv = ContentValues()
        if (from == "sendToAll") {
            var allCustomers = db.getAllCustomer()
            if (allCustomers.moveToFirst()) {
                do {

                    cv.put("Title", messageTitle)
                    cv.put("Messeage", messageBody)
                    cv.put("sent", 0)
                    cv.put("cusId", allCustomers.getInt(0).toInt())
                    db.createNotification(cv)
                } while (allCustomers.moveToNext())
            }


        } else if (from == "sendToChosen") {
            while (arrayOfString!!.size != counter) {
               var selectedCustomer =  db.getCustomerThatMatchesUserName(arrayOfString.get(counter))
                selectedCustomer.moveToFirst()

                specificCustomer = selectedCustomer.getInt(0)
                cv.put("Title",messageTitle)
                cv.put("Messeage",messageBody)
                cv.put("sent",0)
                cv.put("cusId",specificCustomer)
                 db.createNotification(cv)
                counter = counter + 1
            }
            if(intent.getStringExtra("return")=="orders"){
                 notificationIntent = Intent(this, AdminIncomingOrders::class.java)

            }else {
                 notificationIntent = Intent(this, AdminSendNotification::class.java)

                counter = 0
            }
            startActivity(notificationIntent)
        }

    }
}