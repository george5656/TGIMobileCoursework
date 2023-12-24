package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.example.mobilecoursework.model.DatabaseHelper

class AdminAccountEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_account_edit)
    }

    override fun onStart() {
        super.onStart()

        var accountId = intent.getStringExtra("adminId")
    var db = DatabaseHelper(this)
        var userName = findViewById<EditText>(R.id.etuserNameEdit)
        var password = findViewById<EditText>(R.id.etPasswordEdit)
        var fullName = findViewById<EditText>(R.id.etFullNameEdit)
        var email = findViewById<EditText>(R.id.etEmailEdit)
        var phoneNo = findViewById<EditText>(R.id.etPhoneEdit)
        userName.text.clear()
        password.text.clear()
        fullName.text.clear()
        email.text.clear()
        phoneNo.text.clear()

        var details = db.getAdminDetails(accountId)

        details.moveToFirst()

            userName.text.append(details.getString(4).toString())
            password.text.append(details.getString(5).toString())
            fullName.text.append(details.getString(2).toString())
            email.text.append(details.getString(2).toString())
            phoneNo.text.append(details.getString(3).toString())
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



}