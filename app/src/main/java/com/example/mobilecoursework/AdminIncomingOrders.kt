package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.AdminIncomingOrderAdapter
import com.example.mobilecoursework.model.AdminUserUserNameList
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.Order

class AdminIncomingOrders : AppCompatActivity() {


    var selectedItem: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_incoming_orders)
    }

    override fun onStart() {
        super.onStart()

        var db = DatabaseHelper(this)
        var list = findViewById<ListView>(R.id.lvOrders)
        var status = intent.getStringExtra("status")
        var afterDate = intent.getStringExtra("afterDate")
        var beforeDate = intent.getStringExtra("beforeDate")
        var beforeTime = intent.getStringExtra("beforeTime")
        var afterTime = intent.getStringExtra("afterTime")
        var whereClause = ""
        var adapter = AdminIncomingOrderAdapter(this,createData(db.getOrders()))
        if (intent.getStringExtra("from")=="filter") {
            if (status != "") {
                whereClause = whereClause + "\"orderStatus\" == \"" + status + "\" AND "
            } else if (afterDate != "") {
                whereClause = whereClause + "\"orderData\" >= \"" + afterDate + "\" AND "
            } else if (beforeDate != "") {
                whereClause = whereClause + "\"orderData\" <= \"" + beforeDate + "\" AND "
            } else if (beforeTime != "") {
                whereClause = whereClause + "\"orderTime\" <= \"" + beforeTime + "\" AND "
            } else if (afterTime != "") {
                whereClause = whereClause + "\"orderTime\" >= \"" + afterTime + "\" AND "
            }
            if (whereClause != "") {
                whereClause = whereClause.subSequence(0, whereClause.length - 4).toString() + ";"
                adapter = AdminIncomingOrderAdapter(this, createData(db.getOrdersThatMatchWhere(whereClause))
                )
            }

        }
            list!!.adapter = adapter


            /*  as the drag and drop doesn't have the onclick without crashing, had to use another
            event litininer,
            basically its a lambda function, going in to a OnitemClickListener, interface so
            event listener can take it as no first class functions.

         */
            var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l ->
                selectedItem = list.getItemAtPosition(i) as Order
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
        if (selectedItem != null ){
            var orderStatusPage : Intent = Intent(this, AdminEditOrderStatus::class.java)

            orderStatusPage.putExtra("orderId",selectedItem!!.orderId.toString())
            startActivity(orderStatusPage)
        }else{
            var errorMessage = findViewById<TextView>(R.id.txtErrorMessageIncomingOrders)
            errorMessage.isVisible = true
            errorMessage.text = "no data selected"
        }

    }

    // used as the eventhandler, for when
    fun message(view:View){
        var list = findViewById<ListView>(R.id.lvOrders)
        if (selectedItem != null ){
            var selectedItems: ArrayList<String> = ArrayList()
            selectedItems.add(selectedItem!!.userName)
            var messagePage : Intent = Intent(this, AdminSendPromotions::class.java)
            messagePage.putExtra("from", "sendToChosen")
            messagePage.putExtra("return","orders")
            messagePage.putExtra("selected", selectedItems)
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
            do {
                var db = DatabaseHelper(this)
                var user = db.getSpecificUser(cursor.getInt(1).toString())
                user.moveToFirst()
                order = Order(
                    cursor.getInt(0),
                    user.getString(4),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                orderItems.add(order)
            }while(cursor.moveToNext())
            }
        return orderItems
    }
fun findButton(view: View){
    var userInput = findViewById<EditText>(R.id.etOrderName).text.toString()
    if(userInput!="") {
        var db = DatabaseHelper(this)
        var list = findViewById<ListView>(R.id.lvOrders)
        var data = db.getSpecificOrders(userInput)
        var adapter = AdminIncomingOrderAdapter(this, createData(data))
        list!!.adapter = adapter


    }else{
        var Error = findViewById<TextView>(R.id.txtErrorMessageIncomingOrders)
        Error.isVisible = true
        Error.text = "no inputted date"
    }
}
}