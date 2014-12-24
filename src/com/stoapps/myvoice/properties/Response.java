package com.stoapps.myvoice.properties;

public class Response {
	
	int id;
	String question;
	String qid;
	String userid;
	String option;
	
	public Response(){
		
	}
	
	public Response(int rid,String question,String qid,String userid,String option){
		this.id = rid;
		this.question = question;
		this.qid = qid;
		this.userid = userid;
		this.option = option;
	}
	
	public Response(String question,String qid,String userid,String option){
		this.qid = qid;
		this.question = question;
		this.userid = userid;
		this.option = option;
	}

	public int getRid() {
		return id;
	}

	public void setRid(int rid) {
		this.id = rid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
}
