package com.example.mobilecoursework

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.Hash
import com.example.mobilecoursework.model.inputValdiation
import java.security.MessageDigest

class AdminAccountEdit : AppCompatActivity() {

    var accountId :String? = null
    var db :DatabaseHelper? = null
    var userName : EditText? = null
    var password : EditText? = null
    var fullName : EditText? = null
    var email : EditText? = null
    var phoneNo : EditText? = null
    var error: TextView? = null
    var from : String? = "accountEdit"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_account_edit)


    }

     override fun onStart() {
       super.onStart()

        db = DatabaseHelper(this)
        userName = findViewById<EditText>(R.id.etuserNameEdit)
        password = findViewById<EditText>(R.id.etPasswordEdit)
        fullName = findViewById<EditText>(R.id.etFullNameEdit)
        email = findViewById<EditText>(R.id.etEmailEdit)
        phoneNo = findViewById<EditText>(R.id.etPhoneEdit)
        error = findViewById<TextView>(R.id.txtAccountEditError)
        password!!.text.clear()
        fullName!!.text.clear()
        email!!.text.clear()
        phoneNo!!.text.clear()
        from = intent.getStringExtra("from")
        if(from=="accountEdit") {
            accountId = intent.getStringExtra("adminId")


            if (accountId != "") {
                userName!!.text.clear()
                var details = db!!.getAdminDetails(accountId)
                details.moveToFirst()

                userName!!.text.append(details.getString(4).toString())
                // password!!.text.append(details.getBlob(5).toString())
                fullName!!.text.append(details.getString(1).toString())
                email!!.text.append(details.getString(2).toString())
                phoneNo!!.text.append(details.getString(3).toString())
                if (details.getInt(6) == 1) {
                    findViewById<RadioButton>(R.id.rbYesAdminAccountEdit).isChecked = true
                } else {
                    findViewById<RadioButton>(R.id.rbNoAdminAccountEdit).isChecked = true
                }

            } else {
                error!!.isVisible = true
                error!!.text = "error, login required"

            }
        }else if(accountId != "createAccount"){
            userName!!.text.clear()

        }
    }
    fun backButton(view: View) {
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
    }

fun saveButton(view: View){
    db = DatabaseHelper(this)
    var hash = Hash()
    var validation = inputValdiation()
    var errorMessage = ""
     var errorMessageFullName = validation.stringValidaiton(fullName!!.text.toString())
    var errorMessageUserName = validation.stringValidaiton(userName!!.text.toString())
    var existingUsers = db!!.getLoginDetails(userName!!.text.toString())


    if(existingUsers!!.moveToFirst() && from == "createAccount"){
        errorMessageUserName  = "username all ready taken"
    }else if(existingUsers!!.moveToFirst() && from == "accountEdit" && existingUsers.getInt(0).toString() != accountId){
        errorMessageUserName  = "username all ready taken"
    }

    var errorMessagePassword = validation.stringValidaiton(password!!.text.toString())
    var errorMessagePhone = validation.phoneNumberValidation(phoneNo!!.text.toString())
    var errorMessageEmail = validation.emailValidation(email!!.text.toString())
    var active: Int = 0
    if(errorMessageFullName!="") {
        errorMessage = "full name" + errorMessageFullName
    }else if(errorMessageUserName!="") {
        errorMessage = "username" + errorMessageUserName
    }else if(errorMessagePassword!=""){
        errorMessage = "password" + errorMessagePassword
    }else if(errorMessagePhone!=""){
        errorMessage = "phone number" + errorMessagePhone
    }else if(errorMessageEmail!=""){
        errorMessage = "email" + errorMessageEmail
    }
    if (!(findViewById<RadioButton>(R.id.rbYesAdminAccountEdit).isChecked)&&!(findViewById<RadioButton>(R.id.rbNoAdminAccountEdit).isChecked)){
        errorMessage = "active not selected"
    }else if((findViewById<RadioButton>(R.id.rbYesAdminAccountEdit).isChecked)){
        active = 1
    }else if((findViewById<RadioButton>(R.id.rbNoAdminAccountEdit).isChecked))
        active = 0
    if(errorMessage=="") {
        var outPutString = hash.hashMessage(password!!.text.toString())
        var cv = ContentValues()
        cv.put("adminFullName", fullName!!.text.toString())
        cv.put("adminEmail", email!!.text.toString())
        cv.put("adminPhoneNo", phoneNo!!.text.toString())
        cv.put("adminUserName", userName!!.text.toString())
        cv.put("adminPassword", outPutString)
        cv.put("adminIsActive",active)
        if(from == "accountEdit") {
            db!!.updateAdminAccount(cv, accountId.toString())
        }else if(from == "createAccount" ){
            db!!.createAdmin(cv)
        }
            var homePage : Intent = Intent(this, AdminHomePage::class.java)

        startActivity(homePage)

    }else{
        error!!.isVisible = true
        error!!.text = errorMessage
    }
    }

}