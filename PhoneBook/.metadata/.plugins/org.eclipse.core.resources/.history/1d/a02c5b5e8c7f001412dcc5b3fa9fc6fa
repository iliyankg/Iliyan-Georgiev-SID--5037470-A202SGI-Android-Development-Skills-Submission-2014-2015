package com.example.phonebook;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends ActionBarActivity {

	SQLiteDatabase database; 
	ListView contactsList;
	public static ArrayList<String> contacts;
	
	String name;
	String surname;
	String number;
	
	String full;
	
	Cursor c;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        contacts = new ArrayList<String>();
        
        contactsList = (ListView) findViewById(R.id.contacts_list);
        
       
        
        database = openOrCreateDatabase("My_Try_At_Contacts",MODE_PRIVATE, null);
        
        //RESET CODE
        //database.execSQL("DROP TABLE IF EXISTS contact");
        
        database.execSQL("CREATE TABLE IF NOT EXISTS contact(name VARCHAR, surname VARCHAR, number VARCHAR);");
        
        //, id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL
        
        //Iterates through the contact table and creates the final string to be parsed to the list view.
        c = database.rawQuery("SELECT * FROM contact ORDER BY name", null);
        if(c.getCount() >= 1)
        {
        	while(c.moveToNext())
        	{
        	
        	name = c.getString(0);
        	surname = c.getString(1);
        	number = c.getString(2);
        	        	
        	full = "Name: "+ name + " " + surname + "\n" + "Number: " + number;
        	
        	contacts.add(full);
        	full = "";
        	}
        }
       	
     
        contactsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts));
   
        contactsList.setOnItemClickListener(new OnItemClickListener()
        {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        	{
        		Intent intent = new Intent(MainActivity.this, Contact.class);
        		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		
        		//Gets the string of the entry clicked on.
        		String tempFull = contacts.get(position);
        		
        		//Temporary helper variables.
        		int numOfWhite = 0;
        		int stringCursor = 0;
        		
        		//Iterates through the string, finds the third whitespace and sets the stringCursor to its
        		//position.
        		for(int i = 0; i <= tempFull.length() - 1; ++i)
        		{
        			if(numOfWhite == 3)
        				stringCursor = i+1;
        			if(Character.isWhitespace(tempFull.charAt(i)))
        				numOfWhite += 1;
        		}
        		
        		//Creates a substring based on the stringCursor that represents the number.
        		String tempNumber = tempFull.substring(stringCursor, tempFull.length());
        		
        		//Parses the number to the Contact activity as an extra variable.
           		intent.putExtra("number", tempNumber);
        		startActivity(intent);
        	}
        });
                
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.add_contact_action)
        {
        	Intent	intent	=	new Intent(MainActivity.this,
        	AddContact.class);
        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(intent);
        	return true;
        }
       
        return super.onOptionsItemSelected(item);
    }
}
