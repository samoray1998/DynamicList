package com.example.dynamiclist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyViewModel {
    private MySQLLightOpenHelper helper;
    private final  String my_table="Client";
    private SQLiteDatabase db;

    public MyViewModel(MySQLLightOpenHelper helper) {
        this.helper = helper;
       db=helper.getWritableDatabase();
    }
    public long AddToDB(Client c){
        ContentValues cv=new ContentValues();
        cv.put("Name",c.getName());
        cv.put("Age",c.getAge());
        long idc=db.insert(my_table,null,cv);
        return idc;
    }
    public void deletFList(int id){
        db.delete(my_table,"IDc=?",new String[]{String.valueOf(id)});
    }
    public void UpdateListe(int id,Client c){
        ContentValues cv=new ContentValues();
        cv.put("Name",c.getName());
        cv.put("Age",c.getAge());
        db.update(my_table,cv,"IDc=?",new String[]{String.valueOf(id)});
    }
    public ArrayList<Client> ListClient(){
        ArrayList<Client> lsClient=new ArrayList<Client>();
        Cursor cursor=db.query(my_table ,new String[]{"IDc","Name","Age"},null,null,null,null,"Name desc");
        cursor.moveToFirst();
        while ( !cursor.isAfterLast()){
            int IDc=cursor.getInt(0);
            String Name=cursor.getString(1);
            int Age=cursor.getInt(2);
            Client c=new Client(Name,Age);
            c.setIDc(IDc);
            lsClient.add(c);
            cursor.moveToNext();
        }
        return lsClient;

    }
}
