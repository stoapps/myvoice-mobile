package com.stoapps.myvoice.adapter;

import java.util.List;

import com.stoapps.myvoice.R;
import com.stoapps.myvoice.R.layout;
import com.stoapps.myvoice.helper.DataHelper;
import com.stoapps.myvoice.properties.Question;
import com.stoapps.myvoice.properties.Questions;
import com.stoapps.myvoice.properties.QuestionsHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsHistoryAdapter extends ArrayAdapter<Questions> {
	
	private Context mContext;
	//List<Question> list;
	int res;
	Questions qh;
	List<Questions> l;
	int qid;
	DataHelper dbHelper;
	
	public QuestionsHistoryAdapter(Context mContext,int res,List<Questions> l){
		super(mContext,res,l);
		this.mContext = mContext;
		this.l = l;
		this.res = res;
		dbHelper = new DataHelper(mContext);
	}
	
	public QuestionsHistoryAdapter(Context mContext,int res,List<Questions> list,int i){
		super(mContext,res);
		this.mContext = mContext;
		this.l = list;
		this.res = res;
		dbHelper = new DataHelper(mContext);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		qh = l.get(position);
		String ka = qh.getQuestion();
		qid = qh.getId();
		String k = ka;
		View view = convertView;
		ViewHolder holder;
		if(view == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_question_history, parent,false);
			holder = new ViewHolder();
			holder.txtQuestion = (TextView)view.findViewById(R.id.txtQuestionLH);
			holder.txtOptA = (TextView)view.findViewById(R.id.txtOptALH);
			holder.txtOptB = (TextView)view.findViewById(R.id.txtOptBLH);
			holder.txtOptC = (TextView)view.findViewById(R.id.txtOptCLH);
			holder.txtOptD = (TextView)view.findViewById(R.id.txtOptDLH);
			holder.imgDel = (ImageView)view.findViewById(R.id.imgDel);
			holder.imgEdit = (ImageView)view.findViewById(R.id.imgEdit);
			holder.imgShare = (ImageView)view.findViewById(R.id.imgShare);
			view.setTag(holder);
			view.setBackgroundResource(R.drawable.rounder_corners);
		}else{
			holder = (ViewHolder)view.getTag();
		}
		holder.txtQuestion.setText(qh.getQuestion().toString()!=null?qh.getQuestion().toString():"");
		holder.txtOptA.setText(qh.getOpta()!=null?qh.getOpta():"");
		holder.txtOptB.setText(qh.getOptb()!=null?qh.getOptb():"");
		holder.txtOptC.setText(qh.getOptc()!=null?qh.getOptc():"");
		holder.txtOptD.setText(qh.getOptd()!=null?qh.getOptd():"");
		//view.setClickable(true);
		holder.imgDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "del : "+String.valueOf(qid), Toast.LENGTH_LONG).show();
				int kk = dbHelper.deleteQuestion(qid);
				int kl = kk;
			}
		});
		holder.imgEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		holder.imgShare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}
	
	private class ViewHolder{
		TextView txtQuestion,txtOptA,txtOptB,txtOptC,txtOptD;
		ImageView imgDel,imgEdit,imgShare;
	}
	
}
