package com.touchnvoteui;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.touchnvoteui.entities.ClassDetails;
import com.touchnvoteui.entities.PMF;

public class ClassAvailable extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		Writer writer=res.getWriter();
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			List<ClassDetails> allclass = (List<ClassDetails>) pm.newQuery("select from "+ClassDetails.class.getName()).execute();
			List<String> classlist = new ArrayList<String>();
			Date cd = new Date();
			for(int i=0; i<allclass.size();i++){
				String temp = allclass.get(i).getClassId().toString();
				/*
				String temparray[] = temp.split("_");
				String temparrayenddate[] = temparray[2].split("-");
				String endyear = temparrayenddate[2];
				if(Integer.parseInt(endyear) == cd.getYear() || Integer.parseInt(endyear) == cd.getYear()+1){
					classlist.add(temp);
				}
				*/
				/*
				Date startDate =  allclass.get(i).getClassStartDate();
				Date endDate =  allclass.get(i).getClassEndDate();
				Calendar startCalendar = new GregorianCalendar();
				startCalendar.setTime(startDate);
				Calendar nowDate = new GregorianCalendar();
				nowDate.setTime(cd);
				Calendar endCalendar = new GregorianCalendar();
				endCalendar.setTime(endDate);
				
				int diffYear = nowDate.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
				int diffMonth = diffYear * 12 + nowDate.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
				if(diffMonth>=0&&diffMonth<=12){
					System.out.println(temp);
					classlist.add(temp);
				}
				*/
				classlist.add(temp);
			}
			res.setContentType("application/json");
		    res.setCharacterEncoding("UTF-8");
			if(!classlist.isEmpty()){
				/*
			String json = new Gson().toJson(classlist);
			System.out.println(json);
		    writer.write(json);
		    */
		    StringBuilder builder = new StringBuilder();
		    for(String s:classlist){
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