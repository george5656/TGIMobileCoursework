package com.example.mobilecoursework.model

class StringFormat {
    fun DateFormat(nfs:String):String{
        var formattedString = ""
      if(nfs!="") {
          var indexOfFirstSlash = nfs.indexOf("/")
          var indexOfSecondSlash = nfs.indexOf("/", indexOfFirstSlash + 1)
          var year = nfs.subSequence(0, indexOfFirstSlash)
          var month = nfs.subSequence(indexOfFirstSlash + 1, indexOfSecondSlash)
          var day = nfs.subSequence(indexOfSecondSlash + 1, nfs.length)
          formattedString = "" + year + month + day
      }
          return formattedString
    }
}