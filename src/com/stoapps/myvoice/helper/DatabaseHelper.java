package com.stoapps.myvoice.helper;

import android.content.Context;
import java.sql.SQLException;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.stoapps.myvoice.properties.Question;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private Context context;
	private static final String DATABASE_NAME = "myvoice.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<Question, Integer> simpleDao = null;
	private RuntimeExceptionDao<Question, Integer> simpleRuntimeDao = null;
	
	public DatabaseHelper(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		this.context = context;
	}
	
	public Dao<Question, Integer> getDao() throws SQLException{
		if(simpleDao == null){
			simpleDao = getDao(Question.class);
		}
		return simpleDao;
	}
	
	public RuntimeExceptionDao<Question,Integer> getSimpleDao(){
		if(simpleRuntimeDao == null){
			simpleRuntimeDao = getRuntimeExceptionDao(Question.class);
		}
		return simpleRuntimeDao;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		// TODO Auto-generated method stub
		try{
			TableUtils.createTable(arg1, Question.class);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		try {
			TableUtils.dropTable(arg1, Question.class, true);
			onCreate(arg0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public List<Question> getAllData(){
		DatabaseHelper helper = new DatabaseHelper(context);
		RuntimeExceptionDao<Question, Integer> simpleDao = helper.getSimpleDao();
		List<Question> list = simpleDao.queryForAll();
		return list;
	}
	
	public void deleteAll(){
		RuntimeExceptionDao<Question, Integer> dao = getSimpleDao();
		List<Question> list = dao.queryForAll();
		dao.delete(list);
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		simpleRuntimeDao = null;
	}
	
	public int addData(Question question){
		RuntimeExceptionDao<Question, Integer> dao = getSimpleDao();
		int i = dao.create(question);
		return i;
	}
	
	public void delete(int id) throws SQLException{
		RuntimeExceptionDao<Question, Integer> dao = getSimpleDao();
		DeleteBuilder<Question, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq("qid", id);
		deleteBuilder.delete();
	}
	
	public Question getQuestion(int id) throws SQLException{
		RuntimeExceptionDao<Question, Integer> dao = getSimpleDao();
		Question question = dao.queryForId(id);
		return question;
	}
	
	public int getQuestionCount(String question) throws SQLException{
		RuntimeExceptionDao<Question, Integer> dao = getSimpleDao();
		QueryBuilder<Question, Integer> queryBuilder = dao.queryBuilder();
		queryBuilder.where().eq("question", question);
		PreparedQuery<Question> preparedQuery = queryBuilder.prepare();
		int k = dao.query(preparedQuery).size();
		return k;
	}
	
	public int updateQuestion(int id,String question) throws SQLException{
		RuntimeExceptionDao<Question, Integer> dao = getSimpleDao();
		UpdateBuilder<Question, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq("id", id);
		updateBuilder.updateColumnValue("question", question);
		PreparedUpdate<Question> pu = updateBuilder.prepare();
		int k = dao.update(pu);
		return k;
	}
	
}
