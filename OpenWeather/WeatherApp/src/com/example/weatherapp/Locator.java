package com.example.weatherapp;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class Locator extends Service implements LocationListener {

	private final Context appContext;
	
	//Booleans for error checking.
	boolean isGpsOn = false;
	boolean isInternetOn = false;
	boolean canGetLocation = true;
	
	Location location;
	//Final values.
	double latitude;
	double longitude;
	
	private static final long DISTANCE_FOR_UPDATES = 10;
	private static final long UPDATE_TIME = 60000;
	
	protected LocationManager locationManager;
	
	public Locator(Context context)
	{
		this.appContext = context;
		getLocation();
	}
	
	public Location getLocation()
	{
		try
		{
			locationManager = (LocationManager) appContext.getSystemService(LOCATION_SERVICE);
			
			isGpsOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isInternetOn = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
			if(!isGpsOn && !isInternetOn){}
			else
			{
				this.canGetLocation = true;
				//Tries to find the location of the device using the network first.
				if(isInternetOn)
				{
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,UPDATE_TIME,DISTANCE_FOR_UPDATES, this);
					if(locationManager != null)
					{	
						Log.d("d", "Trying network");
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if(location != null)
						{
							latitude = location.getLatitude();
							longitude = location.getLongitude();
							Log.d("d", "Returned lat and long");
						}
					}
				}
				//If a network is not avaliable it uses the GPS
				else if(isGpsOn)
				{
					if(location == null)
					{
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_TIME, DISTANCE_FOR_UPDATES, this);
						if(locationManager != null)
						{
							Log.d("d", "Trying gps");
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if(location != null)
							{
								latitude = location.getLatitude();
								longitude = location.getLongitude();
								Log.d("d", "Returned lat and loong");
								
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return location;
	}
	//Getters for the two main variables.
	public String getLatitude()
	{
		location = getLocation();
		if(location != null)
		{
			latitude = location.getLatitude();
		}
		StringBuilder st = new StringBuilder();
		st.append(latitude);
		return st.toString();
	}
	public String getLongitude()
	{
		location = getLocation();
		if(location != null)
		{
			longitude = location.getLongitude();
		}
		StringBuilder st = new StringBuilder();
		st.append(longitude);
		return st.toString();
	}
	//Helper method.
	public boolean canGetLocation()
	{
		return this.canGetLocation();
	}
	//Setting allert if GPS is off.
	public void showSettingAlert()
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(appContext);
		alertDialog.setTitle("GPS is settings");
		alertDialog.setMessage("GPS is not enabled.Do you want to go to settings menu?");
		
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				appContext.startActivity(intent);
			}
		});
		
		alertDialog.setNegativeButton("Cance", new DialogInterface.OnClickListener()
		{	
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.cancel();	
			}
		});
		
		alertDialog.show();
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
