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
import com.example.mobilecoursework.model.Hash
import java.security.MessageDigest

class AdminLogin : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
    }

    fun login(view: View) {
        var db: DatabaseHelper = DatabaseHelper(this)
        var error = findViewById<TextView>(R.id.txtErrorMessage)
        var username: String = "" + findViewById<EditText>(R.id.etUserName).text.toString()
        var password: String = "" + findViewById<EditText>(R.id.etPassword).text.toString()
        var errorMessage = ""
        if(username==""){
            errorMessage = "missing user name"
        }else if(username.length >= 50) {
            errorMessage = " user name is to big"
        }
        if(password ==""){
            errorMessage = "missing password"
        }else if(password.length >= 50) {
            errorMessage = "password is to big"
        }

        if(errorMessage==""){
            var results: Cursor? = db.getLoginDetails(username)
            var hash = Hash()
            var outPutString = hash.hashMessage(password.toString())



            if (results != null) {
                if (results.moveToFirst()) {
                    do {
                        var comparison = results.getString(5)
                        if (outPutString.toString().equals(comparison)) {
                            //  if(password.equals(results.getString(5))){
                            //as sending to home page will need another one to send message to the activity want to use
                            var loginIntent: Intent =
                                Intent(this, AdminHomePage::class.java).apply {
                                    putExtra("logedInId", results.getInt(0).toString())
                                }

                            startActivity(loginIntent)
                        }else{
                            errorMessage = "password didn't match username"
                        }
                    } while (results.moveToNext())


                }else{
                    errorMessage = "no matching username"
                }




        }


        }
            error.isVisible = true
            error.text = errorMessage

    }

    fun cancel(view: View){
        var cancelIntent : Intent = Intent(this, MainActivity::class.java)
        startActivity((cancelIntent))
    }



}