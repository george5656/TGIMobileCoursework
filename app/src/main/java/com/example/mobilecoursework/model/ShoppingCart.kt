package com.example.mobilecoursework.model

class ShoppingCart private constructor() {

    private val cartItems: ArrayList<CartItem> = ArrayList()

    fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.productId == item.productId }

        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            cartItems.add(item)
        }
    }

    fun removeItem(productId: Int) {
        val itemToRemove = cartItems.find { it.productId == productId }
        if (itemToRemove != null) {
            // If the item's quantity is greater than 1, decrement the quantity
            if (itemToRemove.quantity > 1) {
                itemToRemove.quantity--
            } else {
                // If the item's quantity is 1 or less, remove the item from the cart
                cartItems.remove(itemToRemove)
            }
        }
    }


    fun getItems(): ArrayList<CartItem> {
        return ArrayList(cartItems)
    }

    fun getTotalCost(): Float {
        return cartItems.sumByDouble { it.price.toDouble() * it.quantity }.toFloat()
    }

    fun clearCart() {
        cartItems.clear()
    }

    companion object{
        private var instance: ShoppingCart? = null

        fun getInstance(): ShoppingCart {
            if (instance == null) {
                synchronized(ShoppingCart::class.java) {
                    if (instance == null) {
                        instance = ShoppingCart()
                    }
                }
            }
            return instance!!
        }
    }
}
