package com.studevs.mobile.android.daily_account;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.studevs.mobile.android.daily_account.daos.DatabaseHelper;
import com.studevs.mobile.android.daily_account.models.User;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
	
	private EditText etFirstName;
	private EditText etLastName;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etRepeatPassword;
	private Button btnClear;
	private Button btnCreateUser;
	
	private DatabaseHelper databaseHelper;
	
	private void initializing() {
		
		if(this.databaseHelper == null){
			
			this.databaseHelper = new DatabaseHelper(this);
		}
		
		this.etFirstName = this.findViewById(R.id.etFirstName);
		this.etLastName = this.findViewById(R.id.etLastName);
		this.etUsername = this.findViewById(R.id.etUsername);
		this.etPassword = this.findViewById(R.id.etPassword);
		this.etRepeatPassword = this.findViewById(R.id.etRepeatPassword);
		this.btnClear = this.findViewById(R.id.btnClear);
		this.btnCreateUser = this.findViewById(R.id.btnCreateUser);
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
		setContentView(R.layout.activity_signup);
		
		this.initializing();
		
		this.etFirstName.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				if (etFirstName.getText().toString().trim().equals(null) || etFirstName.getText().toString().trim().equals("")) {
					
					etFirstName.setBackgroundColor(Color.RED);
				} else {
					
					etFirstName.setBackgroundColor(Color.WHITE);
				}
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
			
			}
		});
		
		this.etLastName.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				if (etLastName.getText().toString().trim().equals(null) || etLastName.getText().toString().trim().equals("")) {
					
					etLastName.setBackgroundColor(Color.RED);
				} else {
					
					etLastName.setBackgroundColor(Color.WHITE);
				}
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
			
			}
		});
		
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
		
		this.etRepeatPassword.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				if (etRepeatPassword.getText().toString().equals(null) || etRepeatPassword.getText().toString().equals("")) {
					
					etRepeatPassword.setBackgroundColor(Color.RED);
				} else {
					
					etRepeatPassword.setBackgroundColor(Color.WHITE);
				}
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
			
			}
		});
		
		this.btnClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				etFirstName.setText("");
				etFirstName.setBackgroundColor(Color.WHITE);
				etLastName.setText("");
				etLastName.setBackgroundColor(Color.WHITE);
				etUsername.setText("");
				etUsername.setBackgroundColor(Color.WHITE);
				etPassword.setText("");
				etPassword.setBackgroundColor(Color.WHITE);
				etRepeatPassword.setText("");
				etRepeatPassword.setBackgroundColor(Color.WHITE);
			}
		});
		
		this.btnCreateUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				if (!areFieldsNull(etFirstName, etLastName, etUsername, etPassword, etRepeatPassword)) {
					
					if (etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
						
						try{
							
							SQLiteDatabase database = databaseHelper.getWritableDatabase();
							ContentValues values = new ContentValues();
							values.put("first_name", etFirstName.getText().toString());
							values.put("last_name", etLastName.getText().toString());
							values.put("username", etUsername.getText().toString());
							values.put("password", etUsername.getText().toString());
							database.insert("user", null, values);
							Toast.makeText(getBaseContext(), "New user is created, Please login now!", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(SignupActivity.this, MainActivity.class);
							startActivity(intent);
						}catch (Exception e){
							
							Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
						}
					} else {
						
						Toast.makeText(getBaseContext(), "Passwords did not match!", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
}
