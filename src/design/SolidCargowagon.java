package design;

public class SolidCargowagon extends Wagon{
	private double contentcubic;

	public SolidCargowagon(int wagonid, double length, double content) {
		super(wagonid, length);
		contentcubic=content;
	}

	public double getContentcubic() {
		return contentcubic;
	}

	public void setContentliters(double contentcubic) {
		this.contentcubic = contentcubic;
	}
	
	
	

}
