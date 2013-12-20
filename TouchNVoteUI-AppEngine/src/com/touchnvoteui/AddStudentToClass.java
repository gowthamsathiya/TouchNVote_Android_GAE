package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.StudentEnrolledClass;
import com.touchnvoteui.entities.UserDetails;

public class AddStudentToClass extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_USER_USERNAME="username";
	public final static String PARAM_USER_CLASS="classid";
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String username=req.getParameter(PARAM_USER_USERNAME);
		String classid=req.getParameter(PARAM_USER_CLASS);
		res.setContentType("application/text");
		
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			StudentEnrolledClass details=(StudentEnrolledClass) pm.getObjectById(StudentEnrolledClass.class,username);
			writer.write("Sorry, the student is already registered to this class");
		}catch(javax.jdo.JDOObjectNotFoundException w){
			//UserDetails userdetails= new UserDetails(new JSONObject(jsonuserdetails));
			StudentEnrolledClass details = new StudentEnrolledClass();
			details.setNetId(username);
			details.setClassId(classid);
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


