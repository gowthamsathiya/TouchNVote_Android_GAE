package com.touchnvoteui.entities;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity(name="CLASSDETAILS")
@PersistenceCapable
public class ClassDetails {

	@PrimaryKey
	@Persistent
	private String classId;
	@Persistent
	private String classSeason;
	@Persistent
	private Date classStartDate;
	@Persistent
	private Date classEndDate;
	@Persistent
	private Date classTime;
	
	public String getClassId(){
		return this.classId;
	}
	public void setClassId(String classId){
		this.classId = classId;
	}
	public String getClassSeason(){
		return this.classSeason;
	}
	public void setClassSeason(String classSeason){
		this.classSeason = classSeason;
	}
	public Date getClassStartDate(){
		return this.classStartDate;
	}
	public void setClassStartDate(Date classStartDate){
		this.classStartDate = classStartDate;
	}
	public Date getClassEndDate(){
		return this.classEndDate;
	}
	public void setClassEndDate(Date classEndDate){
		this.classEndDate = classEndDate;
	}
	public Date getClassTime(){
		return this.classTime;
	}
	public void setClassTime(Date classtime){
		this.classTime = classtime;
	}
	
}
