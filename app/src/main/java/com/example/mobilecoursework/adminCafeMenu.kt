package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.mobilecoursework.model.AdminMenuItemAdapter
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.DatabaseHelper

class adminCafeMenu : AppCompatActivity() {

    var db = DatabaseHelper(this)
    var adapter = AdminMenuItemAdapter(this, arrayOf(CafeItem("Test",10.100f,ByteArray(1),true)))
    var lv = findViewById<ListView>(R.id.lvAdminCafeMenuItems)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cafe_menu)

lv.adapter = adapter


    }
    fun addItemLoad(view: View){
        var addItemIntent: Intent = Intent(this, AdminAddCafeItem::class.java)
        startActivity(addItemIntent)
    }

    fun getCafeItems(): Array<CafeItem?>{
        var cusrsor = db.getMenuItems()
        var menuItem = arrayOfNulls<CafeItem>(cusrsor.count)
        var item: CafeItem? = null
        var inStock: Boolean = false
        var counter = 0
        if(cusrsor.moveToFirst()){
            do{
                 if(cusrsor.getInt(4)==1){
                    inStock= true
                }else{
                    inStock= false
                }
            //    item = CafeItem(cusrsor.getString(1),cusrsor.getFloat(2),cusrsor.getBlob(3),inStock)
            menuItem[counter] = item
                counter = counter + 1
            }while(cusrsor.moveToNext())


        }
    return menuItem
    }

}