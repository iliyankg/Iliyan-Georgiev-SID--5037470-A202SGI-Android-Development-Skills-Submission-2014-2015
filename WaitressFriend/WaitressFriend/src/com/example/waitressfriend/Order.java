package com.example.waitressfriend;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Order extends ActionBarActivity {

	
	ListView orderList;		
	TextView price;
	//Variables for calculating and displaying the price.
	double priceInteger;
	String priceString;
	
	Button clear;
	
	//Holds final order.
	static ArrayList<String> orders;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		clear = (Button) findViewById(R.id.clear_button);
		
		price = (TextView) findViewById(R.id.finalPrice);
		
		//Clears the order
		clear.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				orders.clear();
				Intent intent = new Intent(Order.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
			}
			
		});
		
		orders= MainActivity.orders;
		
		if(SecondActivity.tempMenu.isEmpty())
		{
			
		}
		else
		{
			for(int i = 0; i<= SecondActivity.tempMenu.size() - 1; ++i)
			{
				orders.add(SecondActivity.tempMenu.get(i));
			}
		}
		
		
		orderList = (ListView) findViewById(R.id.menuGrid);
		        
		orderList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,orders));
		
		//For loop used to calculate the final price. (Hardcoded)
		for(int i = 0; i <= orders.size() - 1; ++i)
		{
			if(orders.get(i) == "English Breakfast \n 4.99gbp")
				priceInteger += 4.99; 
			if(orders.get(i) == "Burger and chips \n 3.99gbp")
				priceInteger += 3.99;
			if(orders.get(i) == "Bacon and eggs \n 2.49gbp")
				priceInteger += 2.49;
			if(orders.get(i) == "Cod and chips \n 4.99gbp")
				priceInteger += 4.99;
			if(orders.get(i) == "American pancake \n 2.49gbp")
				priceInteger += 2.49;
			if(orders.get(i) == "Grilled cheese toastie \n 1.99gbp")
				priceInteger += 1.99;
			if(orders.get(i) == "Beer \n 2.99gbp")
				priceInteger += 2.99;
			if(orders.get(i) == "Orange juice \n 1.50gbp")
				priceInteger += 1.50;
			if(orders.get(i) == "Appe juice \n 1.50gbp")
				priceInteger += 1.50;
			if(orders.get(i) == "Coca cola \n 1.49gbp")
				priceInteger += 1.49;
			if(orders.get(i) == "German beer \n 2.99gbp")
				priceInteger += 2.99;
			if(orders.get(i) == "Bulgarian beer \n 3.99gbp")
				priceInteger += 3.99;
			
			//String builder to convert final price to a string.
			StringBuilder stringer = new StringBuilder();
			stringer.append(priceInteger);
			price.setText(stringer.toString());
			
		}
	}
			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent	intent	=	new Intent(Order.this,
			MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("Key1",	"this	is	the	message");
			startActivity(intent);
			return true;
		}
		if(id == R.id.search_action)
		{
			Intent	intent	=	new Intent(Order.this,
			SecondActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("Key1",	"this	is	the	message");
			startActivity(intent);
			return true;
		}
		if(id == R.id.order_action)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
