package com.hexaware.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JobListing {
	 	private int jobID;
	    private Company companyID;
	    private String jobTitle;
	    private String jobDescription;
	    private String jobLocation;
	    private double salary;
	    private JobType jobType;
	    private LocalDate postedDate;
	    public enum JobType {
	    	FULL_TIME,PART_TIME,CONTRACT;
	    }
	    
	    public JobListing()
	    {
	    	
	    }
	    
	    public JobListing(int jobID,Company companyID,String jobTitle,String jobDescription,
	    		String jobLocation,double salary,JobType jobType,LocalDate postedDate) 
	    {
	        this.jobID = jobID;
	        this.companyID = companyID;
	        this.jobTitle = jobTitle;
	        this.jobDescription = jobDescription;
	        this.jobLocation = jobLocation;
	        this.salary = salary;
	        this.jobType = jobType;
	        this.postedDate = postedDate;
	        new ArrayList<>();
	    }

		public int getJobID() {
			return jobID;
		}

		public void setJobID(int jobID) {
			this.jobID = jobID;
		}

		public Company getCompanyID() {
			return companyID;
		}

		public void setCompanyID(Company companyID) {
			this.companyID = companyID;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public String getJobDescription() {
			return jobDescription;
		}

		public void setJobDescription(String jobDescription) {
			this.jobDescription = jobDescription;
		}

		public String getJobLocation() {
			return jobLocation;
		}

		public void setJobLocation(String jobLocation) {
			this.jobLocation = jobLocation;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public JobType getJobType() {
			return jobType;
		}

		public void setJobType(JobType jobType) {
			this.jobType = jobType;
		}

		public LocalDate getPostedDate() {
			return postedDate;
		}

		public void setPostedDate(LocalDate postedDate) {
			this.postedDate = postedDate;
		}

		public void setApplicants(List<Applicant> applicants) {
		}

		/*public void apply(int applicantID, String coverLetter) {
	       IMPLEMENTED THIS METHOD IN DATABASE MANAGER CLASS
	    }
		
	    public List<Applicant> getApplicants() {
	    	IMPLEMENTED THIS METHOD IN DATABASE MANAGER CLASS
	        return applicants;
	    }*/

		@Override
		public String toString() {
			return "JobListing [jobID=" + jobID + ", companyID=" + companyID + ", jobTitle=" + jobTitle
					+ ", jobDescription=" + jobDescription + ", jobLocation=" + jobLocation + ", salary=" + salary
					+ ", jobType=" + jobType + ", postedDate=" + postedDate + "]";
		}
	    
}
