package com.stoapps.myvoice.adapter;

import com.stoapps.myvoice.fragments.ResponseFragmentCurrent;
import com.stoapps.myvoice.fragments.ResponseFragmentHistory;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerViewAdapterResponse extends FragmentPagerAdapter {
	
	final int PAGE_COUNT = 2;
	private String[] tabTitles = new String[]{"Current","History"};
	Context context;
	
	public PagerViewAdapterResponse(FragmentManager fm){
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch(position){
		case 0 : ResponseFragmentCurrent rfc = new ResponseFragmentCurrent();
		return rfc;
		case 1 : ResponseFragmentHistory rfh = new ResponseFragmentHistory();
		return rfh;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGE_COUNT;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return tabTitles[position];
	}
	
}
