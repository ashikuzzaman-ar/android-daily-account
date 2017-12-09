package com.studevs.mobile.android.daily_account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	
	private EditText etUsername ;
	private EditText etPassword ;
	private Button btnSignup ;
	private Button btnLogin ;
	
	private void initialization(){
	
		this.etUsername = this.findViewById(R.id.etUsername);
		this.etPassword = this.findViewById(R.id.etPassword);
		this.btnLogin = this.findViewById(R.id.btnLogin);
		this.btnSignup = this.findViewById(R.id.btnSignup);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.initialization();
		
		this.btnSignup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(MainActivity.this, SignupActivity.class);
				startActivity(intent);
			}
		});
	}
}
