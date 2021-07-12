package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class apiUtils {

	//read from url and fetch JSON data in string format
	public static String ReadFromUrlAndFetchStringData(String apiEndPoint){
		try {
        	
            URL url = new URL(apiEndPoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(in);
            String output;
            output = br.readLine();
            conn.disconnect();
            
            if(output != null && !output.trim().isEmpty()){
            	return output;
            }else
            {
            	return null;
            }
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
            return null;
        }
	}
}
