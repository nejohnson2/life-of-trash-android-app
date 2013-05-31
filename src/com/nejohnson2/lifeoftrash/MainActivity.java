package com.nejohnson2.lifeoftrash;

import java.util.Calendar;
import java.util.TimeZone;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.telephony.CellLocation;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.PendingIntent;


public class MainActivity extends Activity implements OnClickListener, LocationListener {
	
	private LocationManager lm;
//	private LocationListener locListenD;
	public String longitude;
	public String latitude;
	
	Button myButton;
	Button getLocationButton;
	SmsManager sms = SmsManager.getDefault();
	private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "1) onCreate - Called");
        
        Log.d(TAG, "2) onCreate - setting new timer");
        setTimer();
     
        // Setup buttons on main screen
//        myButton = (Button) this.findViewById(R.id.button1);
//        getLocationButton = (Button) this.findViewById(R.id.Button01);
        
        //Activate location system
        Log.d(TAG, "3) onCreate - getSystemService LOCATION_SERVICE");
    	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//    	Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

 
    	Log.d(TAG, "4) onCreate - Request location updates being called...");
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000l, 5.0f, (LocationListener) this);
        
//      lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000l, 5.0f, this);

        Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        
        Log.d(TAG, "5) onCreate - calling sendTrashLocation");
        sendTrashLocation(loc);
        
        Log.d(TAG, "The End.");
        
//        getLocationButton.setOnClickListener(new View.OnClickListener() {
//        	public void onClick(View v) {
//        		        	
//                TextView lat = (TextView)findViewById(R.id.TextView01);
//                TextView lon = (TextView)findViewById(R.id.TextView02);
//
//            	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                
//            	Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            	//Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            	
//        		lat.setText(Double.toString(loc.getLatitude()));
//        		lon.setText(Double.toString(loc.getLongitude()));               
//        		
//        		String latitude = (Double.toString(loc.getLatitude()));
//        		String longitude = (Double.toString(loc.getLongitude()));
//        		
//        		String message = latitude + ", " + longitude;
//        	
//        		sms.sendTextMessage("+1-646-430-9130", null, message,null, null);
//        		Toast.makeText(MainActivity.this, "You sent your location", Toast.LENGTH_LONG).show();
//        		
//        	}
//        	
//        });;
        
/*
        myButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		// automatically send an SMS
        		Toast.makeText(MainActivity.this, "You sent and SMS", Toast.LENGTH_LONG).show();
        		//myTextView.setText("Should be lat and long Variable");
        	}
        });
*/        

    }
    public void setTimer(){
	    //Log.d(TAG, "finish() - called");
        Log.d(TAG, "2a) setTime - Setting intent ");
    	// Setup intent for to trigger the broadcast receiver message
		Intent intent = new Intent(this, BroadCastReceiver.class);
	    intent.putExtra("alarm_message", "O'Doyle Rules!");
	    // In reality, you would want to have a static variable for the request code instead of 192837
	    PendingIntent sender = PendingIntent.getBroadcast(this.getApplicationContext(), 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	    
	    Log.d(TAG, "2b) setTime - Setting alarm manager");    
        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add 5 minutes to the calendar object
        cal.add(Calendar.MINUTE, 15);	
        
        //Long time = cal.getTimeInMillis();
        String t = String.valueOf(cal);
        
        Log.d(TAG, t);
        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);	
        Log.d(TAG, "2c) setTime - called finish ");
        finish();
    	
    }
	public void sendTrashLocation(Location location) {
		//Send an SMS with location coordinates
		
		Log.d(TAG, "5a) sendTrashLocation - Called");
		SmsManager sms = SmsManager.getDefault();

//    	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        
//        Log.d(TAG, "sendTrashLocation - getLastKnownLocation");
//    	Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//    	//Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);              
//	
		String latitude = (Double.toString(location.getLatitude()));
		String longitude = (Double.toString(location.getLongitude()));
		
		String message = latitude + ", " + longitude;
		Log.d(TAG, message);
		
		Log.d(TAG, "5b) sendTrashLocation - Sending SMS...");
		sms.sendTextMessage("+1-646-402-5754", null, message,null, null);
		//Toast.makeText(this, "You sent your location", Toast.LENGTH_LONG).show();
		Log.d(TAG, "5c) sendTrashLocation - SMS sent! Exiting sendTrashLocation...");
//		Log.d(TAG, "finish() - called");
//		finish();
	}

	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		Log.d(TAG, "onLocationChanged - Called");
//		
//		sendTrashLocation(location);
		

		finish();
        Log.d(TAG, "Exiting onLocationChanged function...");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

    protected void onStart(Bundle savedInstanceState) {
    	//uper.onStart(savedInstanceState);
    	Log.d(TAG, "onStart - Called");
	}
    
    protected void onRestart(Bundle savedInstanceState) {
    	Log.d(TAG, "onRestart - Called");
	}

    protected void onResume(Bundle savedInstanceState) {
    	Log.d(TAG, "onResume - Called");
    }

    protected void onPause(Bundle savedInstanceState) {
    	Log.d(TAG, "onPause - Called");
	}

    protected void onStop(Bundle savedInstanceState) {
    	Log.d(TAG, "onStop - Called");
	}

    protected void onDestroy(Bundle savedInstanceState) {
    	Log.d(TAG, "onDestroy - Called");
	}

}


