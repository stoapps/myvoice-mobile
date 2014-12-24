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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ResponseFragmentCurrent extends Fragment {
	
	DatabaseHelper helper;
	QuestionsHistoryAdapter adapter;
	ListView lstQuestions;
	List<Questions> allQuestions;
	DataHelper dbHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_response_current, container,false);
		lstQuestions = (ListView)view.findViewById(R.id.lstResponseCurrent);
		helper = new DatabaseHelper(getActivity());
		dbHelper = new DataHelper(getActivity());
		allQuestions = dbHelper.getAllQuestions();//helper.getAllData();
		adapter = new QuestionsHistoryAdapter(getActivity(), R.layout.list_question_history, allQuestions);
		lstQuestions.setAdapter(adapter);
		
		lstQuestions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Questions question = allQuestions.get(position);
				String que = question.getQuestion()!=null?question.getQuestion():"";
				String optA = question.getOpta()!=null?question.getOpta():"";
				String optB = question.getOptb()!=null?question.getOptb():"";
				String optC = question.getOptc()!=null?question.getOptc():"";
				String optD = question.getOptd()!=null?question.getOptd():"";
				String qid = question.getQid()!=null?question.getQid():"";
				Toast.makeText(getActivity(), que, Toast.LENGTH_LONG).show();
				
				ResponseGraphFragment rgf = new ResponseGraphFragment();
				Bundle args = new Bundle();
				args.putString("QuestionR", que);
				args.putString("rOptionA", optA);
				args.putString("rOptionB", optB);
				args.putString("rOptionC", optC);
				args.putString("rOptionD", optD);
				args.putString("qid", qid);
				rgf.setArguments(args);
				getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.pager, rgf).commit();
			}
		});
		return view;
	}
	
}
