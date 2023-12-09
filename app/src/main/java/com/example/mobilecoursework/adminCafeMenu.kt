package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class adminCafeMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cafe_menu)
    }
    fun addItemLoad(view: View){
        var addItemIntent: Intent = Intent(this, AdminAddCafeItem::class.java)
        startActivity(addItemIntent)
    }


}