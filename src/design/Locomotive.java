package design;

public class Locomotive extends Wagon{
	private int seats;

	public Locomotive(String wagonid, int seats) {
		super(wagonid);
		this.seats=seats;
	}

	public int getSeats() {
		return seats;
	}

}
