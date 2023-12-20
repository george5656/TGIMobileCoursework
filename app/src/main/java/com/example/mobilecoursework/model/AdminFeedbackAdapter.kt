package com.example.mobilecoursework.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.mobilecoursework.R

class AdminFeedbackAdapter(var context : Context): BaseAdapter() {
    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

       var view = LayoutInflater.from(context).inflate(R.layout.admin_cafe_menu_list_item,p2,false)
    }
}