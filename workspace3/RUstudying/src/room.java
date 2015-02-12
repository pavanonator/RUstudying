import java.util.LinkedList;

//LL NEEDS TO HAVE 840 NODES TO BEGIN WITH
public class room {
	LinkedList<time> room_ll = new LinkedList<time>();
	String campus;
	String building;
	String roomNumber;
	
	public room(String campus, String building, String roomNumber) {
		// TODO Auto-generated constructor stub
		//this.room_ll = new LinkedList<time>();
		this.campus=campus;
		this.building=building;
		this.roomNumber=roomNumber;
	}

}
