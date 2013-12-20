package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.Answers;
import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.UserDetails;


public class LoginUserDetails extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_USER_UNAME="username";
	public final static String PARAM_USER_PWD="password";
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		String username=req.getParameter(PARAM_USER_UNAME);
		String password=req.getParameter(PARAM_USER_PWD);
		
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			UserDetails details=(UserDetails) pm.getObjectById(UserDetails.class,username);
			//List<ClassDetails> allclass = (List<ClassDetails>) pm.newQuery("select from "+ClassDetails.class.getName()).execute();
			//for(int i=0; i<allclass.size();i++)
				//System.out.println(allclass.get(i).getClassId().toString());
			if(details.getPassword().equals(password)){
				writer.write("true"+"_"+details.getClassSelected()+"_"+details.getType()); //Entity found
				System.out.println("valid user");
			}
			else{
				writer.write("false");
				System.out.println("valid user invalid password");
			}
		}
		catch(javax.jdo.JDOObjectNotFoundException w){
			writer.write("false"); //Entity not found
			//UserDetails userdetails=new UserDetails("admin","admin","adm1234","admin","Q1","Q5","Q7","pizza","miami","soccer", null);
			//userdetails.setType(0);
			//pm.makePersistent(userdetails);
			//pm.newQuery("delete from "+QuizList.class.getName()).execute();
			//Query query = pm.newQuery("SELECT FROM "+QuizList.class.getName());
			//Long number = (Long)query.deletePersistentAll();
			//Answers a = new Answers("gxs1932","gg","hi",null,null,null);
			//pm.makePersistent(a);
			System.out.println("Invalid user");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			pm.close();
			writer.close();
		}
		
	}
	
}
