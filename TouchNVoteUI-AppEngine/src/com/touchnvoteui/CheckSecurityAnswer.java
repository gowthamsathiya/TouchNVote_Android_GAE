package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.UserDetails;

public class CheckSecurityAnswer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_USER_DETAILS="username";
	public final static String PARAM_USER_ANSWER1="answer1";
	public final static String PARAM_USER_ANSWER2="answer2";
	public final static String PARAM_USER_ANSWER3="answer3";

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String username=req.getParameter(PARAM_USER_DETAILS);
		String answer1=req.getParameter(PARAM_USER_ANSWER1);
		String answer2=req.getParameter(PARAM_USER_ANSWER2);
		String answer3=req.getParameter(PARAM_USER_ANSWER3);
		
		res.setContentType("application/text");
		
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			UserDetails details=(UserDetails) pm.getObjectById(UserDetails.class,username);
			//System.out.println("NetID already present");
			if(details.getSecurityQuestionAnswer1().equalsIgnoreCase(answer1)&&details.getSecurityQuestionAnswer2().equalsIgnoreCase(answer2)&&details.getSecurityQuestionAnswer3().equalsIgnoreCase(answer3)){
				System.out.println(details.getSecurityQuestionAnswer1()+details.getSecurityQuestionAnswer2()+details.getSecurityQuestionAnswer3());
				writer.write("true");
			}
			else
				writer.write("false");
		}catch(javax.jdo.JDOObjectNotFoundException w){
			//UserDetails userdetails= new UserDetails(new JSONObject(jsonuserdetails));
			writer.write("false");
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			pm.close();
			writer.close();
		}
	}

}
