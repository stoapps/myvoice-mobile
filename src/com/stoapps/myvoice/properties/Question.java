package com.stoapps.myvoice.properties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "questions")
public class Question {
	
	@DatabaseField(generatedId = true)
	private int qid;
	@DatabaseField
	private String question;
	@DatabaseField
	private String optiona;
	@DatabaseField
	private String optionb;
	@DatabaseField
	private String optionc;
	@DatabaseField
	private String optiond;
	@DatabaseField(defaultValue = "0")
	private int status;
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getOptiona() {
		return optiona;
	}
	
	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}
	
	public String getOptionb() {
		return optionb;
	}
	
	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}
	
	public String getOptionc() {
		return optionc;
	}
	
	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}
	
	public String getOptiond() {
		return optiond;
	}
	
	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
}
