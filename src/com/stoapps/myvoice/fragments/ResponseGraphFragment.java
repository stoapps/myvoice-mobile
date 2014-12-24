package com.stoapps.myvoice.fragments;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONValue;

import com.stoapps.myvoice.R;
import com.stoapps.myvoice.ResponseActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ResponseGraphFragment extends Fragment {
	
	ImageView imgClose;
	TextView txtQuestion;
	RadioButton optA,optB,optC,optD;
	Button btnSubmit;
	ProgressDialog pDialog;
	LinkedHashMap<String, Object> lhm;
	String questionID = "";
	String selectedQuestion = "";
	String selectedOption = "";
	private String url = "http://myvoice.cloudapp.net/myvoice/answer/create";
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_response_graph, container,false);
		String question = getArguments().getString("QuestionR");
		String optionA = getArguments().getString("rOptionA");
		String optionB = getArguments().getString("rOptionB");
		String optionC = getArguments().getString("rOptionC");
		String optionD = getArguments().getString("rOptionD");
		selectedQuestion = question;
		questionID = getArguments().getString("qid");
		String qii = questionID;
		String qk = qii;
		lhm = new LinkedHashMap<String, Object>();
		btnSubmit = (Button)view.findViewById(R.id.btnResponseR);
		imgClose = (ImageView)view.findViewById(R.id.imgCloseR);
		txtQuestion = (TextView)view.findViewById(R.id.txtResponseGraphR);
		optA = (RadioButton)view.findViewById(R.id.txtOptAGR);
		optB = (RadioButton)view.findViewById(R.id.txtOptBGR);
		optC = (RadioButton)view.findViewById(R.id.txtOptCGR);
		optD = (RadioButton)view.findViewById(R.id.txtOptDGR);
		
		txtQuestion.setText(question);
		optA.setText(optionA);
		optB.setText(optionB);
		optC.setText(optionC);
		optD.setText(optionD);
		
		imgClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentResponse qf = new FragmentResponse();
				getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.pager, qf).commit();
			}
		});
		
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new GetJSONData().execute();
			}
		});
		
		optA.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				optB.setChecked(false);
				optC.setChecked(false);
				optD.setChecked(false);
				selectedOption = optA.getText().toString();
			}
		});
		
		optB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				optA.setChecked(false);
				optC.setChecked(false);
				optD.setChecked(false);
				selectedOption = optB.getText().toString();
			}
		});
		
		optC.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				optA.setChecked(false);
				optB.setChecked(false);
				optD.setChecked(false);
				selectedOption = optC.getText().toString();
			}
		});
		
		optD.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				optA.setChecked(false);
				optB.setChecked(false);
				optC.setChecked(false);
				selectedOption = optD.getText().toString();
			}
		});
		return view;
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
			lstSh.add("1000");
			
			/*jsonObject = new JSONObject();
			try {
				jsonObject.put("options",lstOptions);
				jsonObject.put("questionId", "aka005");
				jsonObject.put("question", "Hello");
				jsonObject.put("shareList", lstSh);
				jsonObject.put("userId", "1234");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			String sopt = selectedOption;
			String kkl = sopt;
			lhm.put("questionId", questionID);
			lhm.put("question", selectedQuestion);
			lhm.put("userId", "5678");
			lhm.put("option", selectedOption);
			
			 DefaultHttpClient httpclient = new DefaultHttpClient();
             HttpResponse response = null;
             HttpEntity entity = null;
             String responseString = null;
             HttpPost httpost = new HttpPost(url); 
             httpost.addHeader("Content-Type", "application/json");
             StringEntity reqEntity;
			try {
				String js = JSONValue.toJSONString(lhm);
				String klk = js;
				String klklk = klk;
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
