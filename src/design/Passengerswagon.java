package design;

public class Passengerswagon extends Wagon {
	private int seats;

	public Passengerswagon(String wagonid, int seats) {
		super(wagonid);
		this.seats=seats;
	}

	public int getSeats() {
		return seats;
	}

}
