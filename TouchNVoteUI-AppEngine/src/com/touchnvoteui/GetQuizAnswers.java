package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.Answers;
import com.touchnvoteui.entities.PMF;

public class GetQuizAnswers extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_USER_UNAME="username";
	public final static String PARAM_QUIZ_TITLE="title";
	public final static String PARAM_QUIZ_ANSWERS="answers";
	private String answer1 = null;
	private String answer2 = null;
	private String answer3 = null;
	private String answer4 = null;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		String username=req.getParameter(PARAM_USER_UNAME);
		String title=req.getParameter(PARAM_QUIZ_TITLE);
		String answer=req.getParameter(PARAM_QUIZ_ANSWERS);
		
		System.out.println(answer);
		String[] ans = answer.split("___");
		for(String a:ans){
			if(a.substring(0, 1).equals("1")){
				answer1 = a.substring(2, a.length());
			}
			else if(a.substring(0, 1).equals("2")){
				answer2 = a.substring(2, a.length());
			}
			else if(a.substring(0, 1).equals("3")){
				answer3 = a.substring(2, a.length());
			}
			else if(a.substring(0, 1).equals("4")){
				answer4 = a.substring(2, a.length());
			}
		}
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			Answers answerObj = new Answers(username,title,answer1,answer2,answer3,answer4);
			pm.makePersistent(answerObj);
			writer.write("true");
		}
		catch(Exception e){
			System.out.println("Inside exception");
			writer.write("false");
			e.printStackTrace();
		}
		finally{
			pm.close();
			writer.close();
		}
		
	}
	
	
}
