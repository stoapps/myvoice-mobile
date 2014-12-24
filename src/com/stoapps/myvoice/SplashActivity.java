package com.stoapps.myvoice;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends Activity {
	//Handler mHandler;
	ActionBar mBar;
	SharedPreferences mPrefs;
	String access_token;
	public static final String MyPREFERENCES = "MyPref" ;
	private static final String ACCESS_TOKEN = "Access_Tokens";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mBar = getActionBar();
        mBar.hide();
        mPrefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        access_token = mPrefs.getString(ACCESS_TOKEN, null);
        String at = access_token;
        String g = at;
         new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(access_token == null){
				Intent i = new Intent(SplashActivity.this,MainActivity.class);
				startActivity(i);
				finish();
				}else{
//					Intent i = new Intent(SplashActivity.this,MainActivity.class);
//					startActivity(i);
					Intent i = new Intent(SplashActivity.this,MainActivity.class);
					startActivity(i);
					finish();
				}
			}
		}, 5000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
        return super.onOptionsItemSelected(item);
    }
}
