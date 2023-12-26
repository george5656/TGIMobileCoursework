package com.example.mobilecoursework.model

import java.lang.Error

class inputValdiation() {
    fun StringValidaiton(input : String):String{
        var errorMessage = ""
        if(input.length > 50){
            errorMessage = " it to long"
        }
        if(!input.matches(Regex("[0-9a-zA-Z ]+"))){
            errorMessage = " only allows a-z and 0-9 allowed"
        }
        if(input==""){
            errorMessage=" missing inputted data"
        }
    return errorMessage
    }
fun priceValidaiton(price:String):String {
    var errorMessage = ""

if(price.indexOf(".")==0&& price.length == 1){
    errorMessage = "need more than just \".\""
}



    if (price.length > 50) {
        errorMessage = " it to long"
    }
    var decimalPoint: Int = price.indexOf(".")
    if (decimalPoint != -1) {
        var afterDecimal = price.subSequence(decimalPoint, price.length)
        if (afterDecimal.length >3) {
            errorMessage = " only 2 decimal places"
        }
    }
        if (!price.matches(Regex("[0-9.]+"))) {
            errorMessage = " only allows a-z and 0-9 allowed"
        }
        if (price == "") {
            errorMessage = " missing inputted data"
        }
if(errorMessage==""){
    var priceD: Double? = price?.toDouble()
    if (priceD == null) {
        errorMessage = "wrong data type"
    }
}
        return errorMessage
    }
}
