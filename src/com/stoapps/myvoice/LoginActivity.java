package com.stoapps.myvoice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class LoginActivity extends Activity {
	
	private static String APP_ID = "1502876896646252";
	private Facebook mFacebook;
	private AsyncFacebookRunner mAsyncRunner;
	String FILE_NAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;
	private static final String MYPREFERENCES = "MyPref";
	private static final String ACCESS_TOKEN = "Access_Tokens";
	
	SharedPreferences sharedPreferences;
	
	ImageView loginButton;
	ActionBar mBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mBar = getActionBar();
		mBar.hide();
		loginButton = (ImageView)findViewById(R.id.imgFacebook);
		mFacebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(mFacebook);
		sharedPreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				loginToFacebook();
				getProfileInformation();
				getAccessToken();
			}
		});
	}
	
	public void loginToFacebook(){
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);
		
		if(access_token != null){
			mFacebook.setAccessToken(access_token);
		}
		
		if(expires != 0){
			mFacebook.setAccessExpires(expires);
		}
		
		if(!mFacebook.isSessionValid()){
			mFacebook.authorize(this,new String[]{"email","publish_stream"}, new DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onError(DialogError e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onComplete(Bundle values) {
					// TODO Auto-generated method stub
					SharedPreferences.Editor editor = mPrefs.edit();
					editor.putString("access_token", mFacebook.getAccessToken());
					editor.putLong("access_expires", mFacebook.getAccessExpires());
					editor.commit();
				}
				
				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void getProfileInformation(){
		mAsyncRunner.request("me", new RequestListener() {
			
			@Override
			public void onMalformedURLException(MalformedURLException e, Object state) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onIOException(IOException e, Object state) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFileNotFoundException(FileNotFoundException e, Object state) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFacebookError(FacebookError e, Object state) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onComplete(String response, Object state) {
				// TODO Auto-generated method stub
				Log.d("Profile", response);
				String json = response;
				try{
					JSONObject profile = new JSONObject(json);
					final String name = profile.getString("name");
					final String userid = profile.getString("id");
					final String email = profile.getString("email");
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(), "Name : "+name+" UserID : "+userid+" email : "+email, Toast.LENGTH_LONG).show();
						}
					});
				}catch(JSONException e){
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getAccessToken(){
		String access_token = mFacebook.getAccessToken();
		Editor edit = sharedPreferences.edit();
		edit.putString(ACCESS_TOKEN, access_token);
		edit.commit();
		
		Toast.makeText(getApplicationContext(), access_token, Toast.LENGTH_LONG).show();
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
