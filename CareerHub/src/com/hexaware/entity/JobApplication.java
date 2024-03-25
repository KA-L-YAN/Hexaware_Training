package com.hexaware.entity;

import java.time.LocalDate;

public class JobApplication {
	private int applicantionID;
	private JobListing jobID;
	private Applicant applicantID;
	private LocalDate applicationDate;
	private String coverLetter;
	
	public JobApplication(int applicantionID, JobListing jobID, Applicant applicantID, LocalDate applicationDate,
			String coverLetter) {
		this.applicantionID = applicantionID;
		this.jobID = jobID;
		this.applicantID = applicantID;
		this.applicationDate = applicationDate;
		this.coverLetter = coverLetter;
	}
	public JobApplication() {
	}
	public int getApplicantionID() {
		return applicantionID;
	}
	public void setApplicantionID(int applicantionID) {
		this.applicantionID = applicantionID;
	}
	public JobListing getJobID() {
		return jobID;
	}
	public void setJobID(JobListing jobID) {
		this.jobID = jobID;
	}
	public Applicant getApplicantID() {
		return applicantID;
	}
	public void setApplicantID(Applicant applicantID) {
		this.applicantID = applicantID;
	}
	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	@Override
	public String toString() {
		return "JobApplication [applicantionID=" + applicantionID + ", jobID=" + jobID + ", applicantID=" + applicantID
				+ ", applicationDate=" + applicationDate + ", coverLetter=" + coverLetter + "]";
	}
	
}
