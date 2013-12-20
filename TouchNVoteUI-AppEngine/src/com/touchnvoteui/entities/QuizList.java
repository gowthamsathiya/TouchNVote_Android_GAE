package com.touchnvoteui.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable
public class QuizList {

	@Persistent
	@PrimaryKey
	private String quizId;
	@Persistent
	@Unique
	private String quizTitle;
	
	public QuizList(String id,String title){
		this.quizId = id;
		this.quizTitle = title;
	}
	public void setQuizId(String quizId){
		this.quizId = quizId;
	}
	public String getQuizId(){
		return quizId;
	}
	public void setQuizTitle(String title){
		this.quizTitle = title;
	}
	public String getQuizTitle(){
		return quizTitle;
	}
}
