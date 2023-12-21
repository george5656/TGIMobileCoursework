package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.example.mobilecoursework.model.AdminMenuItemAdapter
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.DatabaseHelper

class adminCafeMenu : AppCompatActivity() {

    var selectedItem: Any? = null

   var x = ArrayList<CafeItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cafe_menu)

        x.add(CafeItem("test",10.00f,ByteArray(1),true))

        var adapter = AdminMenuItemAdapter(this, getCafeItems() )
        var lv = findViewById<ListView>(R.id.lvAdminCafeMenuItems)
        lv.adapter = adapter

        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l -> selectedItem = lv.getItemAtPosition(i) }
        lv.setOnItemClickListener(onclick)



    }
    fun addItemLoad(view: View){
        var addItemIntent: Intent = Intent(this, AdminAddCafeItem::class.java)
        startActivity(addItemIntent)
    }
    fun backButton(view:View){
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
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



    fun deleteButton(view:View){
        //shows red as refactoed the name so  for somereason doesn't work unless original name is used
        var editItemIntent: Intent = Intent(this, AdminDeleteCOnfirmation::class.java)
        startActivity(editItemIntent)
    }
    fun editLoad(view:View){
      if(selectedItem == null){

      }else {

          var editItemIntent: Intent = Intent(this, AdminAddCafeItem::class.java)
          startActivity(editItemIntent)
      }
      }
    fun filterLoad(view:View){
        var filterItemIntent: Intent = Intent(this, AdminNotificationFilter::class.java)
       startActivity(filterItemIntent)
    }
}