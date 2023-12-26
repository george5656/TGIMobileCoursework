package com.example.mobilecoursework

import android.app.Application
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText

import android.widget.ListView
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.view.size
import com.example.mobilecoursework.model.AdminUserUserNameList
import com.example.mobilecoursework.model.DatabaseHelper
import android.widget.AdapterView.OnItemSelectedListener as OnItemSelectedListener

class AdminSendNotification : AppCompatActivity() {

    var selectedItems: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_send_notification)

    }

    override fun onStart() {
        super.onStart()
        selectedItems = ArrayList()
            var db = DatabaseHelper(this)
            var list = findViewById<ListView>(R.id.lvUserUsernames)
            var adapter = AdminUserUserNameList(this, getUserName(db.getAllCustomer()))
            if(intent.getStringExtra("from")=="filter"){
                var status = intent.getStringExtra("status")
                if(status == "yes"){
                    status = "1"
                }else if(status == "no"){
                    status = "0"
                }
                var lob = intent.getStringExtra("lob")
                var loa = intent.getStringExtra("loa")
                var whereClause = ""
                if(status != ""){
                    whereClause = whereClause + "Customers.cusIsActive == " + status + " AND "
                }
                if (lob !=""){
                    whereClause = whereClause + "\"Purchase.orderDate\" <= " + lob.toString().toInt() + " AND "
                }
                if (loa !=""){
                    whereClause = whereClause + "\"Purchase.orderDate\" >= " + loa + " AND "
                }
                if(whereClause!="") {
                    whereClause = whereClause.subSequence(0, whereClause.length-4).toString() + ";"
                }
                 adapter = AdminUserUserNameList(this, getUserName(db.getUserThatMatchCustomeWhere(whereClause)))
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
                var notifcationMakerLoad: Intent = Intent(this, AdminSendPromotions::class.java)
                notifcationMakerLoad.putExtra("from", "sendToChosen")
                notifcationMakerLoad.putExtra("selected", selectedItems)
                startActivity(notifcationMakerLoad)
            } else {
                var error = findViewById<TextView>(R.id.txtNotificatonError)
                error.isVisible = true
                error.text = "no items selected"
            }
        }

        fun sendToAllButton(view: View) {
            var notifcationMakerLoad: Intent = Intent(this, AdminSendPromotions::class.java)
            notifcationMakerLoad.putExtra("from", "sendToAll")
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
                    userNames.add(cursor.getString(4))
                } while (cursor.moveToNext())
            }
            return userNames
        }

        fun findButton(view: View) {
            var userInput = findViewById<EditText>(R.id.etNotficationUser).text
            if (userInput.toString() != "") {
                var db = DatabaseHelper(this)
                var list = findViewById<ListView>(R.id.lvUserUsernames)
                var adapter = AdminUserUserNameList(
                    this,
                    getUserName(db.getSpecificCustomer(userInput.toString()))
                )
                list.adapter = adapter
            }
        }



fun optionTicked (view:View){
var button = view as CheckBox
    if(selectedItems.contains(button.text.toString())){
        selectedItems.remove(button.text.toString())

    }else{
        selectedItems.add(button.text.toString())

    }



}


    }
