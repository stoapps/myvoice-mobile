package com.stoapps.myvoice.helper;

import com.stoapps.myvoice.R;
import com.stoapps.myvoice.properties.Question;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialogGraph extends Dialog implements 
	android.view.View.OnClickListener{
	
	public Activity c;
	Question question;
	public Dialog d;
	public ImageView imgClose,imgShare,imgDelete,imgEdit;
	public TextView txtQuestion,txtOptA,txtOptB,txtOptC,txtOptD,txtTotalVoters;
	
	public CustomDialogGraph(Activity a,Question question){
		
		super(a);
		this.c = c;
		this.question = question;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_graph);
		imgClose = (ImageView)findViewById(R.id.imgCloseD);
		imgShare = (ImageView)findViewById(R.id.imgShareGD);
		imgEdit = (ImageView)findViewById(R.id.imgEditGD);
		imgDelete = (ImageView)findViewById(R.id.imgDelGD);
		txtQuestion = (TextView)findViewById(R.id.txtQuestionGraphD);
		txtOptA = (TextView)findViewById(R.id.txtOptAGD);
		txtOptB = (TextView)findViewById(R.id.txtOptBGD);
		txtOptC = (TextView)findViewById(R.id.txtOptCGD);
		txtOptD = (TextView)findViewById(R.id.txtOptDGD);
		txtQuestion.setText(question.getQuestion()!=null?question.getQuestion():"");
		txtOptA.setText(question.getOptiona()!=null?question.getOptiona():"");
		txtOptB.setText(question.getOptionb()!=null?question.getOptionb():"");
		txtOptC.setText(question.getOptionc()!=null?question.getOptionc():"");
		txtOptD.setText(question.getOptiond()!=null?question.getOptiond():"");
		imgClose.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.imgCloseD:
			dismiss();
			break;
		}
	}
}
