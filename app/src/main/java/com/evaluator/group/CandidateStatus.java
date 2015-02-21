package com.evaluator.group;


import com.evaluator.R;



/**
 * @author Gilad Opher
 */
public enum CandidateStatus{
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
}
