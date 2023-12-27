package com.example.mobilecoursework.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mobilecoursework.R

class AdminIncomingOrderAdapter(private var context : Context, private var items: ArrayList<Order>):BaseAdapter() {


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

       var view = LayoutInflater.from(context).inflate(R.layout.admin_incoming_orders_list_item,p2,false)
        var username = view.findViewById<TextView>(R.id.txtOrderListItemName)
        var date = view.findViewById<TextView>(R.id.txtOrderListItemDate)
        var time = view.findViewById<TextView>(R.id.txtOrderListItemTime)
        var status = view.findViewById<TextView>(R.id.txtOrderListItemStatus)
        username.text = items[p0].userName.toString()
        var dateAsString = items[p0].date.toString()
        var formattedDate = "" + dateAsString.subSequence(0,4) + "\\" + dateAsString.subSequence(4,6) + "\\"+dateAsString.subSequence(6,8)
        date.text = formattedDate
        var formattedTime = "" + items[p0].time.toString().subSequence(0,2) + ":" + items[p0].time.toString().subSequence(2,4)
        time.text = formattedTime
        status.text = items[p0].status.toString()
        return view
    }


}