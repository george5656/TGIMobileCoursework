package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.example.mobilecoursework.model.DatabaseHelper
import java.security.MessageDigest

class AdminRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_register)
    }

fun back(view:View) {
    var adminHomePage: Intent = Intent(this, AdminHomePage::class.java)
    startActivity(adminHomePage)
}
    fun create(view:View){

        //var userName : String = findViewById<EditText>(R.id.etUserNameCreate).text.toString()
        //var password : String = MessageDigest.getInstance(findViewById<EditText>(R.id.etPasswordCreate).text.toString(),"MD5").toString()
        //var fullname : String = findViewById<EditText>(R.id.etFullNameCreate).text.toString()
        //var phonenumber : String = findViewById<EditText>(R.id.etPhoneCreate).text.toString()
        //var email : String = findViewById<EditText>(R.id.etEmailCreate).text.toString()
        //var active: Int
        var userName : String = "test"
       var password : String = "test"//MessageDigest.getInstance("Test","SHA-256").toString()
       var fullname : String = "sir random message"
       var phonenumber : String = "000000000"
       var email : String = "test"
       var active: Int

        if (findViewById<RadioButton>(R.id.rbYesActiveCreate).isChecked()){
            active = 1
        }else{
            active = 0
        }

        var db: DatabaseHelper = DatabaseHelper(this)
      var output:Int=  db.createAdmin(userName,password,fullname,phonenumber,email,active).toInt()
        if(output!=-1) {
            var adminHomePage: Intent = Intent(this, AdminHomePage::class.java)
            startActivity(adminHomePage)
        }


    }

}