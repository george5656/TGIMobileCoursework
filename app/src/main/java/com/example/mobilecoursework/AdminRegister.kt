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
        var errorMessage : String =""
        var userName : String = findViewById<EditText>(R.id.etUserNameCreate).text.toString()
       // var password : String = MessageDigest.getInstance(findViewById<EditText>(R.id.etPasswordCreate).text.toString(),"MD5").toString()
        var password : String = findViewById<EditText>(R.id.etPasswordCreate).text.toString()
        var fullname : String = findViewById<EditText>(R.id.etFullNameCreate).text.toString()
        var phonenumber : String = findViewById<EditText>(R.id.etPhoneCreate).text.toString()
        var email : String = findViewById<EditText>(R.id.etEmailCreate).text.toString()
        var active: Int

        if(userName == ""){
            errorMessage = "UserName is missing "
        }
        if(userName.length > 50){
            errorMessage = "userName is to long"
        }
        if(password ==""){
            errorMessage = "password is missing "
        }
        if(password.length >50){
            errorMessage = "password is to long "
        }
        if(fullname ==""){
            errorMessage = "fullname is missing "
        }
        if(fullname.length >50){
            errorMessage = "fullname is to long "
        }
        try{
            var test : Int = phonenumber.toInt()
        }catch(e: Error){
            errorMessage = "phone can only have numbers"
        }
        if(phonenumber.length < 10){
            errorMessage = "phone number is to short "
        }
        if(phonenumber.length >11){
            errorMessage = "phone number is to long "
        }
        if(email ==""){
            errorMessage = "email is missing"
        }
        if(email.length >50){
            errorMessage = "email is to long "
        }
        if(findViewById<RadioButton>(R.id.rbYesActiveCreate).isChecked() == false && findViewById<RadioButton>(R.id.rbNoActiveCreate).isChecked() == false ){
            errorMessage = "select if account is active"
        }

            if (findViewById<RadioButton>(R.id.rbYesActiveCreate).isChecked()){
            active = 1
        }else{
            active = 0
        }
        if(errorMessage =="") {
            var db: DatabaseHelper = DatabaseHelper(this)
            var output: Int =
                db.createAdmin(userName, password, fullname, phonenumber, email, active).toInt()
            if (output != -1) {
                var adminHomePage: Intent = Intent(this, AdminHomePage::class.java)
                startActivity(adminHomePage)
            }
        }else{
            var error = findViewById<TextView>(R.id.txtRegisterErrorMessage)
            error.isVisible = true
            error.text = errorMessage
        }


    }

}