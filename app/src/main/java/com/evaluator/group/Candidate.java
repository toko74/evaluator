package com.evaluator.group;

/**
 * @author Gilad Opher
 */
public class Candidate{



	private final int id;



	private String name;



	private CandidateStatus candidateStatus;



	public Candidate(int id){
		this.id = id;
		this.candidateStatus = CandidateStatus.STARTED;
		this.name = "Candidate Name";
	}


	public int getId(){
		return id;
	}



	public String getName(){
		return name;
	}



	public void setName(String name){
		this.name = name;
	}



	public CandidateStatus getCandidateStatus(){
		return candidateStatus;
	}



	public void setCandidateStatus(CandidateStatus candidateStatus){
		this.candidateStatus = candidateStatus;
	}


}
