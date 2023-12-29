package com.example.mobilecoursework.model

import java.security.MessageDigest

class Hash() {
    fun hashMessage(password: String): String {
        var md = MessageDigest.getInstance("SHA-256")
        md.update(password.toByteArray())
        var hash = md.digest()
        /* basically a a string buffer, so can get the array data one at a time, else it will
      just keep changing the hash every time it is ran
     */
        var outPutString = StringBuilder()
        for (data in hash) {
            outPutString.append(data.toString())
        }
        return outPutString.toString()
    }
}