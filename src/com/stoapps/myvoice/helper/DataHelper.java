package com.stoapps.myvoice.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.stoapps.myvoice.properties.Questions;
import com.stoapps.myvoice.properties.Response;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
	
	//Logcat tag
	private static final String LOG = "DatabaseHelper";
	private Context context;
	//Database Version
	private static final int DATABASE_VERSION = 1;
	//DatabaseName
	private static final String DATABASE_NAME = "myvoicequestions";
	//Table Names
	private static final String TABLE_QUESTIONS = "questions";
	private static final String TABLE_RESPONSE = "response";
	//Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_QUESTION = "question";
	private static final String KEY_QUESTION_ID = "qid";
	private static final String KEY_CREATED_AT = "created_at";
	private static final String KEY_STATUS = "status";
	//questions table column names;
	private static final String KEY_OPT_A = "opta";
	private static final String KEY_OPT_B = "optb";
	private static final String KEY_OPT_C = "optc";
	private static final String KEY_OPT_D = "optd";
	private static final String KEY_SHARE_COUNT = "shares";
	//Response table columns
	private static final String KEY_USER_ID = "userid";
	private static final String KEY_OPTION = "option";
	//Questions table create statement
	private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE "+TABLE_QUESTIONS
			+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_QUESTION+" TEXT,"
			+KEY_OPT_A+" TEXT,"+KEY_OPT_B+" TEXT,"+KEY_OPT_C+" TEXT,"+KEY_OPT_D+" TEXT,"
			+KEY_QUESTION_ID+" TEXT,"+KEY_CREATED_AT+" DATETIME,"+KEY_STATUS+" INTEGER DEFAULT 0"+")";
	//Response table create statement
	private static final String CREATE_TABLE_RESPONSE = "CREATE TABLE "+TABLE_RESPONSE
			+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_QUESTION+" TEXT,"
			+KEY_QUESTION_ID+" TEXT,"+KEY_USER_ID+" TEXT,"+KEY_OPTION+" TEXT,"
			+KEY_CREATED_AT+" DATETIME"+KEY_STATUS+" INTEGER DEFAULT 0"+")";
	
	public DataHelper(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_QUESTIONS);
		db.execSQL(CREATE_TABLE_RESPONSE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_QUESTIONS);
		db.execSQL("DROPT TABLE IF EXISTS "+TABLE_RESPONSE);
		onCreate(db);
	}
	
	public void addQuestions(Questions questions){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION, questions.getQuestion());
		values.put(KEY_OPT_A, questions.getOpta());
		values.put(KEY_OPT_B, questions.getOptb());
		values.put(KEY_OPT_C, questions.getOptc());
		values.put(KEY_OPT_D, questions.getOptd());
		values.put(KEY_CREATED_AT, getDateTime());
		db.insert(TABLE_QUESTIONS, null, values);
		db.close();
	}
	
	public void addResponse(Response response){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION, response.getQuestion());
		values.put(KEY_QUESTION_ID, response.getQid());
		values.put(KEY_OPTION, response.getOption());
		values.put(KEY_USER_ID, response.getUserid());
		db.insert(TABLE_RESPONSE, null, values);
		db.close();
	}
	
	public int updateQuestions(int id,String qid){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION_ID, qid);
		//values.put(KEY_CREATED_AT, getDateTime());
		return db.update(TABLE_QUESTIONS, values, KEY_ID+" = ?", 
				new String[]{String.valueOf(id)});
	} 
	
	public int updateQuestionsTable(int id,String question,String opta,
			String optb,String optc,String optd){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION, question);
		values.put(KEY_OPT_A, opta);
		values.put(KEY_OPT_B, optb);
		values.put(KEY_OPT_C, optc);
		values.put(KEY_OPT_D, optd);
		values.put(KEY_CREATED_AT, getDateTime());
		return db.update(TABLE_QUESTIONS, values, KEY_ID+" = ?", 
				new String[]{String.valueOf(id)});
	}
	
	public int deleteQuestion(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_QUESTIONS, KEY_ID+" = ?", new String[]{Integer.toString(id)});
	}
	
	public List<Questions> getAllQuestions(){
		List<Questions> questions = new ArrayList<Questions>();
		String selectQuery = "SELECT * FROM "+TABLE_QUESTIONS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Questions que = new Questions();
				que.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
				que.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
				que.setQid(cursor.getString(cursor.getColumnIndex(KEY_QUESTION_ID)));
				que.setOpta(cursor.getString(cursor.getColumnIndex(KEY_OPT_A)));
				que.setOptb(cursor.getString(cursor.getColumnIndex(KEY_OPT_B)));
				que.setOptc(cursor.getString(cursor.getColumnIndex(KEY_OPT_C)));
				que.setOptd(cursor.getString(cursor.getColumnIndex(KEY_OPT_D)));
				//que.setShareCount(cursor.getInt(cursor.getColumnIndex(KEY_SHARE_COUNT)));
				que.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
				questions.add(que);
			}while(cursor.moveToNext());
		}
		return questions;
	}
	
	public List<Response> getAllResponse(){
		List<Response> res = new ArrayList<Response>();
		String selectQuery = "SELECT * FROM "+TABLE_RESPONSE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Response resp = new Response();
				resp.setRid(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
				resp.setOption(cursor.getString(cursor.getColumnIndex(KEY_OPTION)));
				resp.setQid(cursor.getString(cursor.getColumnIndex(KEY_QUESTION_ID)));
				resp.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
				resp.setUserid(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)));
				res.add(resp);
			}while(cursor.moveToNext());
		}
		return res;
	}
	
	public String getQuestion(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		/*String selectQuery = "SELECT * FROM "+TABLE_QUESTIONS+" WHERE "
				+KEY_ID+" = "+id;
		Log.e(LOG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		if(c != null)
			c.moveToFirst();
		Questions que = new Questions();
		que.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		que.setQuestion(c.getString(c.getColumnIndex(KEY_QUESTION)));
		que.setQid(c.getString(c.getColumnIndex(KEY_QUESTION_ID)));
		que.setOpta(c.getString(c.getColumnIndex(KEY_OPT_A)));
		que.setOptb(c.getString(c.getColumnIndex(KEY_OPT_B)));
		que.setOptc(c.getString(c.getColumnIndex(KEY_OPT_C)));
		que.setOptd(c.getString(c.getColumnIndex(KEY_OPT_D)));
		que.setShareCount(c.getInt(c.getColumnIndex(KEY_SHARE_COUNT)));
		que.setStatus(c.getInt(c.getColumnIndex(KEY_STATUS)));*/
		Cursor cursor =db.query(TABLE_QUESTIONS, new String[]{KEY_QUESTION_ID}, KEY_ID+"=?", new String[]{String.valueOf(id)}, null, null, null,null);
		//Questions que = new Questions();
		if(cursor != null)
			cursor.moveToFirst();
		String que = cursor.getString(0);
		return que;
	}
	
	public int getQuestionID(String question){
		Questions questions = new Questions();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_QUESTIONS, new String[]{
				KEY_ID,KEY_QUESTION
		}, KEY_QUESTION+"=?",new String[]{question}, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		return Integer.parseInt(cursor.getString(0));
	}
	
	public String getQID(String question){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_QUESTIONS, new String[]{
				KEY_QUESTION_ID
		}, KEY_QUESTION+"=?", new String[]{question}, null, null, null,null);
		if(cursor!=null)
			cursor.moveToFirst();
		return cursor.getString(0);
	}
	
	public Response getResponse(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_RESPONSE, new String[]{
		KEY_ID,KEY_QUESTION,KEY_QUESTION_ID,KEY_OPTION,KEY_USER_ID
		}, KEY_ID+"=?", new String[]{String.valueOf(id)}, null, null, null, null);
		if(cursor != null)
			cursor.moveToFirst();
		Response res = new Response(
				Integer.parseInt(cursor.getString(0)),
				cursor.getString(1),
				cursor.getString(2),
				cursor.getString(3),
				cursor.getString(4)
				);
		return res;
	}
	
	private String getDateTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
}
