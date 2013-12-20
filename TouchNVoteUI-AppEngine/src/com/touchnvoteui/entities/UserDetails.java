package com.touchnvoteui.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Key;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@PersistenceCapable
public class UserDetails {
	
   // @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
   // private Key key;
	@Persistent
	private String firstName;
	@Persistent
	private String lastName;
	@PrimaryKey
	@Persistent
	private String netID;
	@Persistent
	private String password;
	@Persistent
	private String securityQuestion1;
	@Persistent
	private String securityQuestion2;
	@Persistent
	private String securityQuestion3;
	@Persistent
	private String securityQuestionAnswer1;
	@Persistent
	private String securityQuestionAnswer2;
	@Persistent
	private String securityQuestionAnswer3;
	@Persistent
	private int userType;
	@Persistent
	private String classselected;
	
	public UserDetails(String firstName,String lastName,String netID,String password,String securityQuestion1,String securityQuestion2,String securityQuestion3,String securityQuestionAnswer1,String securityQuestionAnswer2,String securityQuestionAnswer3, String classselected){
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
		this.userType=1;
		this.classselected = classselected;
	}
	
	public UserDetails(){
		
	}
	
	/*
	public Key getKey() {
        return key;
    }
    */

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
		return this.securityQuestion1;
	}
	
	public void setSecurityQuestion1(String question1){
		this.securityQuestion1=question1;
	}
	
	public String getSecurityQuestion2(){
		//return questionlist.getQuestion(this.securityQuestion2);
		return this.securityQuestion2;
	}
	
	public void setSecurityQuestion2(String question2){
		this.securityQuestion2=question2;
	}
	
	public String getSecurityQuestion3(){
		//return questionlist.getQuestion(this.securityQuestion3);
		return this.securityQuestion3;
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

	public String getClassSelected() {
		return classselected;
	}

	public void setClassSelected(String classselected) {
		this.classselected = classselected;
	}
}
