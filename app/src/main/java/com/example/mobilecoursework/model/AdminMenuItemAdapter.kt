package com.example.mobilecoursework.model

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class AdminMenuItemAdapter(var contect : Context, var items:ArrayList<CafeItem>) : BaseAdapter()  {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {



        TODO("Not yet implemented")
    }
}