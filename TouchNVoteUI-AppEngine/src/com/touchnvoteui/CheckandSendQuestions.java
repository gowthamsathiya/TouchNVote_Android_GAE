package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.util.DateUtils;

import com.touchnvoteui.entities.Answers;
import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.Question;
import com.touchnvoteui.entities.QuizList;
import com.touchnvoteui.entities.UserDetails;

public class CheckandSendQuestions extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PARAM_USER_CLASS = "class";
	private String PARAM_USER_NAME = "username";
	
	public int compare(Calendar c1, Calendar c2) {
	    if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) 
	        return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
	    if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)) 
	        return c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
	    return c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		String userenrolledclass=req.getParameter(PARAM_USER_CLASS );
		String username=req.getParameter(PARAM_USER_NAME);
		PersistenceManager pm=PMF.get().getPersistenceManager();
		boolean found = false; 
		try{
			List<QuizList> allclass = (List<QuizList>) pm.newQuery("select from "+QuizList.class.getName()).execute();
			System.out.println(allclass.toString());
			Date cd = new Date();
			if(allclass != null){
				for(int i=0; i<allclass.size();i++){
					String temp = allclass.get(i).getQuizId().toString();
					String userclass = temp.substring(0,temp.lastIndexOf("_"));
					System.out.println("userenrolled: "+temp.substring(0,temp.lastIndexOf("_")));
					System.out.println(userclass);
					if(userclass.equalsIgnoreCase(userenrolledclass)){
						String test = temp.substring(temp.lastIndexOf("_")+1,temp.length());
						Date testdate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(test);
						//if(testdate.getDate() == cd.getDate() && testdate.getMonth() == cd.getMonth() && testdate.getYear() == cd.getYear()){
						Calendar sCalendar = Calendar.getInstance();
						sCalendar.setTime(cd);
						Calendar eCalendar = Calendar.getInstance();
						eCalendar.setTime(testdate);
						if(compare(sCalendar,eCalendar) == 0){
						//if(test.equals("05-Dec-2013") || test.equals("5-Dec-2013")){
							//if(true){
							String questionliststring="";
							found = true;
							/*
							Query check = pm.newQuery("select from "+Answers.class.getName()+" where quizTitle == qt && userName == un");
							check.declareParameters("String qt");
							check.declareParameters("String un");
							List<Answers> ans = (List<Answers>) check.execute(allclass.get(i).getQuizTitle(),username);
							*/
							System.out.println("inside found");
							Query qry = pm.newQuery(Answers.class);
							qry.setFilter("quizTitle == :qt && userName == :un");
							Map<String, String> paramValues = new HashMap();
							paramValues.put("qt", allclass.get(i).getQuizTitle());
							paramValues.put("un", username);
							//List<Answers> ans = (List<Answers>)qry.executeWithMap(paramValues);
							@SuppressWarnings("unchecked")
							List<Answers> ans = (List<Answers>)qry.executeWithArray(new Object[] {allclass.get(i).getQuizTitle(),username});
							
							if(ans.size() != 0){
								System.out.println("Repeated quiz "+ans.size());
								//System.out.println(ans.get(0).getAnswers1());
								writer.write("None");
							}
							else{
							Query query =  pm.newQuery("select from "+Question.class.getName()+" where quiztitle == quizTitle");
							query.declareParameters("String quizTitle");
							List<Question> quizquestion = (List<Question>) query.execute(allclass.get(i).getQuizTitle());
							System.out.println("quizques"+quizquestion);
							if(quizquestion!=null){
								for(Question q: quizquestion){
									questionliststring+=q.toString()+"####";
								}
								//if(!questionliststring.equals(""))
								Logger log = Logger.getLogger(CheckandSendQuestions.class.getName());
								log.info(questionliststring.substring(0, questionliststring.length()-4));
								System.out.println(questionliststring.substring(0, questionliststring.length()-4));
								writer.write(questionliststring.substring(0, questionliststring.length()-4));
								//writer.write("title12///huih///1///[[hih,  jhgjg]]///[[2]]///1####title12///hihi///1///[[hi,  hhh]]///[[1]]///2");
								break;
							}
							}
						}
						else{
							//System.out.println("No exams");
							//writer.write("None");
						}
					
					}
				}
			}
			if(found == false){
				System.out.println("Not found");
				writer.write("None");
			}
			}
		catch(javax.jdo.JDOObjectNotFoundException w){
			System.out.println("JDOObjectNotfound");
			writer.write("None"); //Entity not found
		}
		catch(Exception e){
			writer.write("None"); //Entity not found
			e.printStackTrace();
		}
		finally{
			pm.close();
			writer.close();
		}
		
	}
	
}
