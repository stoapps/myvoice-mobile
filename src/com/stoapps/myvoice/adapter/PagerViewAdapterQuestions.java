package com.stoapps.myvoice.adapter;

import com.stoapps.myvoice.fragments.QuestionCurrentFragment;
import com.stoapps.myvoice.fragments.QuestionerHistoryFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerViewAdapterQuestions extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 2;
	
	private String[] tabTitles = new String[]{"Current","History"};
	Context context;
	
	public PagerViewAdapterQuestions(FragmentManager fm){
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		switch(position){
		case 0 : QuestionCurrentFragment qcf = new QuestionCurrentFragment();
		return qcf;
		case 1 : QuestionerHistoryFragment qhf = new QuestionerHistoryFragment();
		return qhf;
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
