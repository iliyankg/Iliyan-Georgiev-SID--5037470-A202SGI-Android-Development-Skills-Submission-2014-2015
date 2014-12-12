package com.example.weatherapp;


import java.io.InputStream;

import java.net.HttpURLConnection;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;


//HttpCaller extends AsyncTask and therefore inherits the doInBackground and onPostExecute functions.
public class HttpCaller extends AsyncTask<String,String,String> {
	
	//Holds the default URL when making a "city,country" http request
	 
		
	//Executes the the code inside when .execute(var) is called in the MainActivity
	protected String doInBackground(String... location)
	{
		String jsonString = null;
		
		HttpURLConnection connection = null;
		InputStream input = null;
		
		try
		{
			//Sets up the HttpClient makes the call and returns the json data as a String.
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			
			
			HttpGet httpGet = new HttpGet(location[0]);
			
			httpResponse = httpClient.execute(httpGet);
			
			httpEntity = httpResponse.getEntity();
			
			
			
			return EntityUtils.toString(httpEntity);
			
			
			
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
			}
			catch(Throwable t){}
			try
			{
				connection.disconnect();
			}
			catch(Throwable t){}
			
		}
		return null;
	}

	protected void onPostExecute(String result)
	{
		/*
		 * To avoid NullPointerException the *.setText() and image change
		 * functions are called here. This insures that they are not
		 * called before the AsyncTask completes resulting in them receiving 
		 * NULL values as arguments.
		 */
		
		
		
		super.onPostExecute(result);
		
		
		
		JSONpasser reader = new JSONpasser(result);
		
		MainActivity.city.setText(reader.getCity());
        MainActivity.country.setText(reader.getCountry());
        MainActivity.weather.setText(reader.getWeather());
        MainActivity.temperature.setText(reader.getTemp() + (char) 0x00B0 + "C");
        MainActivity.windSpeed.setText(reader.getWindSpeed() + "Mph");
        
        String id = reader.getId();
        
        if(id.contains("n"))
        {
        	MainActivity.dayNight.setImageResource(R.drawable.moon);
        	MainActivity.mainLayout.setBackgroundResource(R.drawable.night);
        }
        if(id.contains("d"))
        {
        	MainActivity.dayNight.setImageResource(R.drawable.sun);
        	MainActivity.mainLayout.setBackgroundResource(R.drawable.day);
        }
        if(id.contains("01"))
        {
        	MainActivity.weatherState.setVisibility(View.GONE);
        	MainActivity.cloudCover.setVisibility(View.GONE);
        }
        if(id.contains("02") || id.contains("03") || id.contains("04"))
        {
        	MainActivity.weatherState.setVisibility(View.GONE);
        	MainActivity.cloudCover.setImageResource(R.drawable.clouds);
        }
        if(id.contains("09") || id.contains("10"))
        {
        	MainActivity.cloudCover.setImageResource(R.drawable.clouds);
        	MainActivity.weatherState.setImageResource(R.drawable.rain);
        }
        if(id.contains("11"))
        {
        	MainActivity.cloudCover.setImageResource(R.drawable.clouds);
        	MainActivity.weatherState.setImageResource(R.drawable.lightning);
        }
        if(id.contains("13"))
        {
        	MainActivity.cloudCover.setImageResource(R.drawable.clouds);
        	MainActivity.weatherState.setImageResource(R.drawable.snow);
        }
        
        
	}
}
