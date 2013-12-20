package com.touchnvoteui.entities;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class Question {
@PrimaryKey
@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
private Long id;
@Persistent
private String quiztitle;
@Persistent
private String question;
@Persistent
private int type;
@Persistent
private List<String> answers = new ArrayList<String>();
@Persistent
private List<String> correctanswer = new ArrayList<String>();
@Persistent
private int count;

public Question(String quiztitle,String question,int type,List<String> answers,List<String> correctanswer2,int count){
	this.quiztitle = quiztitle;
	this.question = question;
	this.type = type;
	this.answers = answers;
	this.correctanswer = correctanswer2;
	this.setCount(count);
}

public String getQuizTitle(){
	return this.quiztitle;
}
public void setQuizTitle(String quiztitle){
	this.quiztitle = quiztitle;
}
public String getQuestion(){
	return this.question;
}
public void setQuestion(String question){
	this.question = question;
}
public int getType(){
	return this.type;
}
public void setType(int type){
	this.type = type;
}
public List<String> getAnswers(){
	return this.answers;
}
public void setAnswers(List<String> answers){
	this.answers = answers;
}
public List<String> getCorrectAnswer(){
	return this.correctanswer;
}
public void setCorrectAnswer(List<String> correctanswer){
	this.correctanswer = correctanswer;
}
public String toString(){
	return quiztitle+"///"+question+"///"+type+"///"+answers.toString()+"///"+correctanswer.toString()+"///"+count;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}
}
