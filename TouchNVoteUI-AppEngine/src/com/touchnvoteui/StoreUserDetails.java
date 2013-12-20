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

public class StoreUserDetails extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_USER_DETAILS="userdetails";
	public final static String PARAM_USER_F_NAME="firstname";
	public final static String PARAM_USER_L_NAME="lastname";
	public final static String PARAM_USER_NET_ID="netid";
	public final static String PARAM_USER_PASSWORD="password";
	public final static String PARAM_USER_SQ1="securityquestion1";
	public final static String PARAM_USER_SQ2="securityquestion2";
	public final static String PARAM_USER_SQ3="securityquestion3";
	public final static String PARAM_USER_SQ1_A="securityquestion1answers";
	public final static String PARAM_USER_SQ2_A="securityquestion2answers";
	public final static String PARAM_USER_SQ3_A="securityquestion3answers";
	public final static String PARAM_USER_CLASS="class";
	private boolean present;
	
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//String jsonuserdetails=req.getParameter(PARAM_USER_DETAILS);
		String firstname=req.getParameter(PARAM_USER_F_NAME);
		String lastname=req.getParameter(PARAM_USER_L_NAME);
		String netid=req.getParameter(PARAM_USER_NET_ID);
		String password=req.getParameter(PARAM_USER_PASSWORD);
		String securityquestion1=req.getParameter(PARAM_USER_SQ1);
		String securityquestion2=req.getParameter(PARAM_USER_SQ2);
		String securityquestion3=req.getParameter(PARAM_USER_SQ3);
		String securityquestion1answers=req.getParameter(PARAM_USER_SQ1_A);
		String securityquestion2answers=req.getParameter(PARAM_USER_SQ2_A);
		String securityquestion3answers=req.getParameter(PARAM_USER_SQ3_A);
		String classselected=req.getParameter(PARAM_USER_CLASS);

		res.setContentType("application/text");
		
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			StudentEnrolledClass studentc = (StudentEnrolledClass) pm.getObjectById(StudentEnrolledClass.class,netid);
			if(studentc.getClassId().toString().equals(classselected)){
				try{
					UserDetails details=(UserDetails) pm.getObjectById(UserDetails.class,netid);
					present = true;
					if(present){
						System.out.println("NetID already present");
						writer.write("null");
					}
				}catch(javax.jdo.JDOObjectNotFoundException w){
					//UserDetails userdetails= new UserDetails(new JSONObject(jsonuserdetails));
					UserDetails userdetails=new UserDetails(firstname,lastname,netid,password,securityquestion1,securityquestion2,securityquestion3,securityquestion1answers,securityquestion2answers,securityquestion3answers,classselected);
					pm.makePersistent(userdetails);
					writer.write(userdetails.getNetID().toString());
				} catch(Exception e){
					e.printStackTrace();
				}finally{
					pm.close();
					writer.close();
				}
			}
			else{
				writer.write("false");
			}
			}catch(javax.jdo.JDOObjectNotFoundException w){
				writer.write("false");
			} catch(Exception e){
				e.printStackTrace();
			}finally{
				pm.close();
				writer.close();
			}
		}
}
