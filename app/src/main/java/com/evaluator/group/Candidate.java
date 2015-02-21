package com.evaluator.group;

/**
 * @author Gilad Opher
 */
public class Candidate{



	private int id;



	private String name;



	private String fName;



	private CandidateStatus candidateStatus;



	public Candidate(){}



	public Candidate(int id){
		this.id = id;
		this.candidateStatus = CandidateStatus.STARTED;
		this.name = "John";
		this.fName = "Doe";
	}


	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getfName(){
		return fName;
	}

	public void setfName(String fName){
		this.fName = fName;
	}

	public CandidateStatus getCandidateStatus(){
		return candidateStatus;
	}

	public void setCandidateStatus(CandidateStatus candidateStatus){
		this.candidateStatus = candidateStatus;
	}
}
