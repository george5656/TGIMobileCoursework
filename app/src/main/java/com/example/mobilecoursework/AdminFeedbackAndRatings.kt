package com.example.mobilecoursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.mobilecoursework.model.AdminFeedbackAdapter

class AdminFeedbackAndRatings : AppCompatActivity() {

    var adpater = AdminFeedbackAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_feedback_and_ratings)
        findViewById<ListView>(R.id.lvFeedback).adapter = adpater
    }
}