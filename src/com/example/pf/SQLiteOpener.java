package com.example.pf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpener extends SQLiteOpenHelper{

	public static final String DB = "test.sqlite3";
	public static final String DB_TABLE = "createData";
	public static final String DB_ATTR1 = "description";
	public static final String DB_ATTR2 = "amount";
	public static final String DB_ATTR3="category";
	public static final String DATABASE_TABLE = "create table "+DB_TABLE+"("+DB_ATTR1+" text,"+DB_ATTR2+" text,"+DB_ATTR3+" text)"; 
	

	
	public SQLiteOpener(Context context)
	{
		super(context,DB,null , 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(DATABASE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
}
