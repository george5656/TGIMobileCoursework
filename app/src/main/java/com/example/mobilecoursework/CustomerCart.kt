package com.example.mobilecoursework

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.CartItem
import com.example.mobilecoursework.model.CustomerCartItemAdapter
import com.example.mobilecoursework.model.CustomerMenuItemAdapter
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.ShoppingCart
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CustomerCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_cart)
    }
    val shoppingCart = ShoppingCart.getInstance()
    fun goBack(view: View){
        var goBack: Intent = Intent(this, CustomerHomePage::class.java)
        goBack.putExtra("cusid", cusid)
        startActivity(goBack)
    }

    var lv: ListView? = null
    var selectedItem: CartItem? = null
    var total: TextView? = null
    var cusid: String? = null

    override fun onStart() {
        super.onStart()
        var cartlist = shoppingCart.getItems()
        lv = findViewById<ListView>(R.id.lvCartItems)
        total = findViewById<TextView>(R.id.txtTotalPrice)
        cusid = intent!!.getStringExtra("cusid")


        var arrayAdapter = CustomerCartItemAdapter(this, cartlist)
        lv!!.adapter = arrayAdapter
        total!!.text = shoppingCart.getTotalCost().toString()


        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            selectedItem = lv!!.getItemAtPosition(i) as? CartItem;

        }


        lv!!.setOnItemClickListener(onclick)
    }
        fun removeItem(view: View){
            shoppingCart.removeItem(selectedItem!!.productId)
            var arrayAdapter = CustomerCartItemAdapter(this, shoppingCart.getItems())
            lv!!.adapter = arrayAdapter
            total!!.text = shoppingCart.getTotalCost().toString()
        }

    fun placeOrder(view: View){
        val cusId = cusid!!.toInt()
        val orderDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
        val orderTime = SimpleDateFormat("HHmm", Locale.getDefault()).format(Date())
        val orderStatus = "preparing"

        val contentValues = ContentValues().apply{
            put("cusId", cusId)
            put("orderDate", orderDate)
            put("orderTime", orderTime)
            put("orderStatus", orderStatus)
        }
        var db = DatabaseHelper(this)
        db.createOrder(contentValues)

    }

}