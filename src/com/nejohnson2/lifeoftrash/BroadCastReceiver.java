package com.nejohnson2.lifeoftrash;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class BroadCastReceiver extends BroadcastReceiver {
	 public static final String CUSTOM_INTENT = "com.nejohnson2.testapp";
	 private static final String TAG = "BroadCastReceiver";
	 @Override
	 public void onReceive(Context context, Intent intent) {
	   try {
		 Log.d(TAG, "WAKE UP");
		 Log.d(TAG, "onReceive - Called");
	     Bundle bundle = intent.getExtras();
	     String message = bundle.getString("alarm_message");
	     
	     //MainActivity.sendTrashLocation();
	     Log.d(TAG, "Bundle Extras?");
	     
	     // Call the app to start again
         Log.d(TAG, "Setting new intent to call MainActivity.class");    
//         String conTax = context.toString();
//         Log.d(TAG, conTax);
	     Intent newIntent = new Intent(context.getApplicationContext(), MainActivity.class);
	     newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     context.startActivity(newIntent);

            
	     //Log.d(TAG, "sent new intent");
	     //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	     
	    } catch (Exception e) {
	     Toast.makeText(context, "There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
	     e.printStackTrace();

	    }
	 }

}
