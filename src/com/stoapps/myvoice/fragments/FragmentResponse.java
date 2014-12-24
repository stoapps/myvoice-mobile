package com.stoapps.myvoice.fragments;

import com.stoapps.myvoice.R;
import com.stoapps.myvoice.adapter.PagerViewAdapterResponse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;    
import android.view.View;
import android.view.ViewGroup;

public class FragmentResponse extends Fragment {
	
	ViewPager viewPager;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_response, container,false);
		viewPager = (ViewPager)view.findViewById(R.id.viewpagerr);
		viewPager.setAdapter(new PagerViewAdapterResponse(getActivity().getSupportFragmentManager()));
		return view;
	}
	
}