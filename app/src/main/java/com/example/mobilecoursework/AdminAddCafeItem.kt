package com.example.mobilecoursework

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.DatabaseHelper


class AdminAddCafeItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_cafe_item)
        var db = DatabaseHelper(this)
      var typeCall = intent.getStringExtra("type")

    if(typeCall == "edit") {

        var name = intent.getStringExtra("menuItem")
      var result =  db.getMenuItemThatMatchName(name!!);
    result.moveToFirst();

        var tOrF : Boolean? = null

        if (result.getInt(4)==1){
            tOrF = true
        }else{
            tOrF= false
        }

        var data = CafeItem(result.getString(1), result.getFloat(2),result.getBlob(3), tOrF)
        populateWidgets(data)
  }

    }
    fun imageLoader(view : View){
        var fileChoserIntent : Intent = Intent()
        fileChoserIntent.setType("image/*")
        fileChoserIntent.setAction(Intent.ACTION_GET_CONTENT)
        startActivity(fileChoserIntent)
      var results : Uri? = intent.clipData?.getItemAt(0)?.uri

    }
    fun backButton(view:View){
        var homeIntent: Intent = Intent(this, AdminHomePage::class.java)
        startActivity(homeIntent)
    }
    fun populateWidgets(data :CafeItem){
        var name = findViewById<EditText>(R.id.etNameCafeItemAdd)
        var price = findViewById<EditText>(R.id.etPriceCafeItemAdd)
        var availableYes = findViewById<RadioButton>(R.id.rbCafeItemAddYes)
        var availableNo = findViewById<RadioButton>(R.id.rbCafeItemAddNo)
       var nameText = name.text
        var priceText = price.text
        //as is an editable object have to do the eextra loops
        nameText.clear()
        nameText.append(data.proName.toString())
        priceText.clear()
        priceText.append(data.prodPrice.toString())
        if(data.prodAvailable == true){
            availableYes.isChecked = true
        }else{
            availableNo.isChecked = true
        }
    }

}