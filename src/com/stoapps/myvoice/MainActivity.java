package com.stoapps.myvoice;

import java.util.ArrayList;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.stoapps.myvoice.adapter.TitleNavigationAdapter;
import com.stoapps.myvoice.fragments.FragmentFeeds;
import com.stoapps.myvoice.fragments.FragmentResponse;
import com.stoapps.myvoice.fragments.FragmentsSupport;
import com.stoapps.myvoice.fragments.QuestionCurrentFragment;
import com.stoapps.myvoice.fragments.QuestionFragment;
import com.stoapps.myvoice.fragments.SettingsFragment;
import com.stoapps.myvoice.properties.SpinnerNavItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements OnNavigationListener {
	
	private ActionBar actionBar;
	private ArrayList<SpinnerNavItem> navSpinner;
	private TitleNavigationAdapter sAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, "vrCjde2VN35QQ8valoOV4fBFMoi1K8FhajGWSa6q", "DPZCI0DnvK0Ak9bUP9C8WxT1yOzGwZK2uwBjpk10");
		ParseInstallation.getCurrentInstallation().put("mvid", ParseInstallation.getCurrentInstallation().getInstallationId());
		
		/*Parse.initialize(this, "vrCjde2VN35QQ8valoOV4fBFMoi1K8FhajGWSa6q", "DPZCI0DnvK0Ak9bUP9C8WxT1yOzGwZK2uwBjpk10");
		ParseInstallation.getCurrentInstallation().put("mvid", ParseInstallation.getCurrentInstallation().getInstallationId());
		String pid = ParseInstallation.getCurrentInstallation().getInstallationId().toString();
		String po = pid;
		String lo = po;*/
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		navSpinner = new ArrayList<SpinnerNavItem>();
		navSpinner.add(new SpinnerNavItem("Questions",R.drawable.question));
		navSpinner.add(new SpinnerNavItem("Response",R.drawable.response));
		//navSpinner.add(new SpinnerNavItem("Feeds",R.drawable.rss));
		//navSpinner.add(new SpinnerNavItem("Support",R.drawable.support));
		navSpinner.add(new SpinnerNavItem("Settings",R.drawable.settings_s));
		navSpinner.add(new SpinnerNavItem("Exit",R.drawable.exit_s));
		
		sAdapter = new TitleNavigationAdapter(getApplicationContext(), navSpinner);
		actionBar.setListNavigationCallbacks(sAdapter, this);
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
		if(id == R.id.action_new_question){
			Intent i = new Intent(MainActivity.this,QuestionerActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		FragmentManager manager = getSupportFragmentManager();
		switch(arg0){
		case 0 :
			manager.beginTransaction()
			.replace(R.id.pager, new QuestionFragment(),"QuestionFr").commit();
			return true;
		case 1 :
			manager.beginTransaction()
			.replace(R.id.pager, new FragmentResponse(),"ResponseFragment").commit();
			return true;
		case 2 :
			manager.beginTransaction()
			.replace(R.id.pager, new SettingsFragment(),"SettingFragment").commit();
			return true;
		case 3 :
			manager.beginTransaction()
			.replace(R.id.pager, new FragmentsSupport(),"SupportFragment").commit();
			return true;
		case 4 :
			manager.beginTransaction()
			.replace(R.id.pager, new FragmentFeeds(),"FeedsFragment").commit();
			return true;
		}
		return false;
	}
}
