package com.example.phonebook;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends ActionBarActivity {
	
	Button cancel;
	Button add;
	
	EditText nameText;
	EditText surnameText;
	EditText numberText;
	
	SQLiteDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		
		//Buttons
		cancel = (Button) findViewById(R.id.cancel_button);
		add = (Button) findViewById(R.id.add_button);
		
		//Views
		nameText = (EditText) findViewById(R.id.name_add);
		surnameText = (EditText) findViewById(R.id.surname_add);
		numberText = (EditText) findViewById(R.id.number_add);
		
		database = openOrCreateDatabase("My_Try_At_Contacts",MODE_PRIVATE, null);
		
		add.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				//Checks if all fields have something in them.
				if(nameText.getText() != null || surnameText != null || numberText != null )
				{
					//Creates and runs the SQL command to add a row in the table.
					database.execSQL("INSERT INTO contact VALUES('"+nameText.getText()+"','" + surnameText.getText() + "','" + numberText.getText()+ "');");
					Toast toast = Toast.makeText(AddContact.this, "Added Entry" , Toast.LENGTH_SHORT);
					toast.show();
				}
				else
				{
					Toast toast = Toast.makeText(AddContact.this, "Fill all fields" , Toast.LENGTH_SHORT);
					toast.show();
				}
					
				
			}
		});
		
		//Cancel button returns the user back to the contacts list view.
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.contacts.clear();
				Intent	intent	=	new Intent(AddContact.this,	MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent	intent	=	new Intent(AddContact.this,	MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
