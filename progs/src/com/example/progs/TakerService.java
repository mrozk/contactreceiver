package com.example.progs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class TakerService extends Service {
	
	final String LOG_TAG = "myLogs";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG,"def");
		Toast toast = Toast.makeText(getApplicationContext(), 
				"Пора покормить кота!",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		return null;
	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		
	    return super.onStartCommand(intent, flags, startId);
	}

}
