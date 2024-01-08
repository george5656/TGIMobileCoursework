package com.example.mobilecoursework.model

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mobilecoursework.R

class CustomerCartItemAdapter(var context: Context, var items: ArrayList<CartItem>) : BaseAdapter() {


    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(x: Int): CartItem {
        return items[x]
    }

    override fun getItemId(x: Int): Long {
        return x.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view

        view = LayoutInflater.from(context).inflate(R.layout.customer_cart_list_item, parent, false)

        var name = view.findViewById<TextView>(R.id.txtCartName)
        var price = view.findViewById<TextView>(R.id.txtCartPrice)
        var quantity = view.findViewById<TextView>(R.id.txtCartQuantity)

        name.text = items[position].productName
        price.text = items[position].price.toString()
        quantity.text = items[position].quantity.toString()

        return view

    }
}