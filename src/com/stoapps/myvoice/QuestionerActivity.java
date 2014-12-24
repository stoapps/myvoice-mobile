package com.stoapps.myvoice;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import com.stoapps.clearedittext.MyEditText;
import com.stoapps.myvoice.helper.DataHelper;
import com.stoapps.myvoice.helper.DatabaseHelper;
import com.stoapps.myvoice.properties.Question;
import com.stoapps.myvoice.properties.Questions;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionerActivity extends Activity {
	
	MyEditText edtQuestion,edtOptionA,edtOptionB,edtOptionC,edtOptionD;
	ImageView btnOptions,btnSave,btnCancel;
	Button btnResponse;
	ImageView imgAddOptions;
	TextView txtOptionChA,txtOptionChB,txtOptionChC,txtOptionChD;
	int counter=1;
	int questionID;
	ParseApplication pApplication;
	
	ActionBar mActionBar;
	
	private ProgressDialog pDialog;
	private static String url = "http://myvoice.cloudapp.net/myvoice/question/create";
	
	JSONObject jsonObj;
	LinkedHashMap<String, Object> lhs;
	
	DatabaseHelper helper;
	
	DataHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questioner);
		pApplication = (ParseApplication)getApplicationContext();
		mActionBar = getActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		helper = new DatabaseHelper(getApplicationContext());
		dbHelper = new DataHelper(getApplicationContext());
		lhs = new LinkedHashMap<String, Object>();
		edtQuestion = (MyEditText)findViewById(R.id.edtQuestion);
		imgAddOptions = (ImageView)findViewById(R.id.imgAddOptions);
		edtOptionA = (MyEditText)findViewById(R.id.edtOptioA);
		edtOptionB = (MyEditText)findViewById(R.id.edtOptioB);
		edtOptionC = (MyEditText)findViewById(R.id.edtOptioC);
		edtOptionD = (MyEditText)findViewById(R.id.edtOptioD);
		txtOptionChA = (TextView)findViewById(R.id.txtOptChA);
		txtOptionChB = (TextView)findViewById(R.id.txtOptChB);
		txtOptionChC = (TextView)findViewById(R.id.txtOptChC);
		txtOptionChD = (TextView)findViewById(R.id.txtOptChD);
		btnSave = (ImageView)findViewById(R.id.btnSave);
		btnCancel = (ImageView)findViewById(R.id.btnCancel);
		btnResponse = (Button)findViewById(R.id.btnResponse);
		//btnResponse.setVisibility(View.INVISIBLE);
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgAddOptions.setVisibility(View.INVISIBLE);
				edtOptionA.setText("");
				edtOptionB.setText("");
				edtOptionC.setText("");
				edtOptionD.setText("");
				edtQuestion.setText("");
				counter = 1;
				visibleViews(0);
			}
		});
		
		imgAddOptions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//counter++;
				if(counter == 1){
					txtOptionChA.setVisibility(View.VISIBLE);
					edtOptionA.setVisibility(View.VISIBLE);
					edtOptionA.requestFocus();
					counter++;
				}
				else if(counter == 2){
					txtOptionChB.setVisibility(View.VISIBLE);
					edtOptionB.setVisibility(View.VISIBLE);
					edtOptionB.requestFocus();
					counter++;
				}
				else if(counter == 3){
					txtOptionChC.setVisibility(View.VISIBLE);
					edtOptionC.setVisibility(View.VISIBLE);
					edtOptionC.requestFocus();
					counter++;
				}
				else if(counter == 4){
					txtOptionChD.setVisibility(View.VISIBLE);
					edtOptionD.setVisibility(View.VISIBLE);
					edtOptionD.requestFocus();
					counter = 1;
				}
			}
		});
		
		btnResponse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(QuestionerActivity.this,ResponseActivity.class);
				startActivity(i);
			}
		});
		
		edtQuestion.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				if(arg3>0){
					imgAddOptions.setVisibility(View.VISIBLE);
				}else{
					imgAddOptions.setVisibility(View.INVISIBLE);
					edtOptionA.setText("");
					edtOptionB.setText("");
					edtOptionC.setText("");
					edtOptionD.setText("");
					counter = 1;
					visibleViews(0);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new GetJSONData().execute();
				
				if(edtQuestion.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), "Question should not be empty...", Toast.LENGTH_LONG).show();
					edtQuestion.requestFocus();
					return;
				}
				
				else if(edtOptionA.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), "option a should not be empty...", Toast.LENGTH_LONG).show();
					edtOptionA.requestFocus();
					return;
				}
				
				else if(edtOptionB.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), "option b should not be empty...", Toast.LENGTH_LONG).show();
					edtOptionB.requestFocus();
					return;
				}
				
				else if(edtOptionC.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), "option c should not be empty...", Toast.LENGTH_LONG).show();
					edtOptionC.requestFocus();
					return;
				}
				
				else if(edtOptionD.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), "option d should not be empty...", Toast.LENGTH_LONG).show();
					edtOptionD.requestFocus();
					return;
				}
				
				else{
					/*Question questions = new Question();
					questions.setQuestion(edtQuestion.getText().toString());
					questions.setOptiona(edtOptionA.getText().toString());
					questions.setOptionb(edtOptionB.getText().toString());
					questions.setOptionc(edtOptionC.getText().toString());
					questions.setOptiond(edtOptionD.getText().toString());
					helper.addData(questions);
					Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
					List<Question> getAllQ = helper.getAllData();
					int kk = getAllQ.size();
					int kll = kk;*/
					Questions questions = new Questions();
					questions.setQuestion(edtQuestion.getText().toString());
					questions.setOpta(edtOptionA.getText().toString());
					questions.setOptb(edtOptionB.getText().toString());
					questions.setOptc(edtOptionC.getText().toString());
					questions.setOptd(edtOptionD.getText().toString());
					dbHelper.addQuestions(questions);
					List<Questions> f = dbHelper.getAllQuestions();
					int kl = f.size();
					int kd = kl;
					questionID = dbHelper.getQuestionID(edtQuestion.getText().toString());
					pApplication.setQuestionID(questionID);
					
				}
				
			}
		});
		
		edtOptionA.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(count>0){
				}else{
					edtOptionB.setText("");
					edtOptionC.setText("");
					edtOptionD.setText("");
				}
			}
			
			
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		edtOptionB.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(count>0){
					
				}else{
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		edtOptionC.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(count>0){
				}else{
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		edtOptionD.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(count>0){
					btnSave.setVisibility(View.VISIBLE);
					btnCancel.setVisibility(View.VISIBLE);
				}else{
					btnSave.setVisibility(View.INVISIBLE);
					btnCancel.setVisibility(View.INVISIBLE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//Clear edit text content
	private void clearEditTextContent(){
		edtQuestion.setText("");
		edtOptionA.setText("");
		edtOptionB.setText("");
		edtOptionC.setText("");
		edtOptionD.setText("");
	}
	
	//To show the invisible views
	//visibility = 0(Invisible)&&visibility = 1(Visible)
	private void visibleViews(int visibilty){
		if(visibilty == 1){
			imgAddOptions.setVisibility(View.VISIBLE);
			edtOptionD.setVisibility(View.VISIBLE);
			btnSave.setVisibility(View.VISIBLE);
			btnCancel.setVisibility(View.VISIBLE);
		}else{
			imgAddOptions.setVisibility(View.INVISIBLE);
			edtOptionA.setVisibility(View.INVISIBLE);
			edtOptionB.setVisibility(View.INVISIBLE);
			edtOptionC.setVisibility(View.INVISIBLE);
			edtOptionD.setVisibility(View.INVISIBLE);
			btnSave.setVisibility(View.INVISIBLE);
			btnCancel.setVisibility(View.INVISIBLE);
			txtOptionChA.setVisibility(View.INVISIBLE);
			txtOptionChB.setVisibility(View.INVISIBLE);
			txtOptionChC.setVisibility(View.INVISIBLE);
			txtOptionChD.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questioner, menu);
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
		if(id == android.R.id.home){
			NavUtils.navigateUpFromSameTask(this);
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class GetJSONData extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(QuestionerActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			ArrayList<String> lstOptions  = new ArrayList<String>();
			lstOptions.add(edtOptionA.getText().toString());
			lstOptions.add(edtOptionB.getText().toString());
			lstOptions.add(edtOptionC.getText().toString());
			lstOptions.add(edtOptionD.getText().toString());
			
			ArrayList<String> lstSh = new ArrayList<String>();
			lstSh.add("1234");
			lstSh.add("1000");
			lstSh.add("5678");
			
			jsonObj = new JSONObject();
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
			lhs.put("userId", "1000");
			lhs.put("question", edtQuestion.getText().toString());
			lhs.put("options", lstOptions);
			lhs.put("shareList", lstSh);
			//lhs.put("questionId", "aka009");
			
			 DefaultHttpClient httpclient = new DefaultHttpClient();
             HttpResponse response = null;
             HttpEntity entity = null;
             @SuppressWarnings("unused")
			String responseString = null;
             HttpPost httpost = new HttpPost(url); 
             httpost.addHeader("Content-Type", "application/json");
             StringEntity reqEntity;
			try {
				String js = JSONValue.toJSONString(lhs);
				reqEntity = new StringEntity(js);
				reqEntity.setContentType("application/json");
	             httpost.setEntity(reqEntity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
             try {
				response = httpclient.execute(httpost);
			//String kkl = response
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
					String rs = responseString;
					String rsd = rs;
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
	
	public int getQuestionID(){
		int k  = questionID;
		int kl = k;
		return questionID;
	}
}
