package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.Question;

public class CheckQuestion extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String PARAM_QUESTION_TITLE = "title";
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String title = req.getParameter(PARAM_QUESTION_TITLE);
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			Query q = pm.newQuery(Question.class, "quiztitle == titleparam");
			q.declareParameters("String titleparam");
			List<Question> present = (List<Question>)q.execute(title);
			if(present == null || present.size() == 0)
				writer.write("false");
			else
				writer.write("true");
			
		}catch(Exception e){
			writer.write("false");
		}
	}

}
