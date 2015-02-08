import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;


public class RetrieveData {
	public static allclassesindept coursejson = new allclassesindept();
	public static allsections coursejson2 = new allsections();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONArray departments = HttpGet("http://sis.rutgers.edu/soc/subjects.json?semester=12015&campus=NB&level=U");
		JSONObject course = new JSONObject();
		///ArrayList<JSONObject> coursejson = new ArrayList<JSONObject>();
		
		String code="";

		for(int i = 0; i < /*departments.length()*/5; i++){
			System.out.println(i);
			course = departments.getJSONObject(i);
			code = course.getString("code").toString(); //get value of each dept code
			coursejson.courses.add(HttpGet("http://sis.rutgers.edu/soc/courses.json?semester=12015&subject="+code+"&campus=NB&level=U"));		
		}
		
		JSONArray sectionsarray = new JSONArray();
		for(int i=0;i<coursejson.courses.size();i++){
			for(int j=0;j<coursejson.courses.get(i).length();j++){
				for(int k=0;k<coursejson.courses.get(i).getJSONObject(j).getJSONArray("sections").length();k++){
					sectionsarray=coursejson.courses.get(i).getJSONObject(j).getJSONArray("sections").getJSONObject(k).getJSONArray("meetingTimes");
					coursejson2.courses2.add(sectionsarray);
				}
			}
		}
		
		System.out.println(coursejson2.courses2.get(0));
	}
	
	private static JSONArray HttpGet(String url){
		
		try{
			 
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(url);
			
			HttpResponse r = client.execute(get);
		 
			BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
			String line = "";
			String response = "";
			while ((line = rd.readLine()) != null){
				response += line;
			}
			
			JSONArray ret = new JSONArray(response);
			
			return ret;
			
		} catch(Exception e){
			
			System.out.println("exception: " + e);
		}
		
		return null;
	}
	
	public static void initAL(){
		// 
		for(int j=0;j<150;j++){
			JSONArray t=new JSONArray();
			coursejson.courses.add(j,t);
		}
	}
}
	


