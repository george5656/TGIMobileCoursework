package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.example.mobilecoursework.model.CustomerMenuItemAdapter
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.ShoppingCart

class CustomerHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home_page)
    }



    fun logOff(view: View){
        var loggingOff: Intent = Intent(this, MainActivity::class.java)
        startActivity(loggingOff)
    }

    fun goToCart(view: View){
        var cart: Intent = Intent(this, CustomerCart::class.java)
        cart.putExtra("cusid",cusid)
        startActivity(cart)
    }

    var lv: ListView? = null
    var selectedItem: CafeItem? = null
    var cusid: String? = null

    override fun onStart() {
        super.onStart()
        var db = DatabaseHelper(this)
        var cursor = db.getMenuItems()
        lv = findViewById<ListView>(R.id.lvCusMenu)
        var data: ArrayList<CafeItem> = getCafeItems(cursor)
        cusid = intent!!.getStringExtra("cusid")


        var arrayAdapter = CustomerMenuItemAdapter(this, data)
        lv!!.adapter = arrayAdapter


        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            selectedItem = lv!!.getItemAtPosition(i) as? CafeItem;
            var seeItem = Intent(this, CustomerProductPage::class.java)
            seeItem.putExtra("menuItem", selectedItem!!.proId.toString())
            startActivity(seeItem)
        }


        lv!!.setOnItemClickListener(onclick)
    }

    fun getCafeItems(cursor: Cursor): ArrayList<CafeItem> {

        var menuItem = ArrayList<CafeItem>()
        var item: CafeItem
        var inStock: Boolean
        if (cursor.moveToFirst()) {
            do {
                inStock = cursor.getInt(4) == 1
                item = CafeItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getBlob(3),
                    inStock
                )
                menuItem.add(item)
            } while (cursor.moveToNext())


        }

        return menuItem
    }
}