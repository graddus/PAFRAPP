package data;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class TrainGUI {
	
	static TrainController tc = new TrainController();
	Frame fr= new Frame();
	
	TrainGUI() {
		System.out.println("StartupTraingui");
		// Creating Frame
		fr.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {

				System.exit(0);
			}
		});
		// Creating a label
		Label lb = new Label("UserId: ");
		// adding label to the frame
		fr.add(lb);
		// Creating Text Field
		TextField t = new TextField();
		// adding text field to the frame
		fr.add(t);
		// setting frame size
		fr.setSize(1000, 600);

		// Setting the layout for the Frame
		fr.setLayout(new FlowLayout());

		fr.setVisible(true);
	}

	public static void main(String args[]) {
		System.out.println("Startupmain");
		TrainGUI ex = new TrainGUI();
		JComboBox<Integer> jComboBox1 = new JComboBox<Integer>();
		ex.fr.add(jComboBox1);
		Label lb = new Label("UserId: ");
		ex.fr.add(lb);
		for (Train t : tc.getTrains()) {
			jComboBox1.addItem(t.getTrainid());
			System.out.println("Train found");
		}

		jComboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer selected = (Integer) jComboBox1.getSelectedItem();
				Train train = tc.getTrainbyID(selected);
				for (Wagon w : train.getWagonlist()) {
					if (w.getClass() == Passengerswagon.class) {
						System.out.println("Passengerswagon");
						Passengerswagon p1 = (Passengerswagon) w;
						System.out.println("Total seats: " + p1.getSeats());
					}
					if (w.getClass() == LiquidCargowagon.class) {
						System.out.println("LiquidCargowagon");
						LiquidCargowagon p1 = (LiquidCargowagon) w;
						System.out.println("Content in liters: " + p1.getContentliters());
					}
					if (w.getClass() == SolidCargowagon.class) {
						System.out.println("SolidCargowagon");
						SolidCargowagon p1 = (SolidCargowagon) w;
						System.out.println("Content in cubic meters: " + p1.getContentcubic());
					}
					if (w.getClass() == Locomotive.class) {
						System.out.println("Locomotive");
						Locomotive p1 = (Locomotive) w;
						System.out.println("Seat: " + p1.getSeats());
					}
				}

			}
		});
	}
}
