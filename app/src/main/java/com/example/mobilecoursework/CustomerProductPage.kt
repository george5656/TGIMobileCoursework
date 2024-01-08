package com.example.mobilecoursework

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.database.getBlobOrNull
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.CartItem
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.ShoppingCart

class CustomerProductPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_product_page)
    }
    val shoppingCart = ShoppingCart.getInstance()
    var name: TextView? = null
    var price: TextView? = null
    var image: ImageView? = null
    var quantitytext: TextView? = null
    var quantity: Int = 1
    var id: Int = -1


    override fun onStart() {
        super.onStart()

        name = findViewById<EditText>(R.id.txtCusItemTitle)
        price = findViewById<EditText>(R.id.txtProductCost)
        image = findViewById<ImageView>(R.id.ivMenuPic)
        quantitytext = findViewById<TextView>(R.id.txtQuantity)

        var db = DatabaseHelper(this)
        id = intent!!.getStringExtra("menuItem")!!.toInt()
        var result = db.getMenuItemThatMatchId(id)

        result.moveToFirst();
        var inStock = result.getInt(4) == 1
        var data = CafeItem(
            result.getInt(0),
            result.getString(1),
            result.getFloat(2),
            result.getBlobOrNull(3),
            inStock
        )
        populateWidgets(data)

    }

    fun populateWidgets(data: CafeItem) {
        if (data.prodImage != null) {
            image!!.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    data.prodImage,
                    0,
                    data.prodImage!!.size
                )
            )
        }
        name!!.text = data.proName
        price!!.text = data.prodPrice.toString()

    }

    fun back(view: View){
        var back: Intent = Intent(this, CustomerHomePage::class.java)
        startActivity(back)
    }


    fun increment(view: View){
        quantity++
        quantitytext!!.text = quantity.toString()

    }

    fun decrement(view: View){
        if (quantity > 1) {
            quantity -= 1
            quantitytext!!.text = quantity.toString()
        }
    }

    fun addCart(view: View){
        shoppingCart.addItem(CartItem(id!!,name!!.text.toString(),price!!.text.toString().toFloat(),quantity!!))
    }

}