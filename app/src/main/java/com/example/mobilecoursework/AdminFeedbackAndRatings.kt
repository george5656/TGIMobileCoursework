package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.mobilecoursework.model.AdminFeedbackAdapter
import com.example.mobilecoursework.model.Feedback

class AdminFeedbackAndRatings : AppCompatActivity() {

    var adpater = AdminFeedbackAdapter(this, arrayOf(Feedback("SirRandom","best food",5),Feedback("test","test",3)))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_feedback_and_ratings)
        findViewById<ListView>(R.id.lvFeedback).adapter = adpater
    }
    fun backButton(view: View){
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
    }
}