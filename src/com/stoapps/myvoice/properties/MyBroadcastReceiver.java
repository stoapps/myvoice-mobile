package com.stoapps.myvoice.properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.stoapps.myvoice.ParseApplication;
import com.stoapps.myvoice.QuestionerActivity;
import com.stoapps.myvoice.helper.DataHelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
	
	DataHelper dbHelper;
	ParseApplication pApplication;
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		dbHelper = new DataHelper(arg0);
		pApplication = (ParseApplication)arg0.getApplicationContext();
		
		 try {
			    JSONObject json = new JSONObject(arg1.getExtras().getString("com.parse.Data"));
			   
			    String a = json.get("questionId").toString();
			  // QuestionerActivity qa = new QuestionerActivity();
			   int qid = pApplication.getQuestionID();
			   String ql = String.valueOf(qid);
			   int k =  dbHelper.updateQuestions(qid, a);
			   //String q = dbHelper.getQuestion(qid);
			   //String hjhj = q;
			   Toast.makeText(arg0, ql, Toast.LENGTH_LONG).show();
			   
			   } catch (JSONException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			   }
	}
	
}
