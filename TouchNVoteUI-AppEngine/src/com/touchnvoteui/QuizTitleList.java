package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.ClassDetails;
import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.QuizList;

public class QuizTitleList extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			List<QuizList> allclass = (List<QuizList>) pm.newQuery("select from "+QuizList.class.getName()).execute();
			List<String> quizlist = new ArrayList<String>();
			Date cd = new Date();
			for(int i=0; i<allclass.size();i++){
				String temp = allclass.get(i).getQuizId().toString();
				String[] ctest = temp.split("_");
				Date testdate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(ctest[4]);
				//System.out.println(testdate);
				//System.out.println(cd);
				//if(!testdate.before(cd)|| (testdate.getDate() == cd.getDate() && testdate.getMonth() == cd.getMonth() && testdate.getYear() == cd.getYear()))
				{
					//System.out.println("inside check");
					quizlist.add(allclass.get(i).getQuizTitle().toString());
				}
			}
			res.setContentType("application/text");
		    res.setCharacterEncoding("UTF-8");
			if(!quizlist.isEmpty()){
				StringBuilder builder = new StringBuilder();
				for(String s:quizlist){
					builder.append(s);
					builder.append("&&");
				}
				writer.write((String) builder.toString().subSequence(0, builder.toString().length()-2));
			}
			else
				writer.write("null");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}