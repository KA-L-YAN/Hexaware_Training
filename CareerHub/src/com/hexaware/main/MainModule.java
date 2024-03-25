package com.hexaware.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.hexaware.dao.*;
import com.hexaware.entity.Applicant;
import com.hexaware.entity.Company;
import com.hexaware.entity.JobApplication;
import com.hexaware.entity.JobListing;
import com.hexaware.entity.JobListing.JobType;
import com.hexaware.exception.*;

public class MainModule {
	static Scanner sc = new Scanner(System.in);

    

	public static void main(String[] args)
	{
		String nxt;
		String sub;
		DatabaseManager dm = new DatabaseManager();
		Applicant ap = new Applicant();
		Company comp = new Company();
		JobApplication ja = new JobApplication();
		JobListing jl = new JobListing();
		do
		{
		System.out.println("Choose the database you want to interact with : ");
		System.out.println("1. Applicant");
		System.out.println("2. Company");
		System.out.println("3. Job Application");
		System.out.println("4. Job Listing");
		int x = sc.nextInt();
		
			switch(x)
			{
			case 1:
			{
				System.out.println("Enter an Option : ");
				System.out.println("1. Create Profile");
				System.out.println("2. Get All Applicants");
				System.out.println("3. Apply for a Job");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					System.out.println("Enter Applicant ID: ");
					int id = sc.nextInt();
					ap.setApplicantID(id);
					System.out.println("Enter Applicant First name: ");
					String fn = sc.next();
					ap.setFirstName(fn);
					System.out.println("Enter Applicant Last Name: ");
					String ln = sc.next();
					ap.setLastName(ln);
					System.out.println("Enter Applicant Email: ");
					String email = sc.next();
					try {
			            dm.validateEmail(email);
			            System.out.println("Email address is valid. Proceed with registration.");
			            ap.setEmail(email);
			            } 
					catch (InvalidEmailFormatException e) 
					{
			            System.out.println("Error: " + e.getMessage());
			            System.exit(0);
			        }
					System.out.println("Enter Applicant Phone number: ");
					String phone = sc.next();
					ap.setPhone(phone);
					System.out.println("Enter Applicant resume: ");
					String res = sc.next();
					ap.setResume(res);
					
					dm.insertApplicant(ap);
					break;
				}
				
				case 2:
				{
					List<Applicant> app = new ArrayList<>();
					app = dm.getApplicants();
					Iterator<Applicant> it = app.iterator();
					while(it.hasNext()) {
						System.out.println(it.next());
					}
					break;
				}
				case 3:
				{
					System.out.println("Enter Applicant ID: ");
					int id = sc.nextInt();
					System.out.println("Enter Cover Letter: ");
					String cl = sc.next();
					dm.apply(id, cl);
					break;
				}
				
				default :
				{
					System.out.println("Enter correct Option");
				}
				break;
			}
				
		}
			
			case 2:
			{
				System.out.println("Enter an Option : ");
				System.out.println("1. Add a new Company");
				System.out.println("2. Get All Companies");
				System.out.println("3. Post job");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					System.out.println("Enter Company ID: ");
					int cid = sc.nextInt();
					comp.setCompanyID(cid);
					System.out.println("Enter Company name: ");
					String cn = sc.next();
					comp.setCompanyName(cn);
					System.out.println("Enter Location: ");
					String ln = sc.next();
					comp.setLocation(ln);
					
					dm.insertCompany(comp);
					break;
				}
				
				case 2:
				{
					List<Company> companies = new ArrayList<>();
					companies = dm.getCompanies();
					Iterator<Company> it = companies.iterator();
					while(it.hasNext()) {
						System.out.println(it.next());
					}
					break;
				}
				case 3:
				{
					System.out.println("Enter Job Title:");
					String title = sc.next();
					System.out.println("Give a brief description about the job:");
					String description = sc.next();
					System.out.println("Enter Job Location:");
					String location = sc.next();
					System.out.println("Enter salary:");
					Double salary = sc.nextDouble();
					System.out.println("Enter job type(PART_TIME,FULL_TIME,CONTRACT): ");
					String type = sc.next();
					
					dm.postJob(title, description, location, salary, type);
					
					break;
				}
				
				default :
				{
					System.out.println("Enter correct Option");
				}
				break;
			}
				
		}
			
			case 3:
			{
				System.out.println("Enter an Option : ");
				System.out.println("1. Create new Job Appliction");
				System.out.println("2. Get Job by job ID");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					System.out.println("Enter Application ID: ");
					int id = sc.nextInt();
					ja.setApplicantionID(id);
					System.out.println("Enter Job ID: ");
					int jid = sc.nextInt();
					jl.setJobID(jid);
					ja.setJobID(jl);
					System.out.println("Enter Applicant ID: ");
					int aid = sc.nextInt();
					ap.setApplicantID(aid);
					ja.setApplicantID(ap);
					System.out.println("Enter Date: ");
					String s = sc.next();
					LocalDate date = LocalDate.parse(s);
					ja.setApplicationDate(date);
					System.out.println("Enter Cover Letter: ");
					String c = sc.next();
					ja.setCoverLetter(c);
					
					dm.insertJobApplication(ja);
					break;
				}
				
				case 2:
				{
					System.out.println("Enter Job ID: ");
					int jid = sc.nextInt();
					
					dm.getApplicationsForJob(jid);
					break;
				}
				
				default :
				{
					System.out.println("Enter correct Option");
				}
				break;
			}
				
		}
			
			case 4:
			{
				System.out.println("Enter an Option : ");
				System.out.println("1. Create new Job");
				System.out.println("2. Get a list of Jobs Available");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					System.out.println("Enter Job ID: ");
					int id = sc.nextInt();
					jl.setJobID(id);
					System.out.println("Enter Company ID: ");
					int cid = sc.nextInt();
					comp.setCompanyID(cid);
					jl.setCompanyID(comp);
					
					System.out.println("Enter Job Title: ");
					String title = sc.next();
					jl.setJobTitle(title);
					System.out.println("Enter Job Description: ");
					String description = sc.next();
					jl.setJobDescription(description);
					System.out.println("Enter Job Location: ");
					String loc = sc.next();
					jl.setJobLocation(loc);
					System.out.println("Enter salary:");
					Double salary = sc.nextDouble();
					jl.setSalary(salary);
					System.out.println("Enter job type(PART_TIME,FULL_TIME,CONTRACT): ");
					String type = sc.next();
					type = type.toUpperCase();
					JobType jType = JobType.valueOf(type);
					jl.setJobType(jType);
					System.out.println("Enter Date: ");
					String s = sc.next();
					LocalDate date = LocalDate.parse(s);
					jl.setPostedDate(date);
					
					dm.insertJobListing(jl);;
					break;
				}
				
				case 2:
				{
					List<JobListing> jobs = new ArrayList<>();
					jobs = dm.getJobListings();
					Iterator<JobListing> it = jobs.iterator();
					while(it.hasNext()) {
						System.out.println(it.next());
					}
					break;
				}
				
				default :
				{
					System.out.println("Enter correct Option");
				}
				break;
			}
				
		}
			
			default :
			{
				System.out.println("Enter correct Option");
			}
			}
		System.out.println("Do You want to Continue ???");
		nxt = sc.next();
		}while(nxt.equals("Y")|| nxt.equals("y"));
		System.out.println("Thank you !!!");
		
		System.exit(0);
	}
}
