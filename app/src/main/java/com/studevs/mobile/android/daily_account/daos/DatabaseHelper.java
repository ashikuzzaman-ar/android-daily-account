package com.studevs.mobile.android.daily_account.daos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.studevs.mobile.android.daily_account.models.User;

import java.io.Serializable;

/**
 * Created by ashik on 12/9/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	
	private final String USER_TABLE_CREATE = "CREATE TABLE user (" +
		"id INTEGER PRIMARY KEY, " +
		"first_name TEXT NOT NULL, " +
		"last_name TEXT NOT NULL, " +
		"username TEXT NOT NULL UNIQUE, " +
		"password TEXT NOT NULL)";
	private final String USER_TABLE_DROP = "DROP TABLE IF EXISTS user";
	
	public DatabaseHelper(Context context) {
		super(context, "daily_accounts", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		
		sqLiteDatabase.execSQL(USER_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		
		sqLiteDatabase.execSQL(USER_TABLE_DROP);
		this.onCreate(sqLiteDatabase);
	}
}
