package com.example.mobilecoursework.model

import java.io.Serializable

class CartItem(var productId: Int, var productName: String, var price: Float, var quantity: Int):Serializable{
}