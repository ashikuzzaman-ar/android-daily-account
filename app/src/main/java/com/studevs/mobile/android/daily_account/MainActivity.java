package com.studevs.mobile.android.daily_account;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studevs.mobile.android.daily_account.daos.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
	
	private EditText etUsername;
	private EditText etPassword;
	private Button btnSignup;
	private Button btnLogin;
	
	private DatabaseHelper databaseHelper;
	
	private void initialization() {
		
		if (this.databaseHelper == null) {
			
			this.databaseHelper = new DatabaseHelper(this);
		}
		
		this.etUsername = this.findViewById(R.id.etUsername);
		this.etPassword = this.findViewById(R.id.etPassword);
		this.btnLogin = this.findViewById(R.id.btnLogin);
		this.btnSignup = this.findViewById(R.id.btnSignup);
	}
	
	private boolean areFieldsNull(EditText... editTexts) {
		
		boolean flag = false;
		List<String> list = new ArrayList<>();
		
		for (EditText editText : editTexts) {
			
			if (editText.getText().toString().equals(null) || editText.getText().toString().equals("")) {
				
				list.add(editText.getHint().toString());
				flag = true;
			}
		}
		
		String messageHeader;
		if (!list.isEmpty()) {
			
			messageHeader = list.get(0);
			if (list.size() > 1) {
				
				for (int i = 1; i < list.size(); i++) {
					
					messageHeader += (", " + list.get(i));
				}
			}
			
			Toast.makeText(this.getBaseContext(), messageHeader + " are not valid!", Toast.LENGTH_LONG).show();
		}
		
		return flag;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.initialization();
		
		this.etUsername.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				if (etUsername.getText().toString().trim().equals(null) || etUsername.getText().toString().trim().equals("")) {
					
					etUsername.setBackgroundColor(Color.RED);
				} else {
					
					etUsername.setBackgroundColor(Color.WHITE);
				}
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
			
			}
		});
		
		this.etPassword.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				if (etPassword.getText().toString().equals(null) || etPassword.getText().toString().equals("")) {
					
					etPassword.setBackgroundColor(Color.RED);
				} else {
					
					etPassword.setBackgroundColor(Color.WHITE);
				}
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
			
			}
		});
		
		this.btnSignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(MainActivity.this, SignupActivity.class);
				startActivity(intent);
			}
		});
		
		this.btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				try{
					
					SQLiteDatabase database = databaseHelper.getReadableDatabase();
					String[] projection = {"id, first_name, last_name, username, password"};
					String selection = "username = ? AND password = ?";
					String[] selectionArgs = {etUsername.getText().toString(),
						etPassword.getText().toString()};
					Cursor cursor = database.query(
						"user",
						projection,
						null, //selection,
						null, //selectionArgs,
						null,
						null,
						null
					);
					
					boolean loginFlag = false;
					//cursor.moveToFirst();
					while (cursor.moveToNext()){
						
						String username = cursor.getString(3);
						String password = cursor.getString(4);
						
						Toast.makeText(getBaseContext(), username, Toast.LENGTH_SHORT).show();
						
						if (username.equals(etUsername.getText().toString())&& password.equals(etPassword.getText().toString())) {
							
							Toast.makeText(getBaseContext(), "Login Success!", Toast.LENGTH_SHORT).show();
							loginFlag = true;
						}
					}
					
					if(!loginFlag){
						
						Toast.makeText(getBaseContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
					}
				}catch (Exception e){
					
					Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
