package com.hexaware.entity;


public class Company {
	private int companyID;
	private String companyName;
	private String location;
	public Company()
	{
		
	}
	public Company(int companyID, String companyName, String location) {
		this.companyID = companyID;
		this.companyName = companyName;
		this.location = location;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	/*public void postJob(String jobTitle, String jobDescription,String jobLocation, float salary, String jobType)
	{
		IMPLEMENTED THIS METHOD IN DATABASEMANAGER CLASS
	}
	
	public List<JobListing> getJobs()
	{
			IMPLEMENTED THIS METHOD IN DATABASEMANAGER CLASS

		return null;
	}*/
	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", companyName=" + companyName + ", location=" + location + "]";
	}
	
}
