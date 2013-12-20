package com.touchnvoteui.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Answers {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String userName;
	@Persistent
	private String quizTitle;
	@Persistent
	private String answers1;
	@Persistent
	private String answers2;
	@Persistent
	private String answers3;
	@Persistent
	private String answers4;
	
	public Answers(String userName,String quizTitle,String answers1, String answers2, String answers3, String answers4){
		this.userName = userName;
		this.quizTitle = quizTitle;
		this.answers1 = answers1;
		this.answers2 = answers2;
		this.answers3 = answers3;
		this.answers4 = answers4;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	public String getAnswers1() {
		return answers1;
	}
	public void setAnswers1(String answers1) {
		this.answers1 = answers1;
	}
	public String getAnswers2() {
		return answers2;
	}
	public void setAnswers2(String answers2) {
		this.answers2 = answers2;
	}
	public String getAnswers3() {
		return answers3;
	}
	public void setAnswers3(String answers3) {
		this.answers3 = answers3;
	}
	public String getAnswers4() {
		return answers4;
	}
	public void setAnswers4(String answers4) {
		this.answers4 = answers4;
	}
	
	
}
