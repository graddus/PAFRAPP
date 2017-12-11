package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.LayoutQueue;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class TrainGUI {

	public static JComboBox cbAllTrains;
	public final static TrainController tc = new TrainController();

	public TrainGUI() {
		// Setup of GUI
		JFrame frame = new JFrame("RichRail1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		// contentPane.setOpaque(true);
		// contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		// Adding all the ui Components
		JLabel lbNewTrain = new JLabel("New Train: ");
		lbNewTrain.setSize(300, 30);
		lbNewTrain.setLocation(5, 250);
		contentPane.add(lbNewTrain);

		JTextField tfNewTrain = new JTextField("Train name?");
		tfNewTrain.setSize(100, 30);
		tfNewTrain.setLocation(5, 300);
		contentPane.add(tfNewTrain);

		JButton btnNewTrain = new JButton("Add Train");
		btnNewTrain.setSize(100, 30);
		btnNewTrain.setLocation(5, 350);
		contentPane.add(btnNewTrain);

		cbAllTrains = new JComboBox();
		cbAllTrains.setSize(150, 30);
		cbAllTrains.setLocation(200, 250);
		contentPane.add(cbAllTrains);

		JButton btnDeleteTrain = new JButton("Delete Train");
		btnDeleteTrain.setSize(130, 30);
		btnDeleteTrain.setLocation(200, 350);
		contentPane.add(btnDeleteTrain);
		// Wagonbuttons
		JButton btnAddWagon1 = new JButton("Add Wagon1");
		btnAddWagon1.setSize(130, 30);
		btnAddWagon1.setLocation(400, 250);
		contentPane.add(btnAddWagon1);

		JButton btnDeleteWagon1 = new JButton("Delete Wagon1");
		btnDeleteWagon1.setSize(130, 30);
		btnDeleteWagon1.setLocation(550, 250);
		contentPane.add(btnDeleteWagon1);

		JButton btnAddWagon2 = new JButton("Add Wagon2");
		btnAddWagon2.setSize(130, 30);
		btnAddWagon2.setLocation(400, 350);
		contentPane.add(btnAddWagon2);

		JButton btnDeleteWagon2 = new JButton("Delete Wagon2");
		btnDeleteWagon2.setSize(130, 30);
		btnDeleteWagon2.setLocation(550, 350);
		contentPane.add(btnDeleteWagon2);

		JButton btnAddWagon3 = new JButton("Add Wagon3");
		btnAddWagon3.setSize(130, 30);
		btnAddWagon3.setLocation(400, 450);
		contentPane.add(btnAddWagon3);

		JButton btnDeleteWagon3 = new JButton("Delete Wagon3");
		btnDeleteWagon3.setSize(130, 30);
		btnDeleteWagon3.setLocation(550, 450);
		contentPane.add(btnDeleteWagon3);

		// displaying frame
		frame.setContentPane(contentPane);
		frame.setSize(800, 600);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		// Command button functions
		btnNewTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check Train doesnt exist already
				if (tfNewTrain.getText().length() != 0 && tc.getTrain(tfNewTrain.getText()) == null) {
					// adds a train and locomotive in dao
					tc.createTrain(tfNewTrain.getText());
					loadTrains();
					cbAllTrains.setSelectedItem(tfNewTrain.getText());
					System.out.println("added train:" + getSelectedTrain().getTrainid());

				}

			}
		});

		btnDeleteTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Happens when button is pressed
				if (cbAllTrains.getSelectedItem() != null) {
					System.out.println("deleted train:" + getSelectedTrain().getTrainid());
					tc.deleteTrain(getSelectedTrain());
					loadTrains();

				}
			}
		});

		btnAddWagon1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Happens when button is pressed
				addwagon("Passengerswagon");
			}
		});
		btnDeleteWagon1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Happens when button is pressed
				deleteWagon("Passengerswagon");
			}
		});

	}

	private void addwagon(String wagontype) {
		String id="214";
		//TODO Select max id
		
		if (cbAllTrains.getSelectedItem() != null) {
			
			if (wagontype.equals("Passengerswagon")) {
				tc.createPassengerWagon((id));
			} else if (wagontype.equals("Solid")) {
				tc.createPassengerWagon(id);
			} else {
				//(wagontype.equals("Liquid")) {
				tc.createPassengerWagon(id);
			}
			
			tc.addWagon(tc.getWagon(id), getSelectedTrain());
			System.out.println("created " + wagontype + " with id " +id+ " and added it to train"
					+ getSelectedTrain().getTrainid());
			
			// TODO reload the SELECTED train and redraw it
			// loadTrains();
		}
	}

	private void deleteWagon(String wagontype) {
		if (cbAllTrains.getSelectedItem() != null) {
			Wagon toDelete = null;
			for (Wagon w : tc.getWagons(getSelectedTrain())) {
				if (w.getClass().getSimpleName().toString().equals(wagontype)) {
					toDelete = w;
					//System.out.println("TODELETE=" + toDelete.getWagonid());
				}
			}
			if (toDelete != null) {
				tc.deleteWagon(toDelete);
				System.out.println("deleted wagon with id " + toDelete.getWagonid() + " and removed it from train"
						+ getSelectedTrain().getTrainid());
				// TODO reload the SELECTEDtrain and draw new wagon
				// loadTrains();
			} else {
				// Train doesnt have any wagons of paramater wagontype
			}
		}
	}

	public void loadTrains() {
		cbAllTrains.removeAllItems();
		for (Train t : tc.getTrains()) {
			cbAllTrains.addItem(t.getTrainid());
			// System.out.println("Train found");
		}
	}

	public Train getSelectedTrain() {
		return tc.getTrain(cbAllTrains.getSelectedItem().toString());
	}

	public static void main(String args[]) {
		TrainGUI gui = new TrainGUI();
		gui.loadTrains();

		/*
		 * jComboBox1.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { String
		 * selected = (String) jComboBox1.getSelectedItem(); Train train =
		 * tc.getTrain(selected); for (Wagon w : train.getWagonlist()) { if
		 * (w.getClass() == Passengerswagon.class) {
		 * System.out.println("Passengerswagon"); Passengerswagon p1 =
		 * (Passengerswagon) w; System.out.println("Total seats: " +
		 * p1.getSeats()); } if (w.getClass() == LiquidCargowagon.class) {
		 * System.out.println("LiquidCargowagon"); LiquidCargowagon p1 =
		 * (LiquidCargowagon) w; System.out.println("Content in liters: " +
		 * p1.getContentliters()); } if (w.getClass() == SolidCargowagon.class)
		 * { System.out.println("SolidCargowagon"); SolidCargowagon p1 =
		 * (SolidCargowagon) w; System.out.println("Content in cubic meters: " +
		 * p1.getContentcubic()); } if (w.getClass() == Locomotive.class) {
		 * System.out.println("Locomotive"); Locomotive p1 = (Locomotive) w;
		 * System.out.println("Seat: " + p1.getSeats()); } }
		 * 
		 * } });
		 */
	}
}
