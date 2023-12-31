package com.example.mobilecoursework.model


import kotlin.Error

class InputValdiation() {
    fun stringValidaiton(input: String): String {
        var errorMessage = ""
        if (input.length > 50) {
            errorMessage = " it to long"
        }
        if (!input.matches(Regex("[0-9a-zA-Z ]+"))) {
            errorMessage = " only allows a-z and 0-9 allowed"
        }
        if (input == "") {
            errorMessage = " missing inputted data"
        }
        return errorMessage
    }

    fun priceValidaiton(price: String): String {
        var errorMessage = ""
        var empty = ""
        if (price.indexOf(".") == 0 && price.length == 1) {
            errorMessage = "need more than just \".\""
        }
        if (price.length > 50) {
            errorMessage = " it to long"
        }
        var decimalPoint: Int = price.indexOf(".")
        if (decimalPoint != -1) {
            var afterDecimal = price.subSequence(decimalPoint, price.length)
            if (afterDecimal.length > 3) {
                errorMessage = " only 2 decimal places"
            }
        }
        if (!price.matches(Regex("[0-9. ]+")) && price != "") {

            errorMessage = " only allows a-z and 0-9 allowed"

        }
        if (price == "") {
            empty = " missing inputted data"
        }
        if (errorMessage == "" && empty == "") {
            var priceD: Double? = price?.toDouble()
            if (priceD == null) {
                errorMessage = "wrong data type"
            }
        }
        return errorMessage
    }

    fun phoneNumberValidation(phoneNo: String): String {
        var errorMessage = ""
        if (phoneNo.length > 11) {
            errorMessage = " it to long"
        }
        if (!phoneNo.matches(Regex("[0-9]+"))) {
            errorMessage = " only allows 0-9 allowed"
        }
        if (phoneNo.length <= 9) {
            errorMessage = " to short"
        }
        return errorMessage

    }

    fun emailValidation(email: String): String {
        var errorMessage = ""
        var atLocaiton = email.indexOf("@")
        if (email.length > 50) {
            errorMessage = " it\'s to long"
        }
        if (!email.matches(Regex("[0-9a-zA-Z.@_ -]+"))) {
            errorMessage = " only allows a-z, 0-9, @, \'.\', \'-\' allowed"
        }
        if (atLocaiton == -1) {
            errorMessage = " invalid"
        }
        if (!(email.subSequence(atLocaiton + 1, email.length).indexOf("@") == -1)) {
            errorMessage = " invalid"
        }
        if ((email.indexOf(".com") == -1)) {
            errorMessage = " missing .com"
        }
        if (email == "") {
            errorMessage = " missing inputted data"
        }
        return errorMessage
    }

    fun ratingValidation(rating: String): String {
        var errorMessage = ""
        // if(rating.length > 1){
        //     errorMessage = " it to long"
        // }
        if (!rating.matches(Regex("[0-5]")) && rating != "") {
            errorMessage = " only allows 0-5 allowed"
        }
        //if(input==""){
        //    errorMessage=" missing inputted data"
        //}
        return errorMessage
    }

    fun dateValdiation(date: String): String {
        var errorMessage = ""
        var indexOfFirstSlash = date.indexOf("/")
        var indexOfSecondSlash: Int = -1
        var extraSlash: Int = -1

        if (date.length > 10) {
            errorMessage = " it to long"
        }
        if (!date.matches(Regex("[0-9/]+")) && date != "") {
            errorMessage = " only allows 0-9 and / allows"
        }
        if (date.length < 10 && date != "") {
            errorMessage = " missing inputted data"
        }
        if (date.length >= 2) {
            indexOfSecondSlash = date.indexOf("/", indexOfFirstSlash + 1)
        }
        if (date.length >= 3 && indexOfSecondSlash != -1) {
            extraSlash = date.indexOf("/", indexOfSecondSlash + 1)
        }

        if (extraSlash != -1) {
            errorMessage = " only 2 / allowed"
        } else if (indexOfFirstSlash == -1 && date != "") {
            errorMessage = " missing /"
        } else if (indexOfSecondSlash == -1 && date != "") {
            errorMessage = " missing second /"
        } else if (date != "") {
            var year = date.subSequence(0, indexOfFirstSlash)
            var month = date.subSequence(indexOfFirstSlash + 1, indexOfSecondSlash)
            var day = date.subSequence(indexOfSecondSlash + 1, date.length)
            if (date.indexOf("/") == -1) {
                errorMessage = " need to be in the format yyyy/mm/dd"
            }
            if (year.length != 4) {
                errorMessage = " need to be in the format yyyy/mm/dd"
            } else if (month.length != 2) {
                errorMessage = " need to be in the format yyyy/mm/dd"
            } else if (day.length != 2) {
                errorMessage = " need to be in the format yyyy/mm/dd"
            }
            var dayInt: Int = -1
            var monthInt: Int = -1

            try {
                dayInt = day.toString().toInt()
                monthInt = month.toString().toInt()
            } catch (e: Error) {

            }

            if (dayInt != -1 && ((monthInt == 1 || monthInt == 3 || monthInt == 5 || monthInt == 7 || monthInt == 8 || monthInt == 10 || monthInt == 12) && dayInt > 31)) {
                errorMessage = " that month only has 31 days"
            } else if (dayInt != -1 && ((monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11) && dayInt > 30)) {
                errorMessage = " that month only has 30 days"
            } else if (dayInt != -1 && ((monthInt == 2) && dayInt > 28)) {
                errorMessage = " that month only has 28 days"
            }
            if (monthInt != -1 && monthInt > 12) {
                errorMessage = " only 12 months in a year "
            }

        }

        return errorMessage
    }

    fun timeValdiaiton(time: String): String {
        var errorMessage = ""
        var loactionOfCollon = time.indexOf(":")
        var locationOfExtraCollon = time.indexOf(":", loactionOfCollon + 1)
        if (!time.matches(Regex("[0-9:]+")) && time != "") {
            errorMessage = " only allows 0-9 and :"
        } else if (loactionOfCollon == -1 && time != "") {
            errorMessage = " not in the format mm:hh"
        } else if (locationOfExtraCollon != -1) {
            errorMessage = " has more than one :"
        } else if (time.length == 1) {
            errorMessage = " not enough data"
        } else if (time != "") {
            var hhInt: Int = -1
            var mmInt: Int = -1
            var hh = time.subSequence(0, loactionOfCollon)
            var mm = time.subSequence(loactionOfCollon + 1, time.length)
            if (hh.length < 2) {
                errorMessage = " hour need to be in the format hh"
            } else if (mm.length < 2) {
                errorMessage = " minutes need to be in the format mm"
            } else {
                try {
                    hhInt = hh.toString().toInt()
                    mmInt = mm.toString().toInt()
                } catch (e: Error) {
                    errorMessage = " wrong data type"
                }
                if (hhInt > 24 && hhInt != -1) {
                    errorMessage = " only 24 hours in a day"
                } else if (mmInt > 60 && mmInt != -1) {
                    errorMessage = " only 60 minutes a hour"
                }
            }
        }
        return errorMessage
    }

    fun stringMessageValidaiton(input: String): String {
        var errorMessage = ""
        if (input.length > 50) {
            errorMessage = " it to long"
        }
        if (!input.matches(Regex("[0-9a-zA-Z£,.? ]+"))) {
            errorMessage = " only allows a-z, 0-9, £, ',', '.', ? allowed"
        }
        if (input == "") {
            errorMessage = " missing inputted data"
        }
        return errorMessage
    }
}



