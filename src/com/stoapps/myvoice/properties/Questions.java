package com.stoapps.myvoice.properties;

public class Questions {
	
	int id;
	String question;
	int status;
	String created_at;
	String qid,opta,optb,optc,optd;
	int shareCount;
	
	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public Questions(){
		
	}
	
	public Questions(String question){
		this.question = question;
	}
	
	public Questions(int id,String question){
		this.id = id;
		this.question = question;
	}
	
	public Questions(String question,String opta,String optb,String optc,String optd){
		this.question = question;
		this.opta = opta;
		this.optb = optb;
		this.optc = optc;
		this.optd = optd;
	}
	
	public Questions(String qid,String question,String opta,String optb,String optc,String optd,int status){
		this.question = question;
		this.opta = opta;
		this.optb = optb;
		this.optc = optc;
		this.optd = optd;
		this.qid = qid;
		this.status = status;
	}
	
	public Questions(String qid,String question,String opta,String optb,String optc,String optd){
		this.question = question;
		this.opta = opta;
		this.optb = optb;
		this.optc = optc;
		this.optd = optd;
		this.qid = qid;
	}
	
	public Questions(int id,String qid,String question,String opta,String optb,String optc,String optd,int shareCount){
		this.id = id;
		this.question = question;
		this.opta = opta;
		this.optb = optb;
		this.optc = optc;
		this.optd = optd;
		this.qid = qid;
		this.shareCount = shareCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getOpta() {
		return opta;
	}

	public void setOpta(String opta) {
		this.opta = opta;
	}

	public String getOptb() {
		return optb;
	}

	public void setOptb(String optb) {
		this.optb = optb;
	}

	public String getOptc() {
		return optc;
	}

	public void setOptc(String optc) {
		this.optc = optc;
	}

	public String getOptd() {
		return optd;
	}

	public void setOptd(String optd) {
		this.optd = optd;
	}
	
}
