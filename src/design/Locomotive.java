package design;

public class Locomotive extends Wagon{
	private int seats;

	public Locomotive(int wagonid, double length, int seats) {
		super(wagonid);
		this.seats=seats;
	}

	public int getSeats() {
		return seats;
	}

}
