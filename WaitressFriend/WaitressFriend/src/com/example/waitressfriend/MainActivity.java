package com.example.waitressfriend;


import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	//Grid view allows for the menu to be expanded.
	GridView foodGrid;
	
	//List of local ordered items.
	static ArrayList<String> orders = new ArrayList<String>();
	
	//Arrays to be used in ListView
	public ArrayList<String> foodMenu = new ArrayList<String>();
		
	@Override
	protected void onCreate(Bundle	savedInstanceState)	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Populates the food menu.
		foodMenu.add("English Breakfast \n 4.99gbp");
		foodMenu.add("Burger and chips \n 3.99gbp");
		foodMenu.add("Bacon and eggs \n 2.49gbp");
		foodMenu.add("Cod and chips \n 4.99gbp");
		foodMenu.add("American pancake \n 2.49gbp");
		foodMenu.add("Grilled cheese toastie \n 1.99gbp");
				
		foodGrid = (GridView) findViewById(R.id.menuGrid);
		        
		foodGrid.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,foodMenu));
		//Adds selected element to local orders.        
		foodGrid.setOnItemClickListener(new OnItemClickListener()
		{
		   	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
		   	{
		   		//Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.foodname)).getText(), Toast.LENGTH_SHORT).show();
		   		orders.add(foodMenu.get(position));
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
        if(id == R.id.search_action)
        {
        	Intent	intent	=	new Intent(MainActivity.this,
			SecondActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("Key1",	"this	is	the	message");
			startActivity(intent);
        }
        if(id == R.id.order_action)
        {
           	Intent	intent	=	new Intent(MainActivity.this,
            Order.class);
        	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	intent.putExtra("Key1",	"this	is	the	message");
        	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
