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



   var x = ArrayList<CafeItem>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cafe_menu)

        x.add(CafeItem("test",10.00f,ByteArray(1),true))

        var adapter = AdminMenuItemAdapter(this, getCafeItems() )
        var lv = findViewById<ListView>(R.id.lvAdminCafeMenuItems)
        lv.adapter = adapter



    }
    fun addItemLoad(view: View){
        var addItemIntent: Intent = Intent(this, AdminAddCafeItem::class.java)
        startActivity(addItemIntent)
    }

   fun getCafeItems(): ArrayList<CafeItem>{
       var db: DatabaseHelper = DatabaseHelper(this)
        var cusrsor = db.getMenuItems()
        var menuItem = ArrayList<CafeItem>()
        var item: CafeItem
        var inStock: Boolean = false
        var counter = 0
        if(cusrsor.moveToFirst()){
            do{
                 if(cusrsor.getInt(4)==1){
                    inStock= true
                }else{
                    inStock= false
                }
                item = CafeItem(cusrsor.getString(1),cusrsor.getFloat(2),cusrsor.getBlob(3),inStock)
            menuItem.add(item)
                counter = counter + 1
            }while(cusrsor.moveToNext())


        }

    return menuItem
    }

}