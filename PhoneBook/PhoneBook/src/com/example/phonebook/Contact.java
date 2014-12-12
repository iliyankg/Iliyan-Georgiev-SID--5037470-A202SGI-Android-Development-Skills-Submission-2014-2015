package com.example.phonebook;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends ActionBarActivity {

	TextView nameText;
	TextView surnameText;
	TextView numberText;
	
	Button delete;
	
	SQLiteDatabase database; 
	Cursor c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		nameText = (TextView) findViewById(R.id.name_display);
		surnameText = (TextView) findViewById(R.id.surname_display);
		numberText = (TextView) findViewById(R.id.number_display);
		
		delete = (Button) findViewById(R.id.delete_button);
		
		database = openOrCreateDatabase("My_Try_At_Contacts",MODE_PRIVATE, null);
		//Gets the row from the contact table with a matching number to the one sent from the MainActivity activity.
		c = database.rawQuery("SELECT * FROM contact WHERE number = '" + getIntent().getExtras().getString("number") + "'", null);
		while(c.moveToNext())
		{
			nameText.setText(c.getString(0));
			surnameText.setText(c.getString(1));
			numberText.setText(c.getString(2));
		}
		
		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Deleted the entry in question.
				database.delete("contact", "number ='" + getIntent().getExtras().getString("number") + "';", null);//
				Intent	intent	=	new Intent(Contact.this,	MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent	intent	=	new Intent(Contact.this,	MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
