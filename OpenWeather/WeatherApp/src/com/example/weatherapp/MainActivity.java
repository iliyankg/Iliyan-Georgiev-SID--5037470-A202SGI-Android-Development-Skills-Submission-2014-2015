package com.example.weatherapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	public static View mainLayout;
	private static boolean searchToggle;
	
	private String toFind;
	
	public static View searchView;
	
	
	
	//Object to handle the gps/network location finder.
	Locator gps = new Locator(this);
	
	
	//View Variables
	public static EditText placeToFind;
	
	//Image Field Declarations
	public static ImageView weatherState;
	public static ImageView dayNight;
	public static ImageView cloudCover;
	
	//Text Field Declarations
	public static TextView city;
    public static TextView country;
    public static TextView weather;
    public static TextView temperature;
    public static TextView windSpeed;
	
    public static Button findBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainLayout = (View) findViewById(R.id.mainlayout);
        
        searchToggle = false;
        
        searchView = (View) findViewById(R.id.searchBar);
        searchView.setVisibility(View.GONE);
        
        placeToFind = (EditText) findViewById(R.id.enteredTxt);
        
        //Click listener composes the url and parses it to the HttpCaller.
        findBtn = (Button) findViewById(R.id.find);
        findBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toFind = placeToFind.getText().toString();
				new HttpCaller().execute("http://api.openweathermap.org/data/2.5/weather?q=" + toFind);
			}
		});
        
        //Image field Initializations
        weatherState = (ImageView) findViewById(R.id.conditionsprite);
        dayNight = (ImageView) findViewById(R.id.sunsprite);
        cloudCover = (ImageView) findViewById(R.id.cloudsprite);
        
        //Text Field Initializations
        city = (TextView) findViewById(R.id.city);
        country = (TextView) findViewById(R.id.country);
        weather = (TextView) findViewById(R.id.weather);
        temperature = (TextView) findViewById(R.id.temp);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
                
        /*
         * Makes the AsyncTask .execute call. 
         * Tasks that potentially take a lot of time 
         * to finish must be executed
         * in a AsyncTask in order to prevent slow down 
         * in the application.
         */
        new HttpCaller().execute("http://api.openweathermap.org/data/2.5/weather?q=Coventry,GB");
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
        //Gets the location from gps/network and composes the url parsing it to the HttpCaller
        if(id == R.id.location_action)
        {
        		String lat = gps.getLatitude();
        		String lon = gps.getLongitude();
        		new HttpCaller().execute("http://api.openweathermap.org/data/2.5/weather?lat="+ lat + "&lon="+lon + "&cnt=1");
        }
      //Manages the searchbar showing up or not.
        if(id == R.id.search_action)
        {
        	
        	if(searchToggle == true)
        	{
        		searchToggle = false;
        	}
        	else
        	{
        		searchToggle = true;
        	}
        	
        	if(searchToggle == true)
            {
            	searchView.setVisibility(View.VISIBLE);
            }
            if(searchToggle == false)
            {
            	searchView.setVisibility(View.GONE);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

