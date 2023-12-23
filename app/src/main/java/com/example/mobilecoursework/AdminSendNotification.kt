package com.example.mobilecoursework

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText

import android.widget.ListView
import com.example.mobilecoursework.model.AdminUserUserNameList
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.DatabaseHelper

class AdminSendNotification : AppCompatActivity() {

var selectedItems:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_send_notification)

    }

    override fun onStart() {
        super.onStart()
        var db = DatabaseHelper(this)
        var list = findViewById<ListView>(R.id.lvUserUsernames)
        var adapter = AdminUserUserNameList(this, getUserName(db.getAllCustomer()))
        list.adapter = adapter
        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l ->

            if(selectedItems.contains(list.getItemAtPosition(i))){
                selectedItems.remove(list.getItemAtPosition(i))
            }else{
                selectedItems.add(list.getItemAtPosition(i).toString())
            }


        }
    }
    fun loadNotificationCreator(view: View){
        var notifcationMakerLoad: Intent = Intent(this, AdminSendPromotions::class.java)
        startActivity(notifcationMakerLoad)
    }

    fun sendToAllButton(view:View){
        var notifcationMakerLoad: Intent = Intent(this, AdminSendPromotions::class.java)
        startActivity(notifcationMakerLoad)
    }
    fun backButton(view:View){
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
    }
    fun filterButton(view:View){
        var filterLoad: Intent = Intent(this, AdminSendPromotions::class.java)
        startActivity(filterLoad)
    }
    fun getUserName(cursor: Cursor):ArrayList<String>{
        var userNames : ArrayList<String> = ArrayList()
        if(cursor.moveToFirst()){
            do{
                userNames.add(cursor.getString(4))
            } while(cursor.moveToNext())
        }
    return userNames
    }
fun findButton(view:View){
    var userInput = findViewById<EditText>(R.id.etNotficationUser).text
    if(userInput.toString()!=""){
        var db = DatabaseHelper(this)
        var list = findViewById<ListView>(R.id.lvUserUsernames)
        var adapter = AdminUserUserNameList(this, getUserName(db.getSpecificCustomer(userInput.toString())))
        list.adapter = adapter
    }
}
}