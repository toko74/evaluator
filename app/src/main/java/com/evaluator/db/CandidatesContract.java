package com.evaluator.db;

import android.content.ContentValues;
import android.provider.BaseColumns;
import android.support.annotation.StringRes;

import com.evaluator.group.CandidateStatus;



/**
 * @author Gilad Opher
 */
public final class CandidatesContract{



	public CandidatesContract(){}


	public static abstract class CandidateEntry implements BaseColumns{
		public static final String TABLE_NAME = "candidates";
		public static final String COLUMN_ENTITY_ID = "entryid";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_FAMILY_NAME = "family_name";
		public static final String COLUMN_STATUS = "status";
	}



	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String COMMA_SEP = ", ";


	public static final String SQL_CREATE_CANDIDATES_TABLE =
			"CREATE TABLE " + CandidateEntry.TABLE_NAME + " (" +
					CandidateEntry.COLUMN_ENTITY_ID + " INTEGER PRIMARY KEY, " +
					CandidateEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
					CandidateEntry.COLUMN_FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
					CandidateEntry.COLUMN_STATUS + INTEGER_TYPE +  " );";



	public static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + CandidateEntry.TABLE_NAME;




	public static String[] projection = {
			CandidateEntry._ID,
			CandidateEntry.COLUMN_ENTITY_ID,
			CandidateEntry.COLUMN_NAME,
			CandidateEntry.COLUMN_FAMILY_NAME,
			CandidateEntry.COLUMN_STATUS};


// Create a new map of values, where column names are the keys



}
