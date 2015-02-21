package com.evaluator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.evaluator.group.Candidate;
import com.evaluator.group.CandidateStatus;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Gilad Opher
 */
public class EvaluatorAppDB extends SQLiteOpenHelper{



	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 1;



	public static final String DATABASE_NAME = "Evaluator.db";



	public EvaluatorAppDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	public void onCreate(SQLiteDatabase db) {

		String sql = CandidatesContract.SQL_CREATE_CANDIDATES_TABLE;

		Log.d("create table", sql);

		db.execSQL(sql);
	}


	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
		// to simply to discard the data and start over
		db.execSQL(CandidatesContract.SQL_CREATE_CANDIDATES_TABLE);
		onCreate(db);
	}


	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}




	public long insert(Candidate candidate){

		ContentValues values = new ContentValues();
		values.put(CandidatesContract.CandidateEntry.COLUMN_ENTITY_ID, candidate.getId());
		values.put(CandidatesContract.CandidateEntry.COLUMN_NAME, candidate.getName());
		values.put(CandidatesContract.CandidateEntry.COLUMN_FAMILY_NAME, candidate.getfName());
		values.put(CandidatesContract.CandidateEntry.COLUMN_STATUS, candidate.getCandidateStatus().ordinal());

		// Insert the new row, returning the primary key value of the new row
		SQLiteDatabase db = getWritableDatabase();
		long newRowId;
		newRowId = db.insert(
				CandidatesContract.CandidateEntry.TABLE_NAME,
				null,
				values);

		db.close();
		return newRowId;
	}



	public List<Candidate> getCandidates(){
		SQLiteDatabase db = getReadableDatabase();

		List<Candidate> candidates = new ArrayList<>();
		String selectQuery = "SELECT * FROM " + CandidatesContract.CandidateEntry.TABLE_NAME;

		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Candidate candidate = new Candidate();
				candidate.setId(c.getInt(c.getColumnIndex(CandidatesContract.CandidateEntry.COLUMN_ENTITY_ID)));
				candidate.setName(c.getString(c.getColumnIndex(CandidatesContract.CandidateEntry.COLUMN_NAME)));
				candidate.setfName(c.getString(c.getColumnIndex(CandidatesContract.CandidateEntry.COLUMN_FAMILY_NAME)));

				int ordinal = c.getInt(c.getColumnIndex(CandidatesContract.CandidateEntry.COLUMN_STATUS));
				candidate.setCandidateStatus(CandidateStatus.values()[ordinal]);

				candidates.add(candidate);
			} while (c.moveToNext());
		}
		return candidates;

	}



}
