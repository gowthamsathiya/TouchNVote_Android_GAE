package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.QuizList;
import com.touchnvoteui.entities.StudentEnrolledClass;

public class PostQuiz extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_QUIZ_ID="quizId";
	public final static String PARAM_QUIZ_TITLE="quizTitle";
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String quizid=req.getParameter(PARAM_QUIZ_ID);
		String quiztitle=req.getParameter(PARAM_QUIZ_TITLE);
		res.setContentType("application/text");
		
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			QuizList details=(QuizList) pm.getObjectById(QuizList.class,quizid);
			writer.write("Sorry, you have already posted a test");
		}catch(javax.jdo.JDOObjectNotFoundException w){
			QuizList details = new QuizList(quizid,quiztitle);
			pm.makePersistent(details);
			writer.write("true");
		} catch(Exception e){
			e.printStackTrace();
			writer.write("Sorry, something went wrong. Try again");
		}finally{
			pm.close();
			writer.close();
		}
	}

}


