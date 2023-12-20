package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.AdminIncomingOrderAdapter
import com.example.mobilecoursework.model.Order

class AdminIncomingOrders : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_incoming_orders)
        var list = findViewById<ListView>(R.id.lvOrders)
        var adapter = AdminIncomingOrderAdapter(this, arrayOf(Order("test1","test2","test3","test4"),Order("testa","testb","testc","testd")))
        list!!.adapter = adapter

    }

    fun backButton(view: View){
        var backPage : Intent = Intent(this, AdminHomePage::class.java)
        startActivity((backPage))
    }

    fun message(view:View){
        var list = findViewById<ListView>(R.id.lvOrders)
        if (list.selectedItem != null ){
            var messagePage : Intent = Intent(this, AdminSendPromotions::class.java)
            startActivity(messagePage)
        }/*else{
            var errorMessage = findViewById<TextView>(R.id.txtErrorMessage)
            errorMessage.isVisible = true
            errorMessage.text = "no data selected"
        }
*/

    }

}