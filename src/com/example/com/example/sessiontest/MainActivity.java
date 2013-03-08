package com.example.com.example.sessiontest;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;

public class MainActivity extends Activity {
	
	Button btnStart;
	Button btnEnd;
	Button btnEndHacked;
	Button btnEvent;
	Button btnDispatch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        EasyTracker.getInstance().setContext(this);
        GoogleAnalytics.getInstance(this).setDebug(true);
        
        btnStart = (Button) findViewById(R.id.btn_start);
        btnEnd = (Button) findViewById(R.id.btn_end);
        btnEndHacked = (Button) findViewById(R.id.btn_end_hacked);
        btnEvent = (Button) findViewById(R.id.btn_event);
        btnDispatch = (Button) findViewById(R.id.btn_dispatch);
        
        btnStart.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				EasyTracker.getTracker().setStartSession(true);
				EasyTracker.getTracker().sendEvent("Test", "Test", "Test", 0L);
			}
		});
        
        btnEnd.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				EasyTracker.getTracker().setStartSession(false);
				EasyTracker.getTracker().sendEvent("Test", "Test", "Test", 0L);
			}
		});
        
        btnEndHacked.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {	
				Map<String, String> data;
				data = EasyTracker.getTracker().constructEvent("Test", "Test", "Test", 0L);
				data.put("sessionControl", "end");
				EasyTracker.getTracker().send("event", data);
			}
		});

        btnEvent.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				EasyTracker.getTracker().sendEvent("Test", "Test", "Test", 0L);
			}
		});
        
        btnDispatch.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				EasyTracker.getInstance().dispatch();
			}
		});
        
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	EasyTracker.getInstance().activityStart(this);
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	EasyTracker.getInstance().activityStop(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
