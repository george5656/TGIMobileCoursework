package com.example.mobilecoursework.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobilecoursework.AdminAddCafeItem

class DatabaseHelper(context:Context) :SQLiteOpenHelper(context,"cafeDatabase.db",null,1){

var db : SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {


        val sQlCreateStament:String = "CREATE TABLE Customers ( "+
                "cusId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "cusFullName TEXT NOT NULL,"+
                "cusEmail TEXT NOT NULL,"+
                "cusPhoneNo TEXT NOT NULL,"+
                "cusUserName TEXT NOT NULL UNIQUE,"+
                "cusPassword  TEXT NOT NULL,"+
                "cusIsActive INTEGER NOT NULL);"


         db?.execSQL(sQlCreateStament)


             val sQlCreateStament2 = "CREATE TABLE Admin ("+
                 "adminId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                 "adminFullName	TEXT NOT NULL,"+
                 "adminEmail TEXT NOT NULL,"+
                 "adminPhoneNo TEXT NOT NULL,"+
                 "adminUserName TEXT NOT NULL UNIQUE,"+
                 "adminPassword  TEXT NOT NULL,"+
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
                            "orderData INTEGER NOT NULL,"+
                            "orderTime NTEGER NOT NULL,"+
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

       val sQlCreateStament8 = "CREATE TABLE Notfifcation ("+
           "notficationId INTEGER PRIMARY KEY AUTOINCREMENT,"+
           "cusId INTEGER NOT NULL,"+
           "Title TEXT NOT NULL,"+
           "Messeage TEXT NOT NULL,"+
           "sent INTEGER NOT NULL,"+
           "FOREIGN KEY(cusId) REFERENCES Customers(cusId));"
        db?.execSQL(sQlCreateStament8)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    fun getLoginDetails(userName:String):Cursor?{
        var query : String = "select * from Admin where adminUserName =" +"\""+ "$userName"+"\""
        return db.rawQuery(query, null)
    }

    fun createAdmin(userName: String, password: String, fullName:String, phoneNo:String, email:String, active: Int ):Long{
        var table : String = "Admin"
        var cv = ContentValues()
        cv.put("adminUserName",userName)
        cv.put("adminPassword",password)
        cv.put("adminFullName",fullName)
        cv.put("adminPhoneNo",phoneNo)
        cv.put("adminEmail",email)
        cv.put("adminIsActive",active)
        return db.insert(table,null,cv)
    }

fun getMenuItems():Cursor{

    var query : String = "select * from \"Product\""
    return db.rawQuery(query, null)
}
fun getMenuItemThatMatchName(name:String):Cursor{
    var query : String = "select * from \"Product\" where prodName = \"" + name  + "\""
return db.rawQuery(query, null)
}
    fun getMenuItemThatMatchPassedInWhere(where:String):Cursor{
        var query : String = "select * from \"Product\" where $where"
        return db.rawQuery(query, null)
    }
fun getAdminDetails(id:String?):Cursor{
    var query : String = "select * from \"Admin\" where adminId = \"" + id + "\""
    return db.rawQuery(query, null)
}
fun deleteCafeMenuItem(id:String?){
    db.delete("\"Product\"","productId = " + id ,null)
}
    fun getSpecificUser(id:String):Cursor{
        var query : String = "select * from \"Customers\" where cusId = \"" + id + "\""
        return db.rawQuery(query, null)
    }
    fun getFeedback():Cursor{
        var query : String = "select * from \"Feedback\""
        return db.rawQuery(query, null)
    }
fun getFeedbackCustomerWhere(where:String):Cursor{
    var query : String = "select * from \"Feedback\" Left Join \"Purchase\" On Feedback.orderId = Purchase.orderId where $where"
    return db.rawQuery(query, null)
}
fun getOrders():Cursor {
    var query: String = "select * from \"Purchase\""
    return db.rawQuery(query, null)
}
fun getAllCustomer():Cursor{
    var query : String = "select * from \"Customers\""
    return db.rawQuery(query, null)
}
fun getSpecificOrders (userName: String):Cursor{
    var query : String = "select * from \"Purchase\", \"Customers\" where (Purchase.cusId == Customers.cusId) AND Customers.cusUserName like \'%" + userName + "%\'"
    return db.rawQuery(query, null)
}
fun getSpecificOrderFromOrderId(id:String?):Cursor{
    var query : String = "select * from \"Purchase\" where orderId = \"" + id + "\""
    return db.rawQuery(query, null)
}
fun updateOrderStatus(status : String, id:String){
        var cv = ContentValues()
        cv.put("orderStatus",status)

    db.update("Purchase",cv,"orderId = " + id,null )
}
fun getSpecificCustomer(userName:String):Cursor{

    var query : String = "select * from \"Customers\" where cusUserName like \'%" + userName + "%\'"
    return db.rawQuery(query, null)
}
    fun getCustomerThatMatchesUserName(userName:String):Cursor{
        var query : String = "select * from \"Customers\" where cusUserName == \'" + userName + "\'"
        return db.rawQuery(query, null)
    }
fun getOrdersThatMatchWhere(where:String):Cursor {
    var query: String = "select * from \"Purchase\" where $where"
    return db.rawQuery(query, null)
}

  fun createNotification(cv : ContentValues){

db.insert("Notfifcation",null,cv)

  }
fun updateCafeMenuItem(cv:ContentValues, id:String){
    db.update("Product",cv,"productId = " + id,null )
}

fun updateAdminAccount(cv:ContentValues, id:String){
    db.update("Admin",cv,"adminId = " + id ,null)
}

fun createMenuItem(cv:ContentValues){
    db.insert("Product",null,cv)
}
fun getUserThatMatchCustomeWhere(where:String):Cursor{
    var query: String = "select * from \"Customers\" Left Join \"Purchase\" ON \"Customers.cusId\" = \"Purchase.cusId\" where $where"
    return db.rawQuery(query, null)
}

}




