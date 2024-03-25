package com.hexaware.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hexaware.entity.Applicant;
import com.hexaware.entity.Company;
import com.hexaware.entity.JobApplication;
import com.hexaware.entity.JobListing;
import com.hexaware.entity.JobListing.JobType;
import com.hexaware.exception.InvalidEmailFormatException;
import com.hexaware.util.DBConnection;

public class DatabaseManager {
	Connection con = DBConnection.getConnection();
	ResultSet rs;
	PreparedStatement ps;
	Statement stmt;
	private void initializeDatabase() {
		
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("CREATE DABATABASE IF NOT EXISTS CAREERHUB;");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY(\r\n"
            		+ "	COMPANYID INT PRIMARY KEY,\r\n"
            		+ "    COMPANYNAME VARCHAR(25),\r\n"
            		+ "    LOCATION VARCHAR(25)\r\n"
            		+ ");");
            stmt.executeUpdate("CREATE TABLE JOBLISTING (\r\n"
            		+ "    JOBID INT PRIMARY KEY,\r\n"
            		+ "    COMPANYID INT,\r\n"
            		+ "    JOBTITLE VARCHAR(25),\r\n"
            		+ "    JOBDESCRIPTION VARCHAR(50),\r\n"
            		+ "    JOBLOCATION VARCHAR(25),\r\n"
            		+ "    SALARY DECIMAL(10 , 2 ),\r\n"
            		+ "    JOBTYPE ENUM('FULL-TIME', 'PART-TIME','CONTRACT'),\r\n"
            		+ "    POSTEDDATE DATE,\r\n"
            		+ "    FOREIGN KEY (COMPANYID)\r\n"
            		+ "        REFERENCES COMPANY (COMPANYID)\r\n"
            		+ ");");
            stmt.executeUpdate("CREATE TABLE APPLICANT(\r\n"
            		+ "	APPLICANTID INT PRIMARY KEY,\r\n"
            		+ "    FIRSTNAME VARCHAR(25),\r\n"
            		+ "    LASTNAME VARCHAR(25),\r\n"
            		+ "    EMAIL VARCHAR(25),\r\n"
            		+ "	PHONE VARCHAR(25),\r\n"
            		+ "    RESUME VARCHAR(25)\r\n"
            		+ ");");
            stmt.executeUpdate("CREATE TABLE JOBAPPLICATION(\r\n"
            		+ "	APPLICATIONID INT PRIMARY KEY,\r\n"
            		+ "    JOBID INT,\r\n"
            		+ "    APPLICANTID INT,\r\n"
            		+ "    APPLICATIONDATE DATE,\r\n"
            		+ "	COVERLETTER VARCHAR(50),\r\n"
            		+ "    FOREIGN KEY (JOBID)\r\n"
            		+ "        REFERENCES JOBLISTING (JOBID),\r\n"
            		+ "	FOREIGN KEY (APPLICANTID)\r\n"
            		+ "        REFERENCES APPLICANT (APPLICANTID)\r\n"
            		+ ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertJobListing(JobListing job) {
        try {
        	
            ps = con.prepareStatement("insert into joblisting values (?,?,?,?,?,?,?,?)");
            ps.setInt(1, job.getJobID());
            ps.setInt(2, job.getCompanyID().getCompanyID());
            ps.setString(3,job.getJobTitle());
            ps.setString(4, job.getJobDescription());
            ps.setString(5, job.getJobLocation());
            ps.setDouble(6,job.getSalary());
            ps.setObject(7, job.getJobType());
            ps.setObject(8, job.getPostedDate());
            int rows = ps.executeUpdate();
            if(rows>0) System.out.println(rows+" inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCompany(Company company) {
        try {
            ps = con.prepareStatement("insert into company values(?,?,?);");
            ps.setInt(1, company.getCompanyID());
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getLocation());
            int rows = ps.executeUpdate();
            if(rows>0) System.out.println(rows+" inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertApplicant(Applicant applicant) {
        try {
            ps = con.prepareStatement("insert into applicant values(?,?,?,?,?,?)");
            ps.setInt(1, applicant.getApplicantID());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getLastName());
            ps.setString(4, applicant.getEmail());
            ps.setString(5, applicant.getPhone());
            ps.setString(6, applicant.getResume());
            int rows = ps.executeUpdate();
            if(rows>0) System.out.println(rows+" inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertJobApplication(JobApplication application) {
        try {
            ps = con.prepareStatement("insert into jobapplication values(?,?,?,?,?);");
            ps.setInt(1, application.getApplicantionID());
            ps.setInt(2, application.getJobID().getJobID());
            ps.setInt(3, application.getApplicantID().getApplicantID());
            ps.setObject(4, application.getApplicationDate());
            ps.setString(5, application.getCoverLetter());
            int rows = ps.executeUpdate();
            if(rows>0) System.out.println(rows+" inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobListing> getJobListings() {
        List<JobListing> jobListings = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from joblistings;");
            while (rs.next()) {
                JobListing job = new JobListing();
                Company comp = new Company();
                job.setJobID(rs.getInt("jobid"));
                comp.setCompanyID(rs.getInt("companyid"));
                job.setCompanyID(comp);
                job.setJobTitle(rs.getString("jobtitle"));
                job.setJobDescription(rs.getString("jobDescription"));
                job.setJobLocation(rs.getString("jobLocation"));
                job.setSalary(rs.getDouble("salary"));
                String t = rs.getString("jobtype");
			    JobType type = JobType.valueOf(t);
                job.setJobType(type);
                String s = rs.getString("posteddate");
                LocalDate date = LocalDate.parse(s);
                job.setPostedDate(date);
                
                jobListings.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobListings;
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from company;");
            while (rs.next()) {
                Company comp = new Company();
                comp.setCompanyID(rs.getInt("companyid"));
                comp.setCompanyName(rs.getString("companyname"));
                comp.setLocation(rs.getString("location"));
                
                companies.add(comp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public List<Applicant> getApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from applicant");
            while (rs.next()) {
                Applicant app = new Applicant();
                app.setApplicantID(rs.getInt("applicantid"));
                app.setFirstName(rs.getString("firstname"));
                app.setLastName(rs.getString("lastname"));
                app.setEmail(rs.getString("email"));
                app.setPhone(rs.getString("phone"));
                app.setResume(rs.getString("resume"));
                
                applicants.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    public List<JobApplication> getApplicationsForJob(int jobId) {
        List<JobApplication> jobApplications = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM Applications WHERE job_id = ?");
            ps.setInt(1, jobId);
            rs = ps.executeQuery();
            while (rs.next()) {
            	JobApplication ja = new JobApplication();
            	JobListing jl = new JobListing();
            	Applicant app = new Applicant();
            	ja.setApplicantionID(rs.getInt("applicationid"));
            	jl.setJobID(jobId);
            	ja.setJobID(jl);
            	app.setApplicantID(rs.getInt("applicantid"));
            	ja.setApplicantID(app);
            	String s = rs.getString("applicationdate");
            	LocalDate date = LocalDate.parse(s);
            	ja.setApplicationDate(date);
            	ja.setCoverLetter(rs.getString("coverletter"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobApplications;
    }
    
    public void apply(int applicantID,String coverLetter)
    {
    	try {
			ps = con.prepareStatement("insert into jobapplication(jobid,applicantid,applicationdate,coverletter) "
					+ "values(?,?,?,?);");
			System.out.println("Enter job ID from the list given below.");
			List<JobListing> jobs = getJobListings();
			System.out.println(jobs);
			Scanner sc = new Scanner(System.in);
			int jid = sc.nextInt();
			ps.setInt(1, jid);
			ps.setInt(2, applicantID);
			LocalDate date = LocalDate.now();
			ps.setObject(3, date);
			ps.setString(4, coverLetter);
			sc.close();
			int rows = ps.executeUpdate();
			if(rows>0) System.out.println(rows+" updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void postJob(String jobTitle, String jobDescription,String jobLocation, double salary, String jobType)
	{
		try {
			ps = con.prepareStatement("insert into joblisting(COMPANYID,JOBTITLE,JOBDESCRIPTION,JOBLOCATION,salary,JOBTYPE,POSTEDDATE)"
					+ "values(?,?,?,?,?,?,?);");
			System.out.println("Choose your company from the below list:");
			List<Company> comp = getCompanies();
			System.out.println(comp);
			Scanner sc = new Scanner(System.in);
			int cid = sc.nextInt();
			ps.setInt(1, cid);	
			ps.setString(2, jobTitle);
			ps.setString(3, jobDescription);
			ps.setString(4, jobLocation);
			ps.setDouble(5, salary);
			jobType = jobType.toUpperCase();
			JobType jType = JobType.valueOf(jobType);
			ps.setObject(6, jType);
			LocalDate date = LocalDate.now();
			ps.setObject(7, date);
			sc.close();
			int rows = ps.executeUpdate();
			if(rows>0) System.out.println(rows+" updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    public void validateEmail(String email) throws InvalidEmailFormatException 
    {
        if (email == null || !email.contains("@"))
        {
            throw new InvalidEmailFormatException("Email address must contain '@'.");
        }
        String[] parts = email.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) 
        {
            throw new InvalidEmailFormatException("Invalid email format.");
        }
        System.out.println("Email address validation successful.");
    }
    
}
