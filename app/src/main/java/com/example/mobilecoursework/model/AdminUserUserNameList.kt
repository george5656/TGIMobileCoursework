package com.example.mobilecoursework.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mobilecoursework.R

class AdminUserUserNameList(var context : Context, var items:ArrayList<String>):BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View? = p1

        view = LayoutInflater.from(context).inflate(R.layout.admin_user_list,p2,false)

        var userName = view.findViewById<TextView>(R.id.sNotificationUserName)
       userName.text = items[p0]

        return view
    }
}