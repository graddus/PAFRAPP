package design;

public class Passengerswagon extends Wagon {
	private int seats;

	public Passengerswagon(int wagonid, double length, int seats) {
		super(wagonid);
		this.seats=seats;
	}

	public int getSeats() {
		return seats;
	}

}
