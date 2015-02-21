package com.evaluator.group;


import android.os.Parcel;
import android.os.Parcelable;

import com.evaluator.R;



/**
 * @author Gilad Opher
 */
public enum CandidateStatus implements Parcelable{
	NOT_STARTED(R.string.not_started),
	STARTED(R.string.started),
	QUITED(R.string.quited),
	MEDICAL_QUITED(R.string.maediacl_quited),
	FINISH(R.string.finish);



	private int statusRes;



	private CandidateStatus(int statusRes){
		this.statusRes = statusRes;
	}



	public int getStatusRes(){
		return statusRes;
	}




	public static final Parcelable.Creator<CandidateStatus> CREATOR = new Parcelable.Creator<CandidateStatus>() {

		@Override
		public CandidateStatus createFromParcel(Parcel source){
			return CandidateStatus.values()[source.readInt()];
		}

		public CandidateStatus[] newArray(int size) {
			return new CandidateStatus[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(ordinal());
	}




}
