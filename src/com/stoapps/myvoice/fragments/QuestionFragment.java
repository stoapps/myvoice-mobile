package com.stoapps.myvoice.fragments;

import com.stoapps.myvoice.R;
import com.stoapps.myvoice.adapter.PagerViewAdapterQuestions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuestionFragment extends Fragment{
	
	ViewPager viewPager;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_questioner, container,false);
		viewPager = (ViewPager)view.findViewById(R.id.viewpagerq);
		viewPager.setAdapter(new PagerViewAdapterQuestions(getActivity().getSupportFragmentManager()));
		return view;
	}
	
	
	
	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String message = intent.getStringExtra("message");
			Log.d("receiver", "Got message: " + message);
		}
		
	};
	
	public void onAttach(android.app.Activity activity) {
		super.onAttach(activity);
		LocalBroadcastManager.getInstance(activity).registerReceiver(
				mMessageReceiver, new IntentFilter("custom-event-name"));
	};
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
				mMessageReceiver);
		super.onDetach();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Fragment frag = getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + viewPager.getCurrentItem());
	    if(frag!=null&&viewPager.getCurrentItem()==0)
	        {
	           ((QuestionCurrentFragment) frag).onResume();
	        }
		/*QuestionCurrentFragment qcf = (QuestionCurrentFragment)getActivity()
				.getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.viewpagerq+":"+viewPager.getCurrentItem());
		QuestionerHistoryFragment qhf = (QuestionerHistoryFragment)getActivity()
				.getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.viewpagerq+":"+viewPager.getCurrentItem());
		qcf.onResume();
		qhf.onResume();*/
	}

	
}
