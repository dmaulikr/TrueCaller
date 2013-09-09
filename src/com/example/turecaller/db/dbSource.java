package com.example.turecaller.db;

import com.example.truecaller.ContactInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbSource {
	
	private static final String LOGTAG = "TAG";
	
	SQLiteOpenHelper helper;
	SQLiteDatabase database;

	public dbSource(Context cotext) {
		helper = new dbhelper(cotext);
	}
	
	public void open () {
		Log.i(LOGTAG, "DataBase Opened");
		database = helper.getWritableDatabase();
	}
	
	public void close(){
		Log.i(LOGTAG, "DataBase Closed");
		helper.close();
	}

	public ContactInfo create(ContactInfo contact) {
		// we use ContentValues to insert values pf the coulmns
		ContentValues  values = new ContentValues();
		values.put(dbhelper.COLUMN_Name, contact.getName());
		values.put(dbhelper.COLUMN_Phone_Number, contact.getPhone_number());
		// we execute the insert statment
		long insertId= database.insert(dbhelper.TABLE_Contact, null, values);
		contact.setId(insertId);
		return contact;
	}
	
}
