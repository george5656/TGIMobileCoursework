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



class AdminMenuItemAdapter(var context : Context, var items:ArrayList<CafeItem>) : BaseAdapter()  {


    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): CafeItem {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view

            view = LayoutInflater.from(context).inflate(R.layout.admin_cafe_menu_list_item,parent,false)

            var name = view.findViewById<TextView>(R.id.txtProdName)
            var price = view.findViewById<TextView>(R.id.txtProdPrice)
            var availble = view.findViewById<TextView>(R.id.txtProdAvailable)

            var image = view.findViewById<ImageView>(R.id.ivProduct)

        name.text = "name " + items[position].proName
        price.text = " cost £" + items[position].prodPrice.toString()
        availble.text = "avalbe " + items[position].prodAvailable.toString()
        /*
        is basically just coveriting the ByteArray into, bitmap for it to be displaid, the zero is the offset, so
        if anything it shouldn't effect, the last part is simply the length for it to decoded, and first part is the
        byte array to decoded
         */
        image.setImageBitmap(BitmapFactory.decodeByteArray(items[position].prodImage,0,items[position].prodImage.size))




        return view

    }
}