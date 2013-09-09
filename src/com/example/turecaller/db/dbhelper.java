package com.example.turecaller.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbhelper extends SQLiteOpenHelper {

	// for test in logcat
	private static final String LOGTAG = "TAG";
	
	// DataBase Name & Version
	private static final String DATABASE_NAME = "truecaller.db";
	private static final int DATABASE_VERSION = 1;
	
	// Table name & it's Columns names
	public static final String TABLE_Contact = "Contact";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_Name = "name";
	public static final String COLUMN_Phone_Number = "phoneNumber";
	// SQL Statment for Create Table
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_Contact
			+ " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_Name + " TEXT, " + COLUMN_Phone_Number + " TEXT )";

	public dbhelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
		Log.i(LOGTAG, "Table has been created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS"+ TABLE_Contact);
		onCreate(db);
	}

}
