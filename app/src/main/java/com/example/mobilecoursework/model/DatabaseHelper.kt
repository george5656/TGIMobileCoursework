package com.example.mobilecoursework.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context) :SQLiteOpenHelper(context,"cafeDatabase.db",null,1){

var db : SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {


        val sQlCreateStament:String = "CREATE TABLE Customers ( "+
                "cusId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "cusFullName TEXT NOT NULL,"+
                "cusEmail TEXT NOT NULL,"+
                "cusPhoneNo TEXT NOT NULL,"+
                "cusUserName TEXT NOT NULL UNIQUE,"+
                "cusPassword TEXT NOT NULL,"+
                "cusIsActive INTEGER NOT NULL);"


         db?.execSQL(sQlCreateStament)

             val sQlCreateStament2 = "CREATE TABLE Admin ("+
                 "adminId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                 "adminFullName	TEXT NOT NULL,"+
                 "adminEmail TEXT NOT NULL,"+
                 "adminPhoneNo TEXT NOT NULL,"+
                 "adminUserName TEXT NOT NULL UNIQUE,"+
                 "adminPassword TEXT NOT NULL,"+
                 "adminIsActive INTEGER NOT NULL);"
        db?.execSQL(sQlCreateStament2)

        val sQlCreateStament3 =  "CREATE TABLE Product ("+
                            "productId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            "prodName TEXT NOT NULL,"+
                            "prodPrice REAL NOT NULL,"+
                            "prodImage BLOB,"+
                            "prodAvailable	INTEGER NOT NULL);"

        db?.execSQL(sQlCreateStament3)
       // var tableName : String = "Order"
        val sQlCreateStament4 = "CREATE TABLE Purchase ("+
                            "orderId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            "cusId INTEGER NOT NULL,"+
                            "orderData TEXT NOT NULL,"+
                            "orderTime TEXT NOT NULL,"+
                            "orderStatus TEXT NOT NULL,"+
                            "FOREIGN KEY(cusId) REFERENCES Customers(cusId));"

        db?.execSQL(sQlCreateStament4)
        val sQlCreateStament5 =  "CREATE TABLE Payment ("+
                            "paymentId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            "orderId INTEGER NOT NULL,"+
                            "paymentType TEXT NOT NULL,"+
                            "Amount	INTEGER NOT NULL,"+
                            "paymentDate TEXT NOT NULL,"+
                            "FOREIGN KEY(orderId) REFERENCES Purchase (orderId));"

        db?.execSQL(sQlCreateStament5)
        val sQlCreateStament6=  "CREATE TABLE Feedback ("+
                                "feedbackId	INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                "cusId INTEGER NOT NULL,"+
                                "orderId INTEGER NOT NULL,"+
                                "feedback TEXT NOT NULL,"+
                                "rating INTEGER NOT NULL,"+
                                "FOREIGN KEY(cusId) REFERENCES Customers(cusId),"+
                                "FOREIGN KEY(orderId) REFERENCES Purchase (orderId));"

        db?.execSQL(sQlCreateStament6)
        val sQlCreateStament7 =   "CREATE TABLE OrderDetails ("+
                                "orderDetailsID	INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                "orderId INTEGER NOT NULL,"+
                                "prodId	INTEGER NOT NULL,"+
                                "FOREIGN KEY(orderId) REFERENCES Purchase (orderId),"+
                                "FOREIGN KEY(prodId) REFERENCES Product(productId));"

        db?.execSQL(sQlCreateStament7)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    fun getDataRow():Cursor?{
        var query : String = "select * from Admin"
        return db?.rawQuery(query, null)
    }


}




