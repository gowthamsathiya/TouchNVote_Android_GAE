package com.touchnvoteui.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecuredLogin {
	private String firstName;
	private String lastName;
	@Id
	private String netID;
	private String password;
	private String securityQuestion1;
	private String securityQuestion2;
	private String securityQuestion3;
	private String securityQuestionAnswer1;
	private String securityQuestionAnswer2;
	private String securityQuestionAnswer3;
	private int userType;
		
	private String Q1="What is your favorite food?";
	private String Q2="What school did you attend for sixth grade?";
	private String Q3="Who was your childhood hero?";
	private String Q4="What is your grandmother\'s first name?";
	private String Q5="Where did you vacation last year?";
	private String Q6="What year did you graduate from High School?";
	private String Q7="What is your favorite sport?";
	private String Q8="Which city you like to visit?";
	private String Q9="What was your dream job as a child?";
	
	//SecurityQuestionList questionlist=new SecurityQuestionList();
	
	public SecuredLogin(){
		userType=1;
	}
	public SecuredLogin(String firstName,String lastName,String netID,String password,
			String securityQuestion1,String securityQuestion2,String securityQuestion3,
			String securityQuestionAnswer1,String securityQuestionAnswer2,String securityQuestionAnswer3 ){
		this.userType=1;
		this.firstName=firstName;
		this.lastName=lastName;
		this.netID=netID;
		this.password=password;
		this.securityQuestion1=securityQuestion1;
		this.securityQuestion2=securityQuestion2;
		this.securityQuestion3=securityQuestion3;
		this.securityQuestionAnswer1=securityQuestionAnswer1;
		this.securityQuestionAnswer2=securityQuestionAnswer2;
		this.securityQuestionAnswer3=securityQuestionAnswer3;
		
	}
	
	public String getQuestion(String qnNumber){
		return (qnNumber);
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	
	public String getNetID(){
		return this.netID;
	}
	
	public void setNetID(String netID){
		this.netID=netID;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getSecurityQuestion1(){
		//return questionlist.getQuestion(this.securityQuestion1);
		return getQuestion(this.securityQuestion1);
	}
	
	public void setSecurityQuestion1(String question1){
		this.securityQuestion1=question1;
	}
	
	public String getSecurityQuestion2(){
		//return questionlist.getQuestion(this.securityQuestion2);
		return getQuestion(this.securityQuestion2);
	}
	
	public void setSecurityQuestion2(String question2){
		this.securityQuestion2=question2;
	}
	
	public String getSecurityQuestion3(){
		//return questionlist.getQuestion(this.securityQuestion3);
		return getQuestion(this.securityQuestion3);
	}
	
	public void setSecurityQuestion3(String question3){
		this.securityQuestion3=question3;
	}
	
	public String getSecurityQuestionAnswer1(){
		return this.securityQuestionAnswer1;
	}
	
	public void setSecurityQuestionAnswer1(String answer1){
		this.securityQuestionAnswer1=answer1;
	}
	
	public String getSecurityQuestionAnswer2(){
		return this.securityQuestionAnswer2;
	}
	
	public void setSecurityQuestionAnswer2(String answer2){
		this.securityQuestionAnswer2=answer2;
	}
	
	public String getSecurityQuestionAnswer3(){
		return this.securityQuestionAnswer3;
	}
	
	public void setSecurityQuestionAnswer3(String answer3){
		this.securityQuestionAnswer3=answer3;
	}
	
	public int getType(){
		return this.userType;
	}
	
	public void setType(int type){
		this.userType=type;
	}
}
