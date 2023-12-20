package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.AdminIncomingOrderAdapter
import com.example.mobilecoursework.model.Order

class AdminIncomingOrders : AppCompatActivity() {


    var selectedItem: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_incoming_orders)
        var list = findViewById<ListView>(R.id.lvOrders)

        var adapter = AdminIncomingOrderAdapter(this, arrayOf(Order("test1","test2","test3","test4"),Order("testa","testb","testc","testd")))
        list!!.adapter = adapter


        /*  as the drag and drop doesn't have the onclick without crashing, had to use another
            event litininer,
            basically its a lambda function, going in to a OnitemClickListener, interface so
            event listener can take it as no first class functions.

         */
        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l -> selectedItem = list.getItemAtPosition(i) }
        list.setOnItemClickListener(onclick)
    }

    fun backButton(view: View){
        var backPage : Intent = Intent(this, AdminHomePage::class.java)
        startActivity((backPage))
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

}