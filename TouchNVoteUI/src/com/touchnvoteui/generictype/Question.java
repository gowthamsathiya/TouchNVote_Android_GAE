package com.touchnvoteui.generictype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.util.Log;

public class Question {
private String quiztitle;	
private String question;
private int type;
private List<String> answers = new ArrayList<String>();
private List<String> correctanswer = new ArrayList<String>();
private int count;

public Question(String quiztitle,String question,int type,List<String> answers,List<String> correctanswer,int count){
	this.quiztitle = quiztitle;
	this.question = question;
	this.type = type;
	this.answers = answers;
	this.correctanswer = correctanswer;
	this.count = count;
}

public Question(){
	
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
	return quiztitle+"///"+question+"///"+type+"///"+answers.toString()+"///"+correctanswer.toString()+"///"+Integer.toString(count);
}
public void setCount(int count){
	this.count = count;
}
public int getCount(){
	return count;
}

public static Question toQuestion(String question){
	Question q = new Question();
	String que[] = question.split("///");
	q.quiztitle = que[0];
	Log.i("Inside question: title:", q.quiztitle);
	q.question = que[1];
	q.type = Integer.parseInt(que[2]);
	q.answers = new ArrayList<String>(Arrays.asList(que[3].split(",")));
	q.correctanswer = new ArrayList<String>(Arrays.asList(que[4].split(",")));
	q.count = Integer.parseInt(que[5]);
	Log.i("in toquestion() title", que[0]);
	Log.i("in toquestion() question", que[1]);
	Log.i("in toquestion() type", que[2]);
	Log.i("in toquestion() answers", que[3]);
	Log.i("in toquestion() correctanswer", que[4]);
	Log.i("in toquestion() count", que[5]);
	return q;
}
}
