package com.stoapps.myvoice.fragments;

import java.util.List;

import com.stoapps.myvoice.R;
import com.stoapps.myvoice.adapter.QuestionsHistoryAdapter;
import com.stoapps.myvoice.helper.DataHelper;
import com.stoapps.myvoice.helper.DatabaseHelper;
import com.stoapps.myvoice.properties.Question;
import com.stoapps.myvoice.properties.Questions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ResponseFragmentHistory extends Fragment {
	
	DatabaseHelper helper;
	QuestionsHistoryAdapter adapter;
	ListView lstQuestions;
	List<Questions> allQuestions;
	DataHelper dbHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_response_history, container,false);
		lstQuestions = (ListView)view.findViewById(R.id.lstResponseHistory);
		helper = new DatabaseHelper(getActivity());
		dbHelper = new DataHelper(getActivity());
		allQuestions = dbHelper.getAllQuestions();
		///adapter = new QuestionsHistoryAdapter(getActivity(), R.layout.list_question_history, allQuestions);
		lstQuestions.setAdapter(adapter);
		
		lstQuestions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Questions q = allQuestions.get(position);
				String qk = q.getQuestion().toString();
				String qll = qk;
			}
		});
		return view;
	}
	
}
