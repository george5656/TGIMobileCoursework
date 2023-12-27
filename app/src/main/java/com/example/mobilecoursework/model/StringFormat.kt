package com.example.mobilecoursework.model

class StringFormat {
    // nfs = not formatted string
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
fun timeFormat(nfs:String):String{
    var formattedString = ""
    if(nfs!="") {
        var indexOfCollon = nfs.indexOf(":")
        var hh = nfs.subSequence(0, indexOfCollon)
        var mm = nfs.subSequence(indexOfCollon + 1, nfs.length)
        formattedString = "" + hh + mm
    }
    return formattedString
}
}