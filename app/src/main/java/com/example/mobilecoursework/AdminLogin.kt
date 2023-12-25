package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.DatabaseHelper
import java.security.MessageDigest

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
        var md = MessageDigest.getInstance("MD5")
        var hash = md.digest(password.toByteArray())
        var byte = md.digest()
        if (results != null) {
            if (results.moveToFirst()) {
                do {
                    if (byte.equals(results.getBlob(5) as ByteArray)) {

                       //as sending to home page will need another one to send message to the activity want to use
                        var loginIntent: Intent = Intent(this, AdminHomePage::class.java).apply{
                            putExtra("logedInId",results.getString(0).toString())
                        }

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