package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.UserDetails;

public class ChangePassword extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_USER_USERNAME="username";
	public final static String PARAM_USER_PASSWORD="password";

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String username=req.getParameter(PARAM_USER_USERNAME);
		String password=req.getParameter(PARAM_USER_PASSWORD);
		res.setContentType("application/text");
		
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			UserDetails details=(UserDetails) pm.getObjectById(UserDetails.class,username);
			//System.out.println("NetID already present");
			details.setPassword(password);
			writer.write("true");
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


