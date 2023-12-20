package com.example.mobilecoursework.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mobilecoursework.R


class AdminMenuItemAdapter(var context : Context, var items:Array<CafeItem?>) : BaseAdapter()  {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): CafeItem? {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view
        if (view!=null){
            view = LayoutInflater.from(context).inflate(R.layout.admin_cafe_menu_list_item,parent,false)

            var name = view.findViewById<TextView>(R.id.txtProdName)
            var price = view.findViewById<TextView>(R.id.txtProdPrice)
            var availble = view.findViewById<TextView>(R.id.txtProdAvailable)
            var image = view.findViewById<TextView>(R.id.ivProduct)
                name.text = getItem(position)?.proName
        }

        return view!!

    }
}