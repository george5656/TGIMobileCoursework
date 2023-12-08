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

    fun login(view: View) {
        var db: DatabaseHelper = DatabaseHelper(this)

        var username: String = "" + findViewById<EditText>(R.id.etUserName).text.toString()
        var password: String = "" + findViewById<EditText>(R.id.etPassword).text.toString()
        var results: Cursor? = db.getLoginDetails(username)
        if (results != null) {
            if (results.moveToFirst()) {
                do {
                    if (password.equals(results.getString(5))) {
                        var loginIntent: Intent = Intent(this, AdminHomePage::class.java)
                        startActivity(loginIntent)
                    }
                } while (results.moveToNext())


            }

        }
    }
    fun cancel(view: View){
        var cancelIntent : Intent = Intent(this, MainActivity::class.java)
        startActivity((cancelIntent))
    }



}