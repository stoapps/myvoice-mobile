package com.stoapps.myvoice.fragments;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONValue;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.stoapps.myvoice.R;
import com.stoapps.myvoice.ResponseActivity;
import com.stoapps.myvoice.adapter.QuestionsHistoryAdapter;
import com.stoapps.myvoice.helper.CustomDialogGraph;
import com.stoapps.myvoice.helper.DataHelper;
import com.stoapps.myvoice.helper.DatabaseHelper;
import com.stoapps.myvoice.properties.Question;
import com.stoapps.myvoice.properties.Questions;

public class QuestionCurrentFragment extends ListFragment{
	
	DatabaseHelper helper;
	QuestionsHistoryAdapter adapter;
	ListView lstQuestions;
	List<Questions> allQuestions;
	CustomDialogGraph customDialog;
	DataHelper dbHelper;
	ProgressDialog pDialog;
	LinkedHashMap<String, Object> lhm;
	
	private String url = "http://myvoice.cloudapp.net/myvoice/question/create";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_question_current, container,false);
		//lstQuestions = (ListView)view.findViewById(R.id.list);
		//allQuestions = new ArrayList<Questions>();
		//pDialog = 
		lhm = new LinkedHashMap<String, Object>();
		helper = new DatabaseHelper(getActivity());
		dbHelper = new DataHelper(getActivity());
		/*allQuestions = helper.getAllData();
		adapter = new QuestionsHistoryAdapter(getActivity(), R.layout.list_question_history, allQuestions);
		//lstQuestions.setAdapter(adapter);
		lstQuestions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int kk = arg2;
				int ll = kk;
				Toast.makeText(getActivity(), "Position : "+arg2, Toast.LENGTH_LONG).show();
			}
		});
		setListAdapter(adapter);*/
		setDataToAdapter();
		
		return view;
	}
	
	public void setDataToAdapter(){
		allQuestions = dbHelper.getAllQuestions();//helper.getAllData();
		adapter = new QuestionsHistoryAdapter(getActivity(), R.layout.list_question_history, allQuestions);
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.notifyDataSetChanged();
		/*adapter = new QuestionsHistoryAdapter(getActivity(), R.layout.list_question_history, allQuestions);
		setListAdapter(adapter);*/
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Questions question = allQuestions.get(position);
		String que = question.getQuestion()!=null?question.getQuestion():"";
		String optA = question.getOpta()!=null?question.getOpta():"";
		String optB = question.getOptb()!=null?question.getOptb():"";
		String optC = question.getOptc()!=null?question.getOptc():"";
		String optD = question.getOptd()!=null?question.getOptd():"";
		Toast.makeText(getActivity(), que, Toast.LENGTH_LONG).show();
		/*customDialog = new CustomDialogGraph(getActivity(), question);
		customDialog.show();*/
		QuestionerGraphFragment qgf = new QuestionerGraphFragment();
		Bundle args = new Bundle();
		args.putString("Question", que);
		args.putString("OptionA", optA);
		args.putString("OptionB", optB);
		args.putString("OptionC", optC);
		args.putString("OptionD", optD);
		qgf.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction()
		.replace(R.id.pager, qgf).commit();
	}

private class GetJSONData extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			ArrayList<String> lstOptions  = new ArrayList<String>();
//			lstOptions.add(edtOptionA.getText().toString());
//			lstOptions.add(edtOptionB.getText().toString());
//			lstOptions.add(edtOptionC.getText().toString());
//			lstOptions.add(edtOptionD.getText().toString());
			
			ArrayList<String> lstSh = new ArrayList<String>();
			lstSh.add("1234");
			
			//jsonObj = new JSONObject();
			/*try {
				jsonObj.put("options",lstOptions);
				jsonObj.put("questionId", "aka005");
				jsonObj.put("question", edtQuestion.getText());
				jsonObj.put("shareList", lstSh);
				jsonObj.put("userId", "1234");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			lhm.put("userid", "1000");
			lhm.put("question", "ll");
			lhm.put("userId", "1234");
			lhm.put("option", "oikl");
			
			 DefaultHttpClient httpclient = new DefaultHttpClient();
             HttpResponse response = null;
             HttpEntity entity = null;
             String responseString = null;
             HttpPost httpost = new HttpPost(url); 
             httpost.addHeader("Content-Type", "application/json");
             StringEntity reqEntity;
			try {
				String js = JSONValue.toJSONString(lhm);
				reqEntity = new StringEntity(js);
				reqEntity.setContentType("application/json");
	             httpost.setEntity(reqEntity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
             try {
				response = httpclient.execute(httpost);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             entity = response.getEntity();
             if (entity != null) {
                  try {
					responseString = EntityUtils.toString(response.getEntity());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
             }
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(pDialog.isShowing())
				pDialog.dismiss();
		}
		
	}
	
}
