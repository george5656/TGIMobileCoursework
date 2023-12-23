package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible

class AdminFeedbackFilter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_feedback_filter)
    }

    fun applyButton(view: View){
        var maxRating = findViewById<EditText>(R.id.etFeedbackMaxRating).text.toString()
        var minRating = findViewById<EditText>(R.id.etFeedbackMinRating).text.toString()
        var beforeDate = findViewById<EditText>(R.id.etFeedbackBeforeDate).text.toString()
        var afterDate = findViewById<EditText>(R.id.etFeedbackAfterDate).text.toString()
if(maxRating==""&&minRating==""&&beforeDate==""&&afterDate==""){
  var error =   findViewById<TextView>(R.id.txtFeedbackFilterError)
    error.isVisible = true
    error.text = "error, please give more info"
}else {
    var applyIntent: Intent = Intent(this, AdminFeedbackAndRatings::class.java)
    applyIntent.putExtra("from", "filter")
    applyIntent.putExtra("max", maxRating)
    applyIntent.putExtra("min", minRating)
    applyIntent.putExtra("before", beforeDate)
    applyIntent.putExtra("after", afterDate)

    startActivity(applyIntent)
}
    }


}