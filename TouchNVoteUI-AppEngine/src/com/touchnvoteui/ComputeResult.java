package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.Answers;
import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.Question;

public class ComputeResult extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PARAM_QUIZ_TITLE = "quiztitle";
	private int noOfStudents = 0;
	private int noOfQuestions = 0;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		String qtitle=req.getParameter(PARAM_QUIZ_TITLE );
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			Query query =  pm.newQuery("select from "+Question.class.getName()+" where quiztitle == quizTitle");
			query.declareParameters("String quizTitle");
			List<Question> quizquestion = (List<Question>) query.execute(qtitle);
			Map<String,Integer> quiz = new HashMap<String,Integer>();
			
			for(Question q:quizquestion){
				//writer.write("question "+q.toString());
				for(String ans:q.getAnswers()){
					//writer.write("answers "+ans);
					quiz.put(Integer.toString(q.getCount())+ans.replace("[", "").replace("]", "").trim(),0);
					//writer.write("question map "+Integer.toString(q.getCount())+ans.replace("[", "").replace("]", "").trim()+"---"+0);
				//	System.out.println("map "+Integer.toString(q.getType())+ans+" "+0);
				}
			}
			
			for(Map.Entry<String, Integer> e:quiz.entrySet()){
				//System.out.println("original "+e.getKey()+","+e.getValue());
				//writer.write("quiz original "+e.getKey()+","+e.getValue());
			}
			
			Query query1 =  pm.newQuery("select from "+Answers.class.getName()+" where quizTitle == quiztitle");
			query1.declareParameters("String quiztitle");
			List<Answers> answerslist = (List<Answers>) query1.execute(qtitle);
			
			if(answerslist!=null){
			for(Answers ans:answerslist){
				String ans1 = ans.getAnswers1();
				String ans2 = ans.getAnswers2();
				String ans3 = ans.getAnswers3();
				String ans4 = ans.getAnswers4();
				//System.out.println("Std ans"+ans1+" "+ans2+" "+ans3+" "+ans4);
				//writer.write("Std ans"+ans1+" "+ans2+" "+ans3+" "+ans4+"///");
				
				//if(ans1!=null || ans1!="<null>" || ans1!="" || !ans1.equals("")){
				if(ans1!=null){
					//writer.write("inside ans1"+ans1);
					if(ans1.contains(",,")){
						String[] anslist = ans1.split(",,");
						for(String a:anslist){
							String key="1"+a;
					//	System.out.println(key);
							int count = quiz.get(key);
							quiz.put(key, count+1);
							//writer.write("ans1 "+key+" "+quiz.get(key));
						}

					//	System.out.println(key+count+1);
					//	System.out.println("value after updation "+quiz.get("1"+a));
					}else{
						String key="1"+ans1;
						//	System.out.println(key);
								int count = quiz.get(key);
								quiz.put(key, count+1);
								//writer.write("ans1 "+key+" "+quiz.get(key));
					}
				}
				//if(ans2!=null || ans2!="<null>" || ans2!="" || !ans2.equals("")){
				if(ans2!=null){
					if(ans2.contains(",,")){
					//writer.write("inside ans2"+ans2);
					String[] anslist = ans2.split(",,");
					for(String a:anslist){
						String key="2"+a;
						int count = quiz.get(key);
						quiz.put(key, count+1);
						//writer.write("ans2 "+key+" "+quiz.get(key));
						//System.out.println("2"+a+count+1);
					}
					}else{
						//writer.write("inside ans2"+ans2);
						String key="2"+ans2;
						//writer.write("ans2 key "+key);
						int count = quiz.get(key);
						//writer.write("ans2 count "+count);
						quiz.put(key, count+1);
						//writer.write("ans2 "+key+" "+quiz.get(key));
					}
				}
				//if(ans3!=null || ans3!="<null>" || ans3!="" || !ans3.equals("")){
				if(ans3!=null){
					if(ans3.contains(",,")){
					//writer.write("inside ans3"+ans3);
					String[] anslist = ans3.split(",,");
					for(String a:anslist){
						String key="3"+a;
						int count = quiz.get(key);
						quiz.put(key, count+1);
						//writer.write("ans3 "+key+" "+quiz.get(key));
						
					}
					}else{
						String key="3"+ans3;
						int count = quiz.get(key);
						quiz.put(key, count+1);
						//writer.write("ans3 "+key+" "+quiz.get(key));
					}
				}
				
				//if(ans4!=null || ans4!="<null>" || ans4!="" || !ans4.equals("")){
				if(ans4!=null){
					if(ans4.contains(",,")){
					//writer.write("inside ans4"+ans4);
					String[] anslist = ans4.split(",,");
					for(String a:anslist){
						String key="4"+a;
						int count = quiz.get(key);
						quiz.put(key, count+1);
						//writer.write("ans4 "+key+" "+quiz.get(key));
					}
					}
					else{
						String key="4"+ans4;
						int count = quiz.get(key);
						quiz.put(key, count+1);
						//writer.write("ans4 "+key+" "+quiz.get(key));
					}
				}
				
			}
			
			
			String resp="";
			//writer.write("Enter map loop set");
			for(Map.Entry<String, Integer> e:quiz.entrySet()){
				//System.out.println("updated "+e.getKey()+","+e.getValue());
				resp+= e.getKey()+",,"+e.getValue()+"///";
				//writer.write("inside map loop "+resp);
			}
			//writer.write("respond " + resp.substring(0, resp.length()-3));
			
			if(!resp.equals("")){
				writer.write(resp.substring(0, resp.length()-3));
			}
			else{
				writer.write("exnull");
			}
			
			}
			else{
				//writer.write("null");
				writer.write("null");
				
			}
		}
		catch(Exception e){
			//writer.write("null");
			writer.write(e.toString());
			e.printStackTrace();
		}
		finally{
			pm.close();
			writer.close();
		}
	}
		

}
