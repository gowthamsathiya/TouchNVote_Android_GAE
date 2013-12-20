package com.touchnvoteui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParseResponse {

	ParseResponse(){
		
	}
	public static StringBuilder getResult(org.apache.http.HttpResponse response) throws IllegalStateException, IOException {

        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())), 1024);
        String output;
        while ((output = br.readLine()) != null) 
            result.append(output);
        return result;
    }
}
