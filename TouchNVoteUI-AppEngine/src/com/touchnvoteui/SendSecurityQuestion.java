package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.UserDetails;

public class SendSecurityQuestion extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public final static String PARAM_USER_UNAME="username";
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		String username=req.getParameter(PARAM_USER_UNAME);
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			UserDetails details=(UserDetails) pm.getObjectById(UserDetails.class,username);
			writer.write(details.getSecurityQuestion1()+":"+details.getSecurityQuestion2()+":"+details.getSecurityQuestion3()); //Entity found
			System.out.println("valid user");
			System.out.println("Leaving SendSecurityQuestion Servlet");
			}
		catch(javax.jdo.JDOObjectNotFoundException w){
			writer.write("false"); //Entity not found
			System.out.println("Invalid user");
			System.out.println("Leaving SendSecurityQuestion Servlet");
		}
		catch(Exception e){
			writer.write("false"); //Entity not found
			e.printStackTrace();
		}
		finally{
			pm.close();
			writer.close();
		}
		
	}
	
}
