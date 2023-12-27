package com.example.mobilecoursework.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mobilecoursework.R

class AdminFeedbackAdapter(var context: Context, var items: ArrayList<Feedback>) : BaseAdapter() {

    // private var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view =
            LayoutInflater.from(context).inflate(R.layout.admin_feedback_list_item, p2, false)
        var username = view.findViewById<TextView>(R.id.txtFeedbackListItemUserName)
        var feedback = view.findViewById<TextView>(R.id.txtFeedbackListItemFeedback)
        var rating = view.findViewById<TextView>(R.id.txtFeedbackListItemRating)
        username.text = items[p0].userName
        feedback.text = items[p0].feedback
        rating.text = items[p0].rating.toString()
        return view
    }
}