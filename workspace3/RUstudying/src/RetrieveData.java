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
	public static allrooms onBus = new allrooms();
	public static allrooms onLiv = new allrooms();
	public static allrooms onCac = new allrooms();
	public static allrooms onCd = new allrooms();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONArray departments = HttpGet("http://sis.rutgers.edu/soc/subjects.json?semester=12015&campus=NB&level=U");
		JSONObject course = new JSONObject();
		// /ArrayList<JSONObject> coursejson = new ArrayList<JSONObject>();

		String code = "";

		for (int i = 0; i < /* departments.length() */1; i++) {
			System.out.println(i);
			course = departments.getJSONObject(i);
			code = course.getString("code").toString(); // get value of each
														// dept code
			coursejson.courses
					.add(HttpGet("http://sis.rutgers.edu/soc/courses.json?semester=12015&subject="
							+ code + "&campus=NB&level=U"));
		}

		JSONArray sectionsarray = new JSONArray();
		for (int i = 0; i < coursejson.courses.size(); i++) {
			for (int j = 0; j < coursejson.courses.get(i).length(); j++) {
				for (int k = 0; k < coursejson.courses.get(i).getJSONObject(j)
						.getJSONArray("sections").length(); k++) {
					sectionsarray = coursejson.courses.get(i).getJSONObject(j)
							.getJSONArray("sections").getJSONObject(k)
							.getJSONArray("meetingTimes");
					coursejson2.courses2.add(sectionsarray);
				}
			}
		}

		for (int i = 0; i < coursejson2.courses2.size(); i++) {
			for (int j = 0; j < coursejson2.courses2.get(i).length(); j++) {
				 System.out.println(coursejson2.courses2.get(i).getJSONObject(j)
							.get("campusAbbrev").toString()+
					coursejson2.courses2.get(i).getJSONObject(j)
							.get("buildingCode").toString()+
					coursejson2.courses2.get(i).getJSONObject(j)
							.get("roomNumber").toString()+
					coursejson2.courses2.get(i).getJSONObject(j)
							.get("pmCode").toString()+
					coursejson2.courses2.get(i).getJSONObject(j)
							.get("startTime").toString()+
					coursejson2.courses2.get(i).getJSONObject(j)
							.get("endTime").toString());
				 organize(
						coursejson2.courses2.get(i).getJSONObject(j)
								.get("campusAbbrev").toString(),
						coursejson2.courses2.get(i).getJSONObject(j)
								.get("buildingCode").toString(),
						coursejson2.courses2.get(i).getJSONObject(j)
								.get("roomNumber").toString(),
						coursejson2.courses2.get(i).getJSONObject(j)
								.get("pmCode").toString(),		
						coursejson2.courses2.get(i).getJSONObject(j)
								.get("startTime").toString(),
						coursejson2.courses2.get(i).getJSONObject(j)
								.get("endTime").toString());
			}
		}
		System.out.println(onLiv.roomll_al.size());
	}

	public static int nodesdistanceA = 0;
	public static int nodesdistance = 0;
	public static void organize(String campus, String building, String roomNumber, String pm, String start, String end){
		if(campus.compareTo("BUS")==0){
			int ALindex=0;
			ALindex=inthere(onBus, building, roomNumber);
			System.out.println(ALindex);
			if(ALindex>=0){
				//meaning there is an element in the al for that room# and ALindex is the location of that room# in the al
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				int x=0;
				while(x <nodesdistance){
					//this is to go to the start of that class time in the ll and 
					//switch boolean values for x nodes after distanceA
					onBus.roomll_al.get(ALindex).room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
			}else{
				//no room# of the current json object exists in the AL
				room e = new room(campus,building,roomNumber);
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				for(int i=0;i<840;i++){
					time t= new time();
					e.room_ll.add(t);
				}
				int x=0;
				while(x <nodesdistance){	
					e.room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
				onLiv.roomll_al.add(e);
			}
		}//end bus if
		if(campus.compareTo("LIV")==0){
			int ALindex=0;
			ALindex=inthere(onLiv, building, roomNumber);
			System.out.println(ALindex);
			if(ALindex>=0){
				//meaning there is an element in the al for that room# and ALindex is the location of that room# in the al
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				int x=0;
				while(x <nodesdistance){
					//this is to go to the start of that class time in the ll and 
					//switch boolean values for x nodes after distanceA
					onLiv.roomll_al.get(ALindex).room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
			}else{
				//no room# of the current json object exists in the AL
				room e = new room(campus,building,roomNumber);
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				for(int i=0;i<840;i++){
					time t= new time();
					e.room_ll.add(t);
				}
				int x=0;
				while(x <nodesdistance){	
					e.room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
				onLiv.roomll_al.add(e);
			}
		}//end liv if
		if(campus.compareTo("CAC")==0){
			int ALindex=0;
			ALindex=inthere(onCac, building, roomNumber);
			System.out.println(ALindex);
			if(ALindex>=0){
				//meaning there is an element in the al for that room# and ALindex is the location of that room# in the al
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				int x=0;
				while(x <nodesdistance){
					//this is to go to the start of that class time in the ll and 
					//switch boolean values for x nodes after distanceA
					onCac.roomll_al.get(ALindex).room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
			}else{
				//no room# of the current json object exists in the AL
				room e = new room(campus,building,roomNumber);
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				for(int i=0;i<840;i++){
					time t= new time();
					e.room_ll.add(t);
				}
				int x=0;
				while(x <nodesdistance){	
					e.room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
				onCac.roomll_al.add(e);
			}
		}//end cac if
		if(campus.compareTo("D/C")==0){
			int ALindex=0;
			ALindex=inthere(onCd, building, roomNumber);
			System.out.println(ALindex);
			if(ALindex>=0){
				//meaning there is an element in the al for that room# and ALindex is the location of that room# in the al
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				int x=0;
				while(x <nodesdistance){
					//this is to go to the start of that class time in the ll and 
					//switch boolean values for x nodes after distanceA
					onCd.roomll_al.get(ALindex).room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
			}else{
				//no room# of the current json object exists in the AL
				room e = new room(campus,building,roomNumber);
				if(calcMin(pm,start)>calcMin(pm,end)){
					//meaning start time is greater than end numerically 11AM-2PM
					nodesdistanceA=calcMin(pm, start);
					nodesdistance=240-nodesdistanceA+calcMin(pm,end);
				}else{
					nodesdistanceA=calcMin(pm,start);
					nodesdistance=calcMin(pm,end)-nodesdistanceA;
				}
				for(int i=0;i<840;i++){
					time t= new time();
					e.room_ll.add(t);
				}
				int x=0;
				while(x <nodesdistance){	
					e.room_ll.get(nodesdistanceA+x).occupied=true;
					x++;
				}
				onCd.roomll_al.add(e);
			}
		}//end cd if
	}

	public static int inthere(allrooms campus, String building,
			String roomNumber) {
		for (int i = 0; i < campus.roomll_al.size(); i++) {
			if (building.compareTo(campus.roomll_al.get(i).building)==0) {
				if (roomNumber.compareTo(campus.roomll_al.get(i).roomNumber)==0) {
					return i;
				}
			}
		}
		return -1;
	}

	private static JSONArray HttpGet(String url) {

		try {

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(url);

			HttpResponse r = client.execute(get);

			BufferedReader rd = new BufferedReader(new InputStreamReader(r
					.getEntity().getContent()));
			String line = "";
			String response = "";
			while ((line = rd.readLine()) != null) {
				response += line;
			}

			JSONArray ret = new JSONArray(response);

			return ret;

		} catch (Exception e) {

			System.out.println("exception: " + e);
		}

		return null;
	}

	public final static int MINUTESINHOUR = 60;
	public final static int MINUTESINMORNING = 240;
	public static int calcMin(String pm, String time) {
		// might be unnessary
		if (time.length() != 4) {
			return 0;
		}
		int minutesAfter8 = 0;
		int hour = Integer.parseInt(time.substring(0, 2));
		int minutes = Integer.parseInt(time.substring(2, 4));
		if (pm.compareTo("P")==0 && hour!=12) {
			minutesAfter8 = (minutes + (hour * MINUTESINHOUR)) + 240;
		} else { /* am */
			minutesAfter8 = minutes + (hour * MINUTESINHOUR) - 480;
		}
		//System.out.println(minutesAfter8);
		return minutesAfter8;
	}
}
