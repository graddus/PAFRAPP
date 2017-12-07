package design;

import java.util.ArrayList;

public class Train {
	private String trainid;
	private int totalseats;
	private ArrayList<Wagon> wagonlist=new ArrayList<Wagon>();
	
	
	public Train(String trainid) {
		this.trainid = trainid;
	}
	public String getTrainid() {
		return trainid;
	}
	public int getTotalseats() {
		return totalseats;
	}
	public void setTotalseats(int totalseats) {
		this.totalseats = totalseats;
	}
	public ArrayList<Wagon> getWagonlist() {
		return wagonlist;
	}
	public void setWagonlist(ArrayList<Wagon> wagonlist) {
		this.wagonlist = wagonlist;
	}
	public void addWagon(Wagon wagon){
		this.wagonlist.add(wagon);
	}

	
}
