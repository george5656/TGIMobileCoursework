package com.example.mobilecoursework

import android.content.ContentValues
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.mobilecoursework.model.CafeItem
import com.example.mobilecoursework.model.DatabaseHelper
import com.example.mobilecoursework.model.ImageSave
import com.example.mobilecoursework.model.InputValdiation


class AdminAddCafeItem : AppCompatActivity() {

    var name: EditText? = null
    var price: EditText? = null
    var availableYes: RadioButton? = null
    var availableNo: RadioButton? = null
    var image: ImageView? = null
    var available: String? = ""
    var id: Int = -1

    // this is basically the intent that andorid want you to use now
    @RequiresApi(Build.VERSION_CODES.P)
// the GetContent is just telling the replacemeant for startactivityforresult that the type of activity want is one to getContent
    var results = registerForActivityResult(ActivityResultContracts.GetContent()) {
        //this is creating an image source from uri which intnet gets
            uri: Uri? ->
        if (uri != null) {


            var imageSocurce = ImageDecoder.createSource(contentResolver, uri!!)
            //this is decoding source into a bitmap
            var imageBitmap = ImageDecoder.decodeBitmap(imageSocurce)
            //this is showing the image
            findViewById<ImageView>(R.id.ivImageAddCafeItem).setImageBitmap(imageBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_cafe_item)
    }

    override fun onStart() {
        super.onStart()
        name = findViewById<EditText>(R.id.etNameCafeItemAdd)
        price = findViewById<EditText>(R.id.etPriceCafeItemAdd)
        availableYes = findViewById<RadioButton>(R.id.rbCafeItemAddYes)
        availableNo = findViewById<RadioButton>(R.id.rbCafeItemAddNo)
        image = findViewById<ImageView>(R.id.ivImageAddCafeItem)
        available = ""
        var db = DatabaseHelper(this)
        var typeCall = intent.getStringExtra("type")

        if (typeCall == "edit") {

            var name = intent.getStringExtra("menuItem")
            var result = db.getMenuItemThatMatchName(name!!);
            result.moveToFirst();
            var tOrF: Boolean? = null

            if (result.getInt(4) == 1) {
                tOrF = true
            } else {
                tOrF = false
            }

            id = result.getInt(0)
            var data = CafeItem(
                result.getInt(0),
                result.getString(1),
                result.getFloat(2),
                result.getBlob(3),
                tOrF
            )
            populateWidgets(data)
        }

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun imageLoader(view: View) {
        results.launch("image/*")
    }

    fun backButton(view: View) {
        var menuIntent: Intent = Intent(this, AdminCafeMenu::class.java)
        startActivity(menuIntent)
    }

    fun populateWidgets(data: CafeItem) {

        image!!.setImageBitmap(
            BitmapFactory.decodeByteArray(
                data.prodImage,
                0,
                data.prodImage!!.size
            )
        )
        var nameText = name!!.text
        var priceText = price!!.text
        //as is an editable object have to do the eextra loops
        nameText.clear()
        nameText.append(data.proName.toString())
        priceText.clear()
        priceText.append(data.prodPrice.toString())
        if (data.prodAvailable == true) {
            availableYes!!.isChecked = true
        } else {
            availableNo!!.isChecked = true
        }
    }

    fun saveButton(view: View) {
        var validation = InputValdiation()
        var errorMessage = ""
        var nameErrors = validation.stringValidaiton(name!!.text.toString())
        var priceErrors = validation.priceValidaiton(price!!.text.toString())
        if (nameErrors != "") {
            errorMessage = "name " + nameErrors
        } else if (priceErrors != "") {
            errorMessage = "price " + priceErrors
        }
        if (availableYes!!.isChecked) {
            available = "yes"
        } else if (availableNo!!.isChecked) {
            available = "no"
        } else {
            errorMessage = "not stated if item is available"
        }
        if (price!!.text.toString() == "") {
            errorMessage = "price missing"
        }
        if (errorMessage == "") {
            var db = DatabaseHelper(this)
            var cv = saveChanges()
            if (intent.getStringExtra("type") == "edit") {
                db.updateCafeMenuItem(cv, id.toString())
                var menuIntent: Intent = Intent(this, AdminCafeMenu::class.java)
                startActivity(menuIntent)
            } else {
                db.createMenuItem(cv)
                var menuIntent: Intent = Intent(this, AdminCafeMenu::class.java)
                startActivity(menuIntent)
            }

        } else {
            var error = findViewById<TextView>(R.id.txtNameAddCafeItemError)
            error.isVisible = true
            error.text = errorMessage
        }
    }

    fun saveChanges(): ContentValues {
        if (availableYes!!.isChecked) {
            available = "yes"
        } else if (availableNo!!.isChecked) {
            available = "no"
        }
        var cv = ContentValues()
        cv.put("prodName", name!!.text.toString())
        cv.put("prodPrice", price!!.text.toString())
        var content = image?.drawable as? BitmapDrawable
        if (content != null) {
            var imageSave = ImageSave()
            var byteArray = imageSave.bitmapToByteArray(image!!)
            cv.put("prodImage", byteArray)
        } else {
            cv.putNull("prodImage")
        }
        if (available == "yes") {
            cv.put("prodAvailable", 1)
        } else if (available == "no") {
            cv.put("prodAvailable", 0)
        }
        return cv

    }
}