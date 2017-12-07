package design;

public abstract class Wagon {
String wagonid;


public Wagon(String wagonid) {
	this.wagonid = wagonid;
}
public String getWagonid() {
	return wagonid;
}
public void setWagonid(String wagonid) {
	this.wagonid = wagonid;
}

}
