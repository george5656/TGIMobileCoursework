package com.example.mobilecoursework.model

import java.io.Serializable


class CafeItem(var proName: String, var prodPrice:Float,var prodImage: ByteArray, var prodAvailable: Boolean):Serializable{
}