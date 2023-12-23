package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.AdminIncomingOrderAdapter
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.Order

class AdminIncomingOrders : AppCompatActivity() {


    var selectedItem: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_incoming_orders)
    }

    override fun onStart() {
        super.onStart()

        var db = DatabaseHelper(this)
        var list = findViewById<ListView>(R.id.lvOrders)

        var adapter = AdminIncomingOrderAdapter(this,createData(db.getOrders()))
        list!!.adapter = adapter


        /*  as the drag and drop doesn't have the onclick without crashing, had to use another
            event litininer,
            basically its a lambda function, going in to a OnitemClickListener, interface so
            event listener can take it as no first class functions.

         */
        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            selectedItem = list.getItemAtPosition(i)
        }
        list.setOnItemClickListener(onclick)

    }
    fun backButton(view: View){
        var backPage : Intent = Intent(this, AdminHomePage::class.java)
        startActivity((backPage))
    }

    fun  filterButton(view: View){
        var filterPage : Intent = Intent(this, AdminOrderFilter::class.java)
        startActivity((filterPage))
    }
    fun orderStatus(view:View){
        var orderStatusPage : Intent = Intent(this, AdminEditOrderStatus::class.java)
        startActivity((orderStatusPage))
    }

    // used as the eventhandler, for when
    fun message(view:View){
        var list = findViewById<ListView>(R.id.lvOrders)
        if (selectedItem != null ){
            var messagePage : Intent = Intent(this, AdminSendPromotions::class.java)
            startActivity(messagePage)
        }else{
            var errorMessage = findViewById<TextView>(R.id.txtErrorMessageIncomingOrders)
            errorMessage.isVisible = true
            errorMessage.text = "no data selected"
        }


    }

    fun createData(cursor:Cursor):ArrayList<Order>{
        var orderItems = ArrayList<Order>()
        var order: Order
        if(cursor.moveToFirst()){
            var db = DatabaseHelper(this)
            var user =  db.getSpecificUser(cursor.getInt(1).toString())
            user.moveToFirst()
            order = Order(user.getString(4),cursor.getInt(2),cursor.getString(3),cursor.getString(4))
            orderItems.add(order)
        }
        return orderItems
    }

}