package com.example.mobilecoursework.model
import org.junit.Test
import org.junit.Assert.*
class StringFormatTest{
    var format = StringFormat()
    @Test
    fun DateNormalValidate(){
        assertEquals("20231227", format.DateFormat("2023/12/27"))
    }

}