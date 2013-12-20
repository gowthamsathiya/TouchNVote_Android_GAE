package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.touchnvoteui.entities.ClassDetails;
import com.touchnvoteui.entities.PMF;
import com.touchnvoteui.entities.UserDetails;


public class SaveClass extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM_CLASS_ID="classid";
	public final static String PARAM_CLASS_SEASON="classseason";
	public final static String PARAM_CLASS_START_DATE="classstartdate";
	public final static String PARAM_CLASS_END_DATE="classenddate";
	public final static String PARAM_CLASS_TIME="classtime";
	private boolean present;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		String classid=req.getParameter(PARAM_CLASS_ID);
		String classseason=req.getParameter(PARAM_CLASS_SEASON);
		String classstartdate=req.getParameter(PARAM_CLASS_START_DATE);
		String classenddate=req.getParameter(PARAM_CLASS_END_DATE);
		String classtime=req.getParameter(PARAM_CLASS_TIME);

		Date startdate = null;
		Date enddate = null;
		Date time = null;
		try {
			startdate = new SimpleDateFormat("dd-MMM-yyyy").parse(classstartdate);
			enddate = new SimpleDateFormat("dd-MMM-yyyy").parse(classstartdate);
			time = new SimpleDateFormat("hh:mm").parse(classtime);
			//System.out.println(startdate.getYear());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println("Parsing exception in saveclass servlet");
			e1.printStackTrace();
		}
		/*
		try{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("touchnvote");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		ClassDetails classdetails = new ClassDetails();
		classdetails.setClassId(classid);
		classdetails.setClassSeason(classseason);
		classdetails.setClassStartDate(classstartdate);
		classdetails.setClassEndDate(classenddate);
		classdetails.setClassTime(classtime);
		em.persist(classdetails);
		writer.write("true");
		em.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			writer.write("false");
		}
		*/
		
		res.setContentType("application/text");

		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			ClassDetails details=(ClassDetails) pm.getObjectById(ClassDetails.class,classid);
			present = true;
			if(present){
				System.out.println("Class is already present");
				writer.write("null");
			}
		}catch(javax.jdo.JDOObjectNotFoundException w){
			//UserDetails userdetails= new UserDetails(new JSONObject(jsonuserdetails));
			ClassDetails userdetails=new ClassDetails();
			userdetails.setClassId(classid);
			userdetails.setClassSeason(classseason);
			userdetails.setClassStartDate(startdate);
			userdetails.setClassEndDate(enddate);
			userdetails.setClassTime(time);
			pm.makePersistent(userdetails);
			writer.write("true");
		} catch(Exception e){
			e.printStackTrace();
			writer.write("false");
		}finally{
			pm.close();
			writer.close();
		}
		
	}

}
