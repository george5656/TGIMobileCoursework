package com.example.mobilecoursework.model

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import java.io.ByteArrayOutputStream

class ImageSave() {
    fun bitmapToByteArray(image: ImageView): ByteArray {
        //refrnece as solution learnt from stack overflow https://stackoverflow.com/questions/4435806/drawable-to-byte
        var bitMapInWrapper = image.drawable as BitmapDrawable
        var bitMap = bitMapInWrapper.bitmap
        var stream = ByteArrayOutputStream()
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        var byteArray = stream.toByteArray()
        return byteArray
    }
}