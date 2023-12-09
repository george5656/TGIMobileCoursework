package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminAddCafeItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_cafe_item)
    }
    fun imageLoader(view : View){
        var fileChoserIntent : Intent = Intent()
        fileChoserIntent.setType("image/*")
        fileChoserIntent.setAction(Intent.ACTION_GET_CONTENT)
         startActivity(fileChoserIntent)
    }

}