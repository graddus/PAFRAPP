package design;

public class LiquidCargowagon extends Wagon{
	private double contentliters;

	public LiquidCargowagon(int wagonid, double length, double content) {
		super(wagonid);
		contentliters=content;
	}

	public double getContentliters() {
		return contentliters;
	}

	public void setContentliters(double contentliters) {
		this.contentliters = contentliters;
	}
	
	
	

}
