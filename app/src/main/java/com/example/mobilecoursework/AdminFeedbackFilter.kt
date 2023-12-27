package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.StringFormat
import com.example.mobilecoursework.model.InputValdiation

class AdminFeedbackFilter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_feedback_filter)
    }

    fun applyButton(view: View) {
        var maxRating = findViewById<EditText>(R.id.etFeedbackMaxRating).text.toString()
        var minRating = findViewById<EditText>(R.id.etFeedbackMinRating).text.toString()
        var beforeDate = findViewById<EditText>(R.id.etFeedbackBeforeDate).text.toString()
        var afterDate = findViewById<EditText>(R.id.etFeedbackAfterDate).text.toString()
        var validation = InputValdiation()
        var userInputMaxRating = validation.ratingValidation(maxRating)
        var userInputMinRating = validation.ratingValidation(minRating)
        var beforeDateValdiation = validation.dateValdiation(beforeDate)
        var afterDateValdiation = validation.dateValdiation(afterDate)
        var errorMessage = ""
        if (userInputMaxRating != "") {
            errorMessage = "max rating " + userInputMaxRating
        } else if (userInputMinRating != "") {
            errorMessage = "min rating " + userInputMinRating
        } else if (beforeDateValdiation != "") {
            errorMessage = "before date " + beforeDateValdiation
        } else if (afterDateValdiation != "") {
            errorMessage = "after date " + afterDateValdiation
        }
        if (errorMessage != "") {
            var error = findViewById<TextView>(R.id.txtFeedbackFilterError)
            error.isVisible = true
            error.text = errorMessage
        } else {
            var sf = StringFormat()
            var applyIntent: Intent = Intent(this, AdminFeedbackAndRatings::class.java)
            applyIntent.putExtra("from", "filter")
            applyIntent.putExtra("max", maxRating)
            applyIntent.putExtra("min", minRating)
            applyIntent.putExtra("before", sf.DateFormat(beforeDate))
            applyIntent.putExtra("after", sf.DateFormat(afterDate))

            startActivity(applyIntent)
        }
    }

    fun cancelButton(view: View) {
        var feedbackIntent: Intent = Intent(this, AdminFeedbackAndRatings::class.java)
        startActivity(feedbackIntent)
    }


}