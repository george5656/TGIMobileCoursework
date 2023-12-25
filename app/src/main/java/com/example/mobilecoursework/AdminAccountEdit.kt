package com.example.mobilecoursework

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.example.mobilecoursework.model.DatabaseHelper
import java.security.MessageDigest

class AdminAccountEdit : AppCompatActivity() {

    var accountId :String? = null
    var db :DatabaseHelper? = null
    var userName : EditText? = null
    var password : EditText? = null
    var fullName : EditText? = null
    var email : EditText? = null
    var phoneNo : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_account_edit)
    }

    override fun onStart() {
        super.onStart()

         accountId = intent.getStringExtra("adminId")
         db = DatabaseHelper(this)
         userName = findViewById<EditText>(R.id.etuserNameEdit)
         password = findViewById<EditText>(R.id.etPasswordEdit)
         fullName = findViewById<EditText>(R.id.etFullNameEdit)
         email = findViewById<EditText>(R.id.etEmailEdit)
         phoneNo = findViewById<EditText>(R.id.etPhoneEdit)
        userName!!.text.clear()
        password!!.text.clear()
        fullName!!.text.clear()
        email!!.text.clear()
        phoneNo!!.text.clear()

        var details = db!!.getAdminDetails(accountId)

        details.moveToFirst()

            userName!!.text.append(details.getString(4).toString())
            password!!.text.append(details.getBlob(5).toString())
            fullName!!.text.append(details.getString(2).toString())
            email!!.text.append(details.getString(2).toString())
            phoneNo!!.text.append(details.getString(3).toString())
            if (details.getInt(6) == 1) {
                findViewById<RadioButton>(R.id.rbYesAdminAccountEdit).isChecked = true
            } else {
                findViewById<RadioButton>(R.id.rbNoAdminAccountEdit).isChecked = true
            }







    }
    fun backButton(view: View) {
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
    }

fun saveButton(view: View){
    var md = MessageDigest.getInstance("MD5")
    md.update(password!!.text.toString().toByteArray())
    var hash = md.digest()
    /* basically a a string buffer, so can get the array data one at a time, else it will
      just keep changing the hash every time it is ran

     */
    var outPutString = StringBuilder()
    for (data in hash) {
        outPutString.append(data.toString())
    }
    var cv = ContentValues()
    cv.put("adminFullName",fullName!!.text.toString())
    cv.put("adminEmail",email!!.text.toString())
    cv.put("adminPhoneNo",phoneNo!!.text.toString())
    cv.put("adminUserName",userName!!.text.toString())
    cv.put("adminPassword",outPutString.toString())
    db = DatabaseHelper(this)
    db!!.updateAdminAccount(cv,accountId.toString())
}

}