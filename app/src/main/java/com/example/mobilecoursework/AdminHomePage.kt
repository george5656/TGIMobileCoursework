package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminHomePage : AppCompatActivity() {
    //companion object{
        var accountId : String? = null
    //}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home_page)

    }

    override fun onStart() {
        super.onStart()
        accountId = intent.getStringExtra("logedInId").toString()
    }
    fun menuLoad(view: View){
        var menuIntent:Intent = Intent(this,adminCafeMenu::class.java)
        startActivity(menuIntent)
    }
    fun feedbackLoad(view: View){
        var feedbackIntent:Intent = Intent(this,AdminFeedbackAndRatings::class.java)
        startActivity(feedbackIntent)
    }
    fun incomingOrderLoad(view: View){
        var orderIntent:Intent = Intent(this,AdminIncomingOrders::class.java)
        startActivity(orderIntent)
    }
    fun sendNotifcationLoad(view: View){
        var notificationIntent:Intent = Intent(this,AdminSendNotification::class.java)
        startActivity(notificationIntent)
    }
    fun accountSettingLoad(view: View){
        var settingsIntent:Intent = Intent(this, AdminAccountEdit::class.java)
        // as intent aren't super global i am just forwaring the message here
        settingsIntent.putExtra("adminId",accountId)
        startActivity(settingsIntent)
    }
    fun logout(view:View){
        var logOutIntent:Intent = Intent(this, MainActivity::class.java)
        startActivity(logOutIntent)
    }
    fun register(view:View){
        var registerIntent:Intent = Intent(this, AdminRegister::class.java)
        startActivity(registerIntent)
    }

}