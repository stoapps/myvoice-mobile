package com.stoapps.myvoice.fragments;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;
import com.stoapps.myvoice.R;

public class QuestionerGraphFragment extends Fragment implements
OnChartValueSelectedListener{
	
	ImageView imgClose,imgDelete,imgShare,imgEdit;
	TextView txtQuestion,txtOptA,txtOptB,txtOptC,txtOptD,txtTotalVotes;
	PieChart mChart; 
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_question_graph, container,false);
		String question = getArguments().getString("Question");
		String optionA = getArguments().getString("OptionA");
		String optionB = getArguments().getString("OptionB");
		String optionC = getArguments().getString("OptionC");
		String optionD = getArguments().getString("OptionD");
		mChart = (PieChart)view.findViewById(R.id.chart);
		 mChart.setHoleColor(Color.rgb(235, 235, 235));
		imgClose = (ImageView)view.findViewById(R.id.imgClose);
		imgDelete = (ImageView)view.findViewById(R.id.imgDelG);
		imgShare = (ImageView)view.findViewById(R.id.imgShareG);
		imgEdit = (ImageView)view.findViewById(R.id.imgEditG);
		txtQuestion = (TextView)view.findViewById(R.id.txtQuestionGraph);
		txtOptA = (TextView)view.findViewById(R.id.txtOptAG);
		txtOptB =(TextView)view.findViewById(R.id.txtOptBG);
		txtOptC = (TextView)view.findViewById(R.id.txtOptCG);
		txtOptD = (TextView)view.findViewById(R.id.txtOptDG);
		txtTotalVotes = (TextView)view.findViewById(R.id.txtTotalVotes);
		
		txtQuestion.setText(question);
		txtOptA.setText(optionA);
		txtOptB.setText(optionB);
		txtOptC.setText(optionC);
		txtOptD.setText(optionD);
		
		imgClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				QuestionFragment qf = new QuestionFragment();
				getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.pager, qf).commit();
			}
		});
		
		
		 mChart.setHoleRadius(60f);

	        mChart.setDescription("");

	        mChart.setDrawYValues(true);
	        mChart.setDrawCenterText(true);

	        mChart.setDrawHoleEnabled(true);

	        mChart.setRotationAngle(0);

	        // draws the corresponding description value into the slice
	        mChart.setDrawXValues(true);

	        // enable rotation of the chart by touch
	        mChart.setRotationEnabled(true);

	        // display percentage values
	        mChart.setUsePercentValues(true);
	        // mChart.setUnit(" €");
	        // mChart.setDrawUnitsInChart(true);

	        // add a selection listener
	        mChart.setOnChartValueSelectedListener(this);
	        // mChart.setTouchEnabled(false);

	        mChart.setCenterText("100/150 responded");
	        setData(3, 100);

	        mChart.animateXY(1500, 1500);
	        // mChart.spin(2000, 0, 360);

	        Legend l = mChart.getLegend();
	        l.setPosition(LegendPosition.RIGHT_OF_CHART);
	        l.setXEntrySpace(7f);
	        l.setYEntrySpace(5f);
		
		return view;
	}
	
	 private void setData(int count, float range) {

	        float mult = range;

	        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

	        // IMPORTANT: In a PieChart, no values (Entry) should have the same
	        // xIndex (even if from different DataSets), since no values can be
	        // drawn above each other.
	        for (int i = 0; i < count + 1; i++) {
	            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
	        }

	        ArrayList<String> xVals = new ArrayList<String>();

	        for (int i = 0; i < count + 1; i++)
	        {
	            xVals.add("OptionA");
	        	xVals.add("OptionB");
	        	xVals.add("OptionC");
	        	xVals.add("OptionD");
	        }
	        //PieDataSet set1 = new PieDataSet(yVals1, "Election Results");
	        PieDataSet set1 = new PieDataSet(yVals1, "");
	        set1.setSliceSpace(3f);
	        
	        // add a lot of colors

	        ArrayList<Integer> colors = new ArrayList<Integer>();
	        int c = Color.parseColor("#e86519");
	         int d = Color.parseColor("#0061FF");
	         int e = Color.parseColor("#e8c019");
	         int f = Color.parseColor("#cc3300");
	        //for (int c : ColorTemplate.VORDIPLOM_COLORS)
	            colors.add(c);

	       // for (int c : ColorTemplate.JOYFUL_COLORS)
	            colors.add(d);

	        //for (int c : ColorTemplate.COLORFUL_COLORS)
	            colors.add(e);

	        //for (int c : ColorTemplate.LIBERTY_COLORS)
	            colors.add(f);
	        
	        //for (int c : ColorTemplate.PASTEL_COLORS)
	            //colors.add(c);
	        
	        colors.add(ColorTemplate.getHoloBlue());

	        set1.setColors(colors);

	        PieData data = new PieData(xVals, set1);
	        mChart.setData(data);

	        // undo all highlights
	        mChart.highlightValues(null);

	        mChart.invalidate();
	    }

	@Override
	public void onValueSelected(Entry e, int dataSetIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
