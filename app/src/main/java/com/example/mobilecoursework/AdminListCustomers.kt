package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText

import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.AdminUserUserNameList
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.InputValdiation

class AdminListCustomers : AppCompatActivity() {

    var selectedItems: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_list_customers)

    }

    override fun onStart() {
        super.onStart()
        selectedItems = ArrayList()
        var db = DatabaseHelper(this)
        var list = findViewById<ListView>(R.id.lvUserUsernames)
        var adapter = AdminUserUserNameList(this, getUserName(db.getAllCustomer()))
        if (intent.getStringExtra("from") == "filter") {
            var status = intent.getStringExtra("status")
            if (status == "yes") {
                status = "1"
            } else if (status == "no") {
                status = "0"
            }
            var lob = intent.getStringExtra("lob")
            var loa = intent.getStringExtra("loa")
            var whereClause = ""
            if (status != "") {
                whereClause = whereClause + "Customers.cusIsActive == " + status + " AND "
            }
            if (lob != "") {
                whereClause = whereClause + "\"Order\".orderDate < " + lob + " AND "
            }
            if (loa != "") {
                whereClause = whereClause + "\"Order\".orderDate > " + loa + " AND "
            }
            if (whereClause != "") {
                whereClause = whereClause.subSequence(0, whereClause.length - 5).toString() + ";"
                adapter = AdminUserUserNameList(
                    this,
                    getUserName(db.getUserThatMatchCustomeWhere(whereClause))
                )
            }
        }
        list.adapter = adapter

        /*var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l ->

            if(selectedItems.contains(list.getItemAtPosition(i).toString())){
            selectedItems.remove(list.getItemAtPosition(i).toString())

        }else{
            selectedItems.add(list.getItemAtPosition(i).toString())

       }
            Toast.makeText(this, "hit", Toast.LENGTH_SHORT).show()


        }

        list!!.setOnItemClickListener(onclick)
*/
    }

    fun loadNotificationCreator(view: View) {
        if (selectedItems.size != 0) {
            var notifcationMakerLoad: Intent = Intent(this, AdminSendNotification::class.java)
            notifcationMakerLoad.putExtra("from", "sendToChosen")
            notifcationMakerLoad.putExtra("selected", selectedItems)
            notifcationMakerLoad.putExtra("origins", "sn")
            startActivity(notifcationMakerLoad)
        } else {
            var error = findViewById<TextView>(R.id.txtNotificatonError)
            error.isVisible = true
            error.text = "no items selected"
        }
    }

    fun sendToAllButton(view: View) {
        var notifcationMakerLoad: Intent = Intent(this, AdminSendNotification::class.java)
        notifcationMakerLoad.putExtra("from", "sendToAll")
        notifcationMakerLoad.putExtra("origins", "sn")
        startActivity(notifcationMakerLoad)
    }

    fun backButton(view: View) {
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
    }

    fun filterButton(view: View) {
        var filterLoad: Intent = Intent(this, AdminNotificationFilter::class.java)
        startActivity(filterLoad)
    }

    fun getUserName(cursor: Cursor): ArrayList<String> {
        var userNames: ArrayList<String> = ArrayList()
        if (cursor.moveToFirst()) {
            do {
                if (!userNames.contains(cursor.getString(4))) {
                    userNames.add(cursor.getString(4))
                }
            } while (cursor.moveToNext())
        }
        return userNames
    }

    fun findButton(view: View) {
        var userInput = findViewById<EditText>(R.id.etNotficationUser).text
        var error = findViewById<TextView>(R.id.txtNotificatonError)
        var validation = InputValdiation()
        var errorMessage = validation.stringValidaiton(userInput.toString())
        if (errorMessage == "") {
            var db = DatabaseHelper(this)
            var list = findViewById<ListView>(R.id.lvUserUsernames)
            var adapter = AdminUserUserNameList(
                this,
                getUserName(db.getSpecificCustomer(userInput.toString()))
            )
            list.adapter = adapter
        } else {
            error.isVisible = true
            error.text = errorMessage
        }
    }


    fun optionTicked(view: View) {
        var button = view as CheckBox
        if (selectedItems.contains(button.text.toString())) {
            selectedItems.remove(button.text.toString())

        } else {
            selectedItems.add(button.text.toString())

        }


    }


}
