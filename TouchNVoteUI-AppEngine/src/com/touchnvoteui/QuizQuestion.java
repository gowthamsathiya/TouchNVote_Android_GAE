package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.datanucleus.Transaction;

import com.touchnvoteui.entities.ClassDetails;
import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.Question;

public class QuizQuestion extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PARAM_QUESTION_LIST = "questionlist";
	boolean posted = false;
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String questionlist=URLDecoder.decode(req.getParameter(PARAM_QUESTION_LIST),"UTF-8");
		System.out.println(questionlist);
		//String replacedstring = questionlist.replaceAll("****", "####").replaceAll("*_*", "///");
		res.setContentType("application/text");
		System.out.println(questionlist);
		//System.out.println(questionlist);
		Writer writer=res.getWriter();
		PersistenceManager pmf=PMF.get().getPersistenceManager();
		javax.jdo.Transaction tx=pmf.currentTransaction();
		try{
		if(questionlist!=null){
			String[] questionobj = questionlist.split("####");
			for(String q:questionobj){
				String[] questionparts = q.split("///");
				String quiztitle = questionparts[0];	
				String questionstring= questionparts[1];
				int type = Integer.parseInt(questionparts[2]);
				List<String> answers = new ArrayList<String>();
				answers = Arrays.asList(questionparts[3].split(","));
				List<String> correctanswer = new ArrayList<String>();
				correctanswer = Arrays.asList(questionparts[4].split(","));
				int count = Integer.parseInt(questionparts[5]);
				//System.out.println(quiztitle+questionstring+type+answers.toString()+correctanswer.toString());
				Question question = new Question(quiztitle,questionstring,type,answers,correctanswer,count);
				pmf.makePersistent(question);
				posted = true;
				}
			if(posted){
				writer.write("true");
			}
		}
		}
		catch(javax.jdo.JDOObjectNotFoundException w){
				writer.write("false");
		} catch(Exception e){
				writer.write("false");
				e.printStackTrace();
		}finally{
				pmf.close();
				writer.close();
		}
		}

}

