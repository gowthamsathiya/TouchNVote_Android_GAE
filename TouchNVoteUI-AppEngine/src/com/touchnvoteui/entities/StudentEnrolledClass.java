package com.touchnvoteui.entities;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//@Entity(name="STUDENT_ENROLLED_CLASS")
@PersistenceCapable
public class StudentEnrolledClass {

	@Persistent
	@PrimaryKey
	private String netId;
	@Persistent
	private String classid;
	
	public String getNetId(){
		return this.netId;
	}
	public void setNetId(String netId){
		this.netId = netId;
	}
	public String getClassId(){
		return this.classid;
	}
	public void setClassId(String classdetails){
		this.classid = classdetails;
	}
}
