package com.stoapps.myvoice;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

import android.app.Application;
import android.content.Context;

public class ParseApplication extends Application {
	
	private Context context;
	private int questionID;
	
	public int getQuestionID(){
		return questionID;
	}
	
	public void setQuestionID(int questionID){
		this.questionID = questionID;
	}
	
	  @Override
	  public void onCreate() {
	    super.onCreate();

	    // Add your initialization code here
	    Parse.initialize(this, "vrCjde2VN35QQ8valoOV4fBFMoi1K8FhajGWSa6q", "DPZCI0DnvK0Ak9bUP9C8WxT1yOzGwZK2uwBjpk10");
	    // Also, specify a default Activity to handle push notifications in this method as well
	    PushService.setDefaultPushCallback(this, MainActivity.class);
	    ParseUser.enableAutomaticUser();
	    ParseInstallation.getCurrentInstallation().put("mvid", ParseInstallation.getCurrentInstallation().getInstallationId());
	    ParseACL defaultACL = new ParseACL();
	      
	    // If you would like all objects to be private by default, remove this line.
	    defaultACL.setPublicReadAccess(true);
	    
	    ParseACL.setDefaultACL(defaultACL, true);
	  }
	
}
