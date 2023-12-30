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
import com.example.mobilecoursework.model.InputValdiation

class AdminMenuItemFilter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu_item_filter)
    }


    fun applyButton(view: View) {
        var validation = InputValdiation()
        var error = findViewById<TextView>(R.id.txtMenuFilterError)
        var maxPrice = findViewById<EditText>(R.id.etMaxPrice).text.toString()
        var minPrice = findViewById<EditText>(R.id.etMinPrice).text.toString()
        var itemStock: String = ""
        var image: String = ""
        var errorMessage: String = ""
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

        errorMessage = validation.priceValidaiton(maxPrice)
        if (errorMessage != "") {
            errorMessage = "max price" + errorMessage
        } else {
            errorMessage = validation.priceValidaiton(minPrice)
            if (errorMessage != "") {
                errorMessage = "min price" + errorMessage
            }
        }
        if (errorMessage != "") {
            error.isVisible = true
            error.text = errorMessage
        } else {
            var menuIntent: Intent = Intent(this, AdminCafeMenu::class.java)
            menuIntent.putExtra("maxPrice", maxPrice)
            menuIntent.putExtra("minPrice", minPrice)
            menuIntent.putExtra("image", image)
            menuIntent.putExtra("inStock", itemStock)
            menuIntent.putExtra("from", "filter")
            startActivity(menuIntent)
        }
    }

    fun back(view: View) {
        var menuIntent: Intent = Intent(this, AdminCafeMenu::class.java)
        startActivity(menuIntent)
    }

}