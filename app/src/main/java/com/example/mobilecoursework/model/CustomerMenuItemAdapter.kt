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

class CustomerMenuItemAdapter(var context: Context, var items: ArrayList<CafeItem>) : BaseAdapter() {


    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(x: Int): CafeItem {
        return items[x]
    }

    override fun getItemId(x: Int): Long {
        return x.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view

        view = LayoutInflater.from(context).inflate(R.layout.customer_cafe_menu_list_item, parent, false)

        var name = view.findViewById<TextView>(R.id.txtItemName)
        var price = view.findViewById<TextView>(R.id.txtItemPrice)

        var image = view.findViewById<ImageView>(R.id.liImage)

        name.text = items[position].proName
        price.text = "£" + items[position].prodPrice.toString()

        if (items[position].prodImage != null) {
            image.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    items[position].prodImage,
                    0,
                    items[position].prodImage!!.size
                )
            )
        }



        return view

    }
}