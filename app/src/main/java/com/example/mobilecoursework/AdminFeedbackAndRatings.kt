package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.mobilecoursework.model.AdminFeedbackAdapter
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.Feedback

class AdminFeedbackAndRatings : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_feedback_and_ratings)

    }

    override fun onStart() {
        super.onStart()
       var db = DatabaseHelper(this)
        var data = db.getFeedback()
       var max = intent.getStringExtra("max")
       var min = intent.getStringExtra("min")
       var before = intent.getStringExtra("before")
       var after = intent.getStringExtra("after")
        var whereClause = ""
        var whereClauseUse = ""
        if(intent.getStringExtra("from")=="filter"){
            if(max !=""){
                whereClause = whereClause + "Feedback.rating <= " + max + " and "
            }
            if(min !=""){
                whereClause = whereClause + "Feedback.rating >= " + min + " and "
            }
            if(before !=""){
                whereClause = whereClause + "(\"orderData\" <= \"" + before!!.toInt()  + "\") and "
            }
            if(after !=""){
                whereClause = whereClause + "(\"orderData\" >= \"" + after!!.toInt() + "\") and "
            }
            if(whereClause!="") {
                whereClauseUse = whereClause.subSequence(0, whereClause.length-4).toString() + ";"
                data = db.getFeedbackCustomerWhere(whereClauseUse)
            }

        }

        var adpater = AdminFeedbackAdapter(this,getDate(data) )
            findViewById<ListView>(R.id.lvFeedback).adapter = adpater
    }
    fun backButton(view: View){
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
    }

    fun filterButton(view:View){
        var filterIntent: Intent = Intent(this, AdminFeedbackFilter::class.java)
        startActivity(filterIntent)
    }
fun getDate(cursor: Cursor):ArrayList<Feedback>{
    var FeedbackItem = ArrayList<Feedback>()
    var listItem: Feedback
    if(cursor.moveToFirst()){
        do {
            var db = DatabaseHelper(this)
var userNameCursor = db.getSpecificUser(cursor.getInt(1).toString())
            userNameCursor.moveToFirst()
            listItem = Feedback(userNameCursor.getString(4),cursor.getString(3),cursor.getInt(4))
        FeedbackItem.add(listItem)
        }while(cursor.moveToNext())
        }
    return FeedbackItem
}
}