package com.example.mobilecoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible

import com.example.mobilecoursework.model.AdminMenuItemAdapter
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.DatabaseHelper

class adminCafeMenu : AppCompatActivity() {

    var x = test()

    var selectedItem: CafeItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cafe_menu)


        var adapter = AdminMenuItemAdapter(this, getCafeItems() )
        var lv = findViewById<ListView>(R.id.lvAdminCafeMenuItems)
        lv.adapter = adapter

        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l -> selectedItem = lv.getItemAtPosition(i) as? CafeItem;

        }

        lv.setOnItemClickListener(onclick)



    }
    class test : AdapterView.OnItemClickListener{
        public
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        }

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

        var error = findViewById<TextView>(R.id.txtCafeMenuError)
      if(selectedItem == null){
          error.isVisible = true
          error.text = "none selected"
      }else {


          var editItemIntent: Intent = Intent(this, AdminAddCafeItem::class.java)
          editItemIntent.putExtra("type","edit")


         editItemIntent.putExtra("menuItem", selectedItem!!.proName)
          startActivity(editItemIntent)

         error.isVisible = false

        findViewById<EditText>(R.id.etItem).text.clear()
        findViewById<EditText>(R.id.etItem).append(selectedItem?.proName)
      }
      }
    fun filterLoad(view:View){
        var filterItemIntent: Intent = Intent(this, AdminNotificationFilter::class.java)
       startActivity(filterItemIntent)
    }



}