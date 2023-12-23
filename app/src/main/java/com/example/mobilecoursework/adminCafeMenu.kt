package com.example.mobilecoursework

import android.content.Intent
import android.database.Cursor
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


    var selectedItem: CafeItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cafe_menu)
    }
    override fun onStart() {
        super.onStart()



        var db: DatabaseHelper = DatabaseHelper(this)

        var cusrsor = db.getMenuItems()
    var whereClause = ""
        var whereClauseUse =""
        var maxPrice = intent.getStringExtra("maxPrice")
        var minPrice = intent.getStringExtra("minPrice")
        var inStock = intent.getStringExtra("inStock")
        var hasImage = intent.getStringExtra("image")
        if(intent.getStringExtra("from")=="filter"){
        if(maxPrice!=""){
            whereClause = whereClause + "prodPrice <= " + maxPrice + " AND "
        }
            if(minPrice!=""){
                whereClause = whereClause + "prodPrice >= " + minPrice + " AND "
           }

            if(inStock!=""){
                whereClause = whereClause + "prodAvailable == " + inStock + " AND "
            }
            if(hasImage!=""){
                if(hasImage=="false"){
                    whereClause = whereClause + "prodImage == " + "null" + " AND "
                }

            }
        if(whereClause!="") {
            whereClauseUse = whereClause.subSequence(0, whereClause.length-4).toString() + ";"
        }
            cusrsor = db.getMenuItemThatMatchPassedInWhere(whereClauseUse)
    }
        var data : ArrayList<CafeItem> = getCafeItems(cusrsor)
        var adapter = AdminMenuItemAdapter(this,data)
        var lv = findViewById<ListView>(R.id.lvAdminCafeMenuItems)
        lv.adapter = adapter

        var onclick = AdapterView.OnItemClickListener { adapterView, view, i, l -> selectedItem = lv.getItemAtPosition(i) as? CafeItem;

        }

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

   fun getCafeItems(cusrsor: Cursor): ArrayList<CafeItem>{

        var menuItem = ArrayList<CafeItem>()
        var item: CafeItem
        var inStock: Boolean = false
       // var counter = 0
        if(cusrsor.moveToFirst()){
            do{
                 if(cusrsor.getInt(4)==1){
                    inStock= true
                }else{
                    inStock= false
                }
                item = CafeItem(cusrsor.getInt(0),cusrsor.getString(1),cusrsor.getFloat(2),cusrsor.getBlob(3),inStock)
            menuItem.add(item)
               // counter = counter + 1
            }while(cusrsor.moveToNext())


        }

    return menuItem
    }



    fun deleteButton(view:View){
        //shows red as refactoed the name so  for somereason doesn't work unless original name is used
        var error = findViewById<TextView>(R.id.txtCafeMenuError)
        if(selectedItem == null) {
            error.isVisible = true
            error.text = "none selected"
        }else {
            var deleteItemIntent: Intent = Intent(this, AdminDeleteCOnfirmation::class.java)
            deleteItemIntent.putExtra("menuItemToBeDeleted", selectedItem!!.proId.toString())
            deleteItemIntent.putExtra("typeOfDelete", "menuItem")
            startActivity(deleteItemIntent)
        }
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
        var filterItemIntent: Intent = Intent(this, AdminMenuItemFilter::class.java)
       startActivity(filterItemIntent)
    }

    fun  findButton(view:View){
        var db: DatabaseHelper = DatabaseHelper(this)
        var menuItemName = findViewById<EditText>(R.id.etItem).text.toString()
        if(menuItemName!="") {
            var whereClauseUse = "prodName like \"%" + menuItemName+"%\""
            var cusrsor = db.getMenuItemThatMatchPassedInWhere(whereClauseUse)
            var data : ArrayList<CafeItem> = getCafeItems(cusrsor)
            var adapter = AdminMenuItemAdapter(this,data)
            var lv = findViewById<ListView>(R.id.lvAdminCafeMenuItems)
            lv.adapter = adapter

        }

    }

}