package com.example.mobilecoursework.model

import org.junit.Test
import org.junit.Assert.*

class inputValdiationTest {
    var valdiation = InputValdiation()

    @Test
    fun stringValidationNoInput() {
        assertEquals("", valdiation.stringValidaiton(" "))
    }
    @Test
    fun StringValidationToMuch(){
        var data = StringBuilder()
        var counter  = 0
        while(counter !=51){
            data.append("1")
            counter = counter + 1
        }
        assertEquals( " it to long", valdiation.stringValidaiton(data.toString()))
    }
    @Test
    fun StringValidationOnBoundary(){
        var data = StringBuilder()
        var counter  = 0
        while(counter !=50){
            data.append("1")
            counter = counter + 1
        }
        assertEquals( "", valdiation.stringValidaiton(data.toString()))
    }
    @Test
    fun StringValidationOneBellowTheMax(){
        var data = StringBuilder()
        var counter  = 0
        while(counter !=49){
            data.append("1")
            counter = counter + 1
        }
        assertEquals( "", valdiation.stringValidaiton(data.toString()))
    }
    @Test
    fun StringValidationNotAllowedCharcter(){
        assertEquals( " only allows a-z and 0-9 allowed", valdiation.stringValidaiton("@?||<>"))
    }
    @Test
    fun StringValidationNormalValidInput(){
        assertEquals( "", valdiation.stringValidaiton("hello"))
    }

}