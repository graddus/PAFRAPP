package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import design.*;

public class TrainGUI {

	public static JComboBox cbAllTrains;
	public static JComboBox cbAllWagons;
	public static JFrame frame;
	public static JPanel contentPane;
	public static JPanel paintPane;
	public final static TrainController tc = new TrainController();

	public void displayGUI() {
		// Setup of GUI
		// JFrame frame = new JFrame();
		frame = new JFrame("RichRail1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		contentPane = new JPanel();
		contentPane.setSize(1000, 1000);
		contentPane.setLocation(0, 250);
		contentPane.setLayout(null);

		paintPane = new JPanel();
		paintPane.setSize(1500, 250);
		paintPane.setLocation(0, 0);
		paintPane.setLayout(null);

		int x = 15;
		int y = 0;
		// Adding all the ui Components
		JLabel lbNewTrain = new JLabel("New Train: ");
		lbNewTrain.setSize(300, 30);
		lbNewTrain.setLocation(x, y);
		contentPane.add(lbNewTrain);

		JTextField tfNewTrain = new JTextField("Train name?");
		tfNewTrain.setSize(100, 30);
		tfNewTrain.setLocation(x, y + 50);
		contentPane.add(tfNewTrain);

		JButton btnNewTrain = new JButton("Add Train");
		btnNewTrain.setSize(100, 30);
		btnNewTrain.setLocation(x, y + 100);
		contentPane.add(btnNewTrain);
		//
		cbAllTrains = new JComboBox();
		cbAllTrains.setSize(150, 30);
		cbAllTrains.setLocation(250, y);
		contentPane.add(cbAllTrains);

		JButton btnDeleteTrain = new JButton("Delete Train");
		btnDeleteTrain.setSize(130, 30);
		btnDeleteTrain.setLocation(250, y + 50);
		contentPane.add(btnDeleteTrain);

		cbAllWagons = new JComboBox();
		cbAllWagons.setSize(150, 30);
		cbAllWagons.setLocation(250, y + 150);
		contentPane.add(cbAllWagons);

		JButton btnDeleteWagon = new JButton("Delete Wagon");
		btnDeleteWagon.setSize(130, 30);
		btnDeleteWagon.setLocation(250, y + 200);
		contentPane.add(btnDeleteWagon);

		// Wagonbuttons
		x = 550;
		JButton btnAddWagon1 = new JButton("+Passengers");
		btnAddWagon1.setSize(130, 30);
		btnAddWagon1.setLocation(x, y);
		btnAddWagon1.setForeground(Color.red);
		contentPane.add(btnAddWagon1);

		JButton btnAddWagon2 = new JButton("+SolidCargo");
		btnAddWagon2.setSize(130, 30);
		btnAddWagon2.setLocation(x, y + 100);
		btnAddWagon2.setForeground(Color.blue);
		contentPane.add(btnAddWagon2);

		JButton btnAddWagon3 = new JButton("+LiquidCargo");
		btnAddWagon3.setSize(130, 30);
		btnAddWagon3.setLocation(x, y + 200);
		btnAddWagon3.setForeground(Color.yellow);
		contentPane.add(btnAddWagon3);

		// extra textfields
		JLabel lbSeats = new JLabel("Seats: ");
		lbSeats.setSize(300, 30);
		lbSeats.setLocation(x, y + 50);
		contentPane.add(lbSeats);

		JTextField tfSeats = new JTextField("20");
		tfSeats.setSize(80, 30);
		tfSeats.setLocation(x + 50, y + 50);
		contentPane.add(tfSeats);

		JLabel lbSolid = new JLabel("Content: ");
		lbSolid.setSize(300, 30);
		lbSolid.setLocation(x, y + 150);
		contentPane.add(lbSolid);

		JTextField tfSolid = new JTextField("100");
		tfSolid.setSize(80, 30);
		tfSolid.setLocation(x + 50, y + 150);
		contentPane.add(tfSolid);

		JLabel lbLiquid = new JLabel("Content: ");
		lbLiquid.setSize(300, 30);
		lbLiquid.setLocation(x, y + 250);
		contentPane.add(lbLiquid);

		JTextField tfLiquid = new JTextField("100");
		tfLiquid.setSize(80, 30);
		tfLiquid.setLocation(x + 50, y + 250);
		contentPane.add(tfLiquid);

		// displaying frame
		mainPanel.add(paintPane);
		mainPanel.add(contentPane);
		frame.setContentPane(mainPanel);
		frame.setSize(800, 600);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		//// Actionlisteners
		btnNewTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTrain(tfNewTrain.getText());
			}
		});

		cbAllTrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getSelectedTrain() != null) {
					loadWagons();
				}
				System.out.println("Selected other train!");
			}
		});

		btnDeleteTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteTrain();
			}
		});

		btnDeleteWagon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteWagon();
			}
		});

		btnAddWagon1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addwagon("Passengerswagon", tfSeats);
			}
		});

		btnAddWagon2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addwagon("SolidCargowagon", tfSolid);
			}
		});

		btnAddWagon3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addwagon("LiquidCargowagon", tfLiquid);
			}
		});
		// AFter setup load Trains and fill comboboxes
		loadTrains("");
	}

	//// Basic Functions
	private void addTrain(String id) {
		// check Train field isnt empty or exist
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(frame, "Input a name for new train");
		} else if (tc.getTrain(id) != null) {
			JOptionPane.showMessageDialog(frame, "Train already exists");
		} else {
			tc.createTrain(id);
			System.out.println("added new train:" + getSelectedTrain().getTrainid());
		}
	}

	private void deleteTrain() {
		if (cbAllTrains.getSelectedItem() != null) {
			// deleting all wagons associated with the train
			for (Wagon w : getSelectedTrain().getWagonlist()) {
				tc.deleteWagon(w);
			}
			tc.deleteTrain(getSelectedTrain());
		} else {
			JOptionPane.showMessageDialog(frame, "No train to delete!");
		}
	}

	private void addwagon(String wagontype, JTextField input) {
		try {
			int content = Integer.parseInt(input.getText());
			String id = tc.getRandomid();
			while (tc.getWagon(id) != null) {
				id = tc.getRandomid();
			}
			if (cbAllTrains.getSelectedItem() != null) {
				if (wagontype.equals("Passengerswagon")) {
					tc.createPassengerWagon(id, content);
				} else if (wagontype.equals("SolidCargowagon")) {
					tc.createSolidCargoWagon(id, content);
				} else {
					tc.createLiquidCargoWagon(id, content);
				}
				tc.addWagon(tc.getWagon(id), getSelectedTrain());
				System.out.println("created " + wagontype + " with id " + id + " and added it to train"
						+ getSelectedTrain().getTrainid());
			} else {
				JOptionPane.showMessageDialog(frame, "No train to add wagon to!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Invalid number was given!");
		}
	}

	private void deleteWagon() {
		if (cbAllWagons.getSelectedItem() != null) {
			String id = (cbAllWagons.getSelectedItem().toString());
			Wagon w = tc.getWagon(id);
			tc.deleteWagon(w);
			System.out.println("deleted wagon" + id);
		} else {
			JOptionPane.showMessageDialog(frame, "No wagon to delete!");
		}

	}

	public void loadTrains(String toSelect) {
		cbAllTrains.removeAllItems();
		for (Train t : tc.getTrains()) {
			cbAllTrains.addItem(t.getTrainid());
		}
		cbAllTrains.setSelectedItem(toSelect);
		loadWagons();
	}

	public void loadWagons() {
		cbAllWagons.removeAllItems();
		if (getSelectedTrain() != null) {
			Train selected = getSelectedTrain();
			for (Wagon w : selected.getWagonlist()) {
				if (w.getClass() != Locomotive.class) {
					cbAllWagons.addItem(w.getWagonid());
				}
			}
			try {
				drawTrain(getSelectedTrain());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Train getSelectedTrain() {
		if (cbAllTrains.getSelectedItem() != null) {
			return tc.getTrain(cbAllTrains.getSelectedItem().toString());
		}
		return null;
	}

	public void drawTrain(Train train) throws IOException {
		System.out.println("Drawing train");
		paintPane.removeAll();
		// Draw Locomotive
		JLabel locoLabel = new JLabel();
		locoLabel.setSize(200, 200);
		locoLabel.setLocation(5, 5);
		ImageIcon locoIcon = new ImageIcon();
		BufferedImage locoImg = ImageIO.read(new File("loc.png"));
		locoIcon.setImage(locoImg);
		locoLabel.setIcon(locoIcon);
		paintPane.add(locoLabel);

		// Draw all carts
		int x = 0;
		for (Wagon w : train.getWagonlist()) {
			if (w.getClass() != Locomotive.class) {
				double content = 0;
				String url = "hetplaatje.png";
				if (w.getClass() == Passengerswagon.class) {
					Passengerswagon pw = (Passengerswagon) w;
					content = pw.getSeats();
					url = "pass.png";
				} else if (w.getClass() == SolidCargowagon.class) {
					SolidCargowagon sw = (SolidCargowagon) w;
					content = sw.getContentcubic();
					url = "solid.png";
				} else {
					LiquidCargowagon lw = (LiquidCargowagon) w;
					content = lw.getContentliters();
					url = "liquid.png";
				}
				JLabel idLabel = new JLabel(w.getWagonid());
				idLabel.setSize(200, 20);
				idLabel.setLocation(250 + x * 200, 70);

				JLabel contentLabel = new JLabel("" + content);
				contentLabel.setSize(200, 20);
				contentLabel.setLocation(210 + x * 200, 180);

				JLabel WagonLabel = new JLabel();
				WagonLabel.setSize(200, 200);
				WagonLabel.setLocation(170 + x * 200, 30);
				ImageIcon cartIcon = new ImageIcon();
				BufferedImage cartImg = ImageIO.read(new File(url));
				cartIcon.setImage(cartImg);
				WagonLabel.setIcon(cartIcon);

				paintPane.add(WagonLabel);
				paintPane.add(idLabel);
				paintPane.add(contentLabel);
				x++;
			}
		}
		paintPane.repaint();
		System.out.println("Drawing train ending");
	}
}
