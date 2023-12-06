package com.example.mobilecoursework.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context) :SQLiteOpenHelper(context,"cafeDatabase.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        var   SQlCreateStament :String = ""
                SQlCreateStament = "CREATE TABLE Customers ("+
                                "cusId INTEGER NOT NULL UNIQUE,"+
                                "cusFullName TEXT NOT NULL,"+
                                "cusEmail TEXT NOT NULL,"+
                                "cusPhoneNo TEXT NOT NULL,"+
                                "cusUserName TEXT NOT NULL UNIQUE,"+
                                "cusPassword TEXT NOT NULL,"+
                                "CusIsActive INTEGER NOT NULL,"+
                                "PRIMARY KEY(cusId AUTOINCREMENT));"





              SQlCreateStament = SQlCreateStament + "CREATE TABLE Admin (" +
                                        "adminId INTEGER NOT NULL UNIQUE,"+
                                        "adminFullName TEXT NOT NULL,"+
                                        "AdminEmail TEXT NOT NULL,"+
                                        "adminPhoneNo TEXT NOT NULL,"+
                                        "adminUserName TEXT NOT NULL UNIQUE,"+
                                        "adminPassword TEXT NOT NULL,"+
                                        "AdminIsActive INTEGER NOT NULL,"+
                                        "PRIMARY KEY(adminId AUTOINCREMENT));"


       SQlCreateStament = SQlCreateStament + "CREATE TABLE Product ("+
                            "productId INTEGER NOT NULL UNIQUE,"+
                            "prodName TEXT NOT NULL,"+
                            "prodPrice REAL NOT NULL,"+
                            "prodImage BLOB,"+
                            "prodAvailable	INTEGER NOT NULL,"+
                            "PRIMARY KEY(productId AUTOINCREMENT));"


        SQlCreateStament = "CREATE TABLE Order ("+
                            "orderId INTEGER NOT NULL UNIQUE,"+
                            "cusId INTEGER NOT NULL,"+
                            "orderData TEXT NOT NULL,"+
                            "orderTime TEXT NOT NULL,"+
                            "orderStatus TEXT NOT NULL,"+
                            "PRIMARY KEY(orderId AUTOINCREMENT),"+
                            "FOREIGN KEY(cusId) REFERENCES Customers(cusId));"


       SQlCreateStament =  SQlCreateStament + "CREATE TABLE Payment ("+
                            "paymentId INTEGER NOT NULL UNIQUE,"+
                            "orderId INTEGER NOT NULL,"+
                            "paymentType TEXT NOT NULL,"+
                            "Amount	INTEGER NOT NULL,"+
                            "paymentDate TEXT NOT NULL,"+
                            "FOREIGN KEY(orderId) REFERENCES Order(orderId),"+
                                "PRIMARY KEY(paymentId AUTOINCREMENT));"


        SQlCreateStament =  SQlCreateStament + "CREATE TABLE Feedback ("+
                                "feedbackId	INTEGER NOT NULL UNIQUE,"+
                                "cusId INTEGER NOT NULL,"+
                                "orderId INTEGER NOT NULL,"+
                                "feedback TEXT NOT NULL,"+
                                "rating INTEGER NOT NULL,"+
                                "PRIMARY KEY(feedbackId AUTOINCREMENT),"+
                                "FOREIGN KEY(cusId) REFERENCES Customers(cusId),"+
                                "FOREIGN KEY(orderId) REFERENCES Order(orderId));"


        SQlCreateStament =  SQlCreateStament + "CREATE TABLE OrderDetails ("+
                                "orderDetailsID	INTEGER NOT NULL UNIQUE,"+
                                "orderId INTEGER NOT NULL,"+
                                "prodId	INTEGER NOT NULL,"+
                                "PRIMARY KEY(orderDetailsID AUTOINCREMENT),"+
                                "FOREIGN KEY(orderId) REFERENCES Order(orderId),"+
                                "FOREIGN KEY(prodId) REFERENCES Product(productId));"
        db?.execSQL(SQlCreateStament)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}


