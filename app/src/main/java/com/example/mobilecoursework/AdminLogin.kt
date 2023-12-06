package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.DatabaseHelper

class AdminLogin : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
    }

    fun login(view: View){

        var DBHelper : DatabaseHelper = DatabaseHelper(this)

        var Db: SQLiteDatabase = DBHelper.readableDatabase

        var query: String =  "select * from Admin"
        var output: Cursor = Db.rawQuery(query,null)
        output.moveToFirst()

        if(output.getString(5) == findViewById<EditText>(R.id.etPassword).text.toString()) {


           var loginIntent: Intent = Intent(this, AdminHomePage::class.java)
            startActivity(loginIntent)
       }
var errorMessage :TextView = findViewById(R.id.txtErrorMessage)
        errorMessage.setText("error")
        errorMessage.isVisible = true
    }
    fun cancel(view: View){
        var cancelIntent : Intent = Intent(this, MainActivity::class.java)
        startActivity((cancelIntent))
    }



}