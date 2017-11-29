package data;

import java.util.ArrayList;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class test {
	public static void main(String[] args) {
		TrainController td1=new TrainController();
		Train t1=td1.getTrains().get(0);
		for (Wagon w:t1.getWagonlist()){
			if (w.getClass()==Passengerswagon.class){
				System.out.println("Passengerswagon");
				Passengerswagon p1=(Passengerswagon)w;
				System.out.println("Total seats: "+p1.getSeats());
			}
			if (w.getClass()==LiquidCargowagon.class){
				System.out.println("LiquidCargowagon");
				LiquidCargowagon p1=(LiquidCargowagon)w;
				System.out.println("Content in liters: "+p1.getContentliters());
			}
			if (w.getClass()==SolidCargowagon.class){
				System.out.println("SolidCargowagon");
				SolidCargowagon p1=(SolidCargowagon)w;
				System.out.println("Content in cubic meters: "+p1.getContentcubic());
			}
			if (w.getClass()==Locomotive.class){
				System.out.println("Locomotive");
				Locomotive p1=(Locomotive)w;
				System.out.println("Seat: "+p1.getSeats());
			}
		}
				
	
	}
	}
