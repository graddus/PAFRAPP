package design;

public abstract class Wagon {
int wagonid;
double length;


public Wagon(int wagonid, double length) {
	this.wagonid = wagonid;
	this.length = length;
}
public int getWagonid() {
	return wagonid;
}
public void setWagonid(int wagonid) {
	this.wagonid = wagonid;
}
public double getLength() {
	return length;
}
public void setLength(double length) {
	this.length = length;
}

}
