package com.example.mobilecoursework.model

import java.lang.Error

class inputValdiation() {
    fun stringValidaiton(input : String):String{
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
var empty = ""
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
        if (!price.matches(Regex("[0-9. ]+"))&& price!="") {

                errorMessage = " only allows a-z and 0-9 allowed"

        }
        if (price == "") {
            empty = " missing inputted data"
        }
if(errorMessage==""&& empty ==""){
    var priceD: Double? = price?.toDouble()
    if (priceD == null) {
        errorMessage = "wrong data type"
    }
}
        return errorMessage
    }
fun phoneNumberValidation(phoneNo:String):String{
    var errorMessage = ""
    if(phoneNo.length > 11){
        errorMessage = " it to long"
    }
    if(!phoneNo.matches(Regex("[0-9]+"))){
        errorMessage = " only allows 0-9 allowed"
    }
    if(phoneNo.length<=9){
        errorMessage=" to short"
    }
    return errorMessage

}
fun emailValidation(email:String):String{
    var errorMessage = ""
    var atLocaiton = email.indexOf("@")
    if(email.length > 50){
        errorMessage = " it\'s to long"
    }
    if(!email.matches(Regex("[0-9a-zA-Z.@_ -]+"))){
        errorMessage = " only allows a-z, 0-9, @, \'.\', \'-\' allowed"
    }
    if(atLocaiton == -1){
        errorMessage = " invalid"
    }
    if(!(email.subSequence(atLocaiton+1,email.length).indexOf("@")==-1)){
        errorMessage = " invalid"
    }
    if((email.indexOf(".com")==-1)){
        errorMessage = " missing .com"
    }
    if(email==""){
        errorMessage=" missing inputted data"
    }
    return errorMessage
}
}
