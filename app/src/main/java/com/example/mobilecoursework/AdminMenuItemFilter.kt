package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.DatabaseHelper

class AdminMenuItemFilter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu_item_filter)
    }


    fun applyButton(view: View) {
        var Error = findViewById<TextView>(R.id.txtMenuFilterError)
        var whereClause = ""
        var maxPrice = findViewById<EditText>(R.id.etMaxPrice).text.toString()
        var minPrice = findViewById<EditText>(R.id.etMinPrice).text.toString()
        var itemStock: String = ""
        var image: String = ""
        if (findViewById<RadioButton>(R.id.rbRemoveNoImage).isChecked) {
            image = "false"
        }
        if (findViewById<RadioButton>(R.id.rbRemoveHasImage).isChecked) {
            image = "true"
        }
        if (findViewById<RadioButton>(R.id.rbRemoveAvailable).isChecked) {
            itemStock = "true"
        }
        if (findViewById<RadioButton>(R.id.rbRemoveNotAvailable).isChecked) {
            itemStock = "false"
        }
        var db = DatabaseHelper(this)
        if (maxPrice == "" && minPrice == "" && itemStock == "") {
            Error.isVisible = true
            Error.text = "not enough options picked"
        } else {
            var menuIntent: Intent = Intent(this, adminCafeMenu::class.java)
            menuIntent.putExtra("maxPrice", maxPrice)
            menuIntent.putExtra("minPrice", minPrice)
            menuIntent.putExtra("image", image)
            menuIntent.putExtra("inStock", itemStock)
            menuIntent.putExtra("from", "filter")
            startActivity(menuIntent)
        }
    }


}