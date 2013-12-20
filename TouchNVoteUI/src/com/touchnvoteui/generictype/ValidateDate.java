package com.touchnvoteui.generictype;

import android.util.Log;

public class ValidateDate {

	public static boolean checkdate1(String input){
	boolean correct = true;
	  String s[]=input.split("-");
	  Log.i("check date", input);
	  int dt = Integer.parseInt(s[0]);
	  if (s[1].equals("Jan"))
	  {
		  Log.i("", "in jan");
		  if(dt==0||dt==00||dt>31){
			  //System.out.println("invalid date");
			 // return false; 
			  	Log.i("", "false");
				correct = false;
		  }
	  }
	  else if (s[1].equals("Feb"))
	  {
		  if(dt==0||dt==00||dt>28){
			 // System.out.println("invalid date");
			 // return false; 
				correct = false;	
		  }
	  }
	  else if (s[1].equals("Mar"))
	  {
		  if(dt==0||dt==00||dt>31){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;		
		  }
	  }
	  else if (s[1].equals("Apr"))
	  {
		  if(dt==0||dt==00||dt>30){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;		
		  }
	}else if (s[1].equals("May"))
	  {
		  if(dt==0||dt==00||dt>31){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;	
		  }
	}else if (s[1].equals("Jun"))
	  {
		  if(dt==0||dt==00||dt>30){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;		
		  }
	}else if (s[1].equals("Jul"))
	  {
		  if(dt==0||dt==00||dt>31){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;		
		  }
	}else if (s[1].equals("Aug"))
	  {
		  if(dt==0||dt==00||dt>31){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;	
		  }
	}else if (s[1].equals("Sep"))
	  {
		  if(dt==0||dt==00||dt>30){
			  //System.out.println("invalid date");
			  //return false; 
				correct = false;		
		  }
	}else if (s[1].equals("Oct"))
	  {
		  if(dt==0||dt==00||dt>31){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;		
		  }
	}else if (s[1].equals("Nov"))
	  {
		  if(dt==0||dt==00||dt>30){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;		
		  }
	}else if (s[1].equals("Dec"))
	  {
		  if(dt==0||dt==00||dt>31){
			  //System.out.println("invalid date");
			  //return false;  
			  correct = false;		
		  }
	}
	  
	  return correct;
	  
	  
	}
}
