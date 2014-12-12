package com.example.waitressfriend;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SecondActivity extends ActionBarActivity {

	GridView drinkGrid;
	
	//Arrays to be used in ListView
	public ArrayList<String> drinkMenu;
	
	public static ArrayList<String> tempMenu;
	
	@Override
	protected void onCreate(Bundle	savedInstanceState)	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		drinkMenu = new ArrayList<String>();
		tempMenu = new ArrayList<String>();
		
		//Populates the drink menu.
		drinkMenu.add("Beer \n 2.99gbp");
		drinkMenu.add("Grape juice 1.50gbp");
		drinkMenu.add("Appe juice \n 1.50gbp");
		drinkMenu.add("Coca cola \n 1.49gbp");
		drinkMenu.add("German beer \n 2.99gbp");
		drinkMenu.add("Bulgarian beer \n 3.99gbp");
				
		drinkGrid = (GridView) findViewById(R.id.drinksGrid);
        
        drinkGrid.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drinkMenu));
        //Stores the ordered drinks in a temporary array list.
        drinkGrid.setOnItemClickListener(new OnItemClickListener()
        {
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        	{
        		tempMenu.add(drinkMenu.get(position));
        	}
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent	intent	=	new Intent(SecondActivity.this,
			MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("Key1",	"this	is	the	message");
			startActivity(intent);
			return true;
		}
		if(id == R.id.order_action)
		{
			Intent	intent	=	new Intent(SecondActivity.this,
			Order.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("Key1",	"this	is	the	message");
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
