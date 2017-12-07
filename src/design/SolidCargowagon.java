package design;

public class SolidCargowagon extends Wagon{
	private double contentcubic;

	public SolidCargowagon(String wagonid, double content) {
		super(wagonid);
		contentcubic=content;
	}

	public double getContentcubic() {
		return contentcubic;
	}

	public void setContentliters(double contentcubic) {
		this.contentcubic = contentcubic;
	}
	
	
	

}
