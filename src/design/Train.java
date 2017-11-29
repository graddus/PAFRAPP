package design;

import java.util.ArrayList;

public class Train {
	private int trainid;
	private int totalseats;
	private ArrayList<Wagon> wagonlist=new ArrayList<Wagon>();
	
	
	public Train(int trainid) {
		this.trainid = trainid;
	}
	public int getTrainid() {
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
