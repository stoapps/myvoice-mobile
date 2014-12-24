package com.stoapps.myvoice;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResponseActivity extends Activity {
	
	TextView txtQuestionR,txtOptAr,txtOptBr,txtOptCr,txtOptDr;
	private ProgressDialog pDialog;
	private static String url = "http://myvoice.cloudapp.net/myvoice/answer/create";
	LinkedHashMap<String, Object> lhm;
	Button btnR;
	JSONObject jsonObject;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_response);
		lhm = new LinkedHashMap<String, Object>();
		txtQuestionR = (TextView)findViewById(R.id.txtQuestionR);
		txtOptAr = (TextView)findViewById(R.id.txtOptionAR);
		txtOptBr = (TextView)findViewById(R.id.txtOptionBR);
		txtOptCr = (TextView)findViewById(R.id.txtOptionCR);
		txtOptDr = (TextView)findViewById(R.id.txtOptionDR);
		btnR = (Button)findViewById(R.id.btnRe);
		btnR.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new GetJSONData().execute();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.response, menu);
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
		return super.onOptionsItemSelected(item);
	}
	
private class GetJSONData extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(ResponseActivity.this);
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
			lhm.put("userId", "1000");
			lhm.put("question", "how");
			lhm.put("options", "oikl");
			lhm.put("shareList", lstSh);
			lhm.put("questionId", "aka0011");
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
