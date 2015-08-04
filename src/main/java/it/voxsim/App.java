package it.voxsim;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App 
{
    public static void main( String[] args )
    {
    	try {
    		SocialNetworkingClient client = new SocialNetworkingClient();
    		
    		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        	
        	String command;
            while ((command = bufferedReader.readLine()) != null) {
              client.process(command);
            }
            inputStreamReader.close();
    	} catch(Exception e) {
    		throw new RuntimeException(e);
    	}
    	
    }
}
