package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.mobilecoursework.model.DatabaseHelper

class AdminDeleteCOnfirmation : AppCompatActivity() {
    var deleteType : String? = null
    var data: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_delete_confirmation)
    }
    override fun onStart(){
        super.onStart()
         deleteType = intent.getStringExtra("menuItem")
         data = intent.getStringExtra("menuItemToBeDeleted")
        findViewById<TextView>(R.id.txtWaring).text = "are you sure \n you want to delete\n " + intent.getStringExtra("itemName")
    }
    fun confirmButton(view: View){
    var db = DatabaseHelper(this)
        db.deleteCafeMenuItem(data)
        var menuIntent: Intent = Intent(this,adminCafeMenu::class.java)
        startActivity(menuIntent)
    }
    fun cancelButton(view:View){
        var menuIntent: Intent = Intent(this,adminCafeMenu::class.java)
        startActivity(menuIntent)
    }
}