package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.LayoutQueue;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;
import oracle.net.aso.s;

public class TrainGUI {
	
	public static JComboBox cbAllTrains;
	public static JComboBox cbAllWagons;
	public final static TrainController tc = new TrainController();

	public TrainGUI() {
		// Setup of GUI
		JFrame frame = new JFrame("RichRail1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		JPanel jPanel2 = new JPanel();
		// contentPane.setOpaque(true);
		// contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		int x=15;
		
		// Adding all the ui Components
		JLabel lbNewTrain = new JLabel("New Train: ");
		lbNewTrain.setSize(300, 30);
		lbNewTrain.setLocation(x, 250);
		contentPane.add(lbNewTrain);
		
		

		JTextField tfNewTrain = new JTextField("Train name?");
		tfNewTrain.setSize(100, 30);
		tfNewTrain.setLocation(x, 300);
		contentPane.add(tfNewTrain);

		JButton btnNewTrain = new JButton("Add Train");
		btnNewTrain.setSize(100, 30);
		btnNewTrain.setLocation(x, 350);
		contentPane.add(btnNewTrain);
		//
		cbAllTrains = new JComboBox();
		cbAllTrains.setSize(150, 30);
		cbAllTrains.setLocation(250, 250);
		contentPane.add(cbAllTrains);
	
		JButton btnDeleteTrain = new JButton("Delete Train");
		btnDeleteTrain.setSize(130, 30);
		btnDeleteTrain.setLocation(250, 300);
		contentPane.add(btnDeleteTrain);
		
		cbAllWagons = new JComboBox();
		cbAllWagons.setSize(150, 30);
		cbAllWagons.setLocation(250, 400);
		contentPane.add(cbAllWagons);
		
		JButton btnDeleteWagon = new JButton("Delete Wagon");
		btnDeleteWagon.setSize(130, 30);
		btnDeleteWagon.setLocation(250, 450);
		contentPane.add(btnDeleteWagon);
		
		// Wagonbuttons
		x=550;
		JButton btnAddWagon1 = new JButton("+Passengers");
		btnAddWagon1.setSize(130, 30);
		btnAddWagon1.setLocation(x, 250);
		contentPane.add(btnAddWagon1);

//		JButton btnDeleteWagon1 = new JButton("-Passengers");
//		btnDeleteWagon1.setSize(130, 30);
//		btnDeleteWagon1.setLocation(x+150, 250);
//		//contentPane.add(btnDeleteWagon1);

		JButton btnAddWagon2 = new JButton("+SolidCargo");
		btnAddWagon2.setSize(130, 30);
		btnAddWagon2.setLocation(x, 350);
		contentPane.add(btnAddWagon2);

//		JButton btnDeleteWagon2 = new JButton("-SolidCargo");
//		btnDeleteWagon2.setSize(130, 30);
//		btnDeleteWagon2.setLocation(x+150, 350);
//		//contentPane.add(btnDeleteWagon2);

		JButton btnAddWagon3 = new JButton("+LiquidCargo");
		btnAddWagon3.setSize(130, 30);
		btnAddWagon3.setLocation(x, 450);
		contentPane.add(btnAddWagon3);

//		JButton btnDeleteWagon3 = new JButton("-LiquidCargo");
//		btnDeleteWagon3.setSize(130, 30);
//		btnDeleteWagon3.setLocation(x+150, 450);
//		//contentPane.add(btnDeleteWagon3);

		//extra textfields
		
		JLabel lbSeats = new JLabel("Seats: ");
		lbSeats.setSize(300, 30);
		lbSeats.setLocation(x, 300);
		contentPane.add(lbSeats);
		
		JTextField tfSeats = new JTextField("20");
		tfSeats.setSize(80, 30);
		tfSeats.setLocation(x+50,300);
		contentPane.add(tfSeats);
		
		JLabel lbSolid = new JLabel("Content: ");
		lbSolid.setSize(300, 30);
		lbSolid.setLocation(x,400);
		contentPane.add(lbSolid);
		
		JTextField tfSolid = new JTextField("100");
		tfSolid.setSize(80, 30);
		tfSolid.setLocation(x+50,400);
		contentPane.add(tfSolid);
		
		JLabel lbLiquid = new JLabel("Content: ");
		lbLiquid.setSize(300, 30);
		lbLiquid.setLocation(x,500);
		contentPane.add(lbLiquid);
	
		JTextField tfLiquid= new JTextField("100");
		tfLiquid.setSize(80, 30);
		tfLiquid.setLocation(x+50,500);
		contentPane.add(tfLiquid);
		
		// displaying frame
		
		frame.setContentPane(contentPane);
		//frame.getContentPane().add(,);
		frame.setSize(800, 600);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

////Actionlisteners
		btnNewTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check Train doesnt exist already
				if (tfNewTrain.getText().length() != 0 && tc.getTrain(tfNewTrain.getText()) == null) {
					// adds a train and locomotive in dao
					tc.createTrain(tfNewTrain.getText());
					loadTrains();
					cbAllTrains.setSelectedItem(tfNewTrain.getText());
					loadWagons();
					System.out.println("added train:" + getSelectedTrain().getTrainid());

				}

			}
		});
		
		cbAllTrains.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        loadWagons();
		        System.out.println("Selected other train!");
		    }
		});
		
		btnDeleteTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Happens when button is pressed
				if (cbAllTrains.getSelectedItem() != null) {
					System.out.println("deleted train:" + getSelectedTrain().getTrainid());
					//deleting all wagons associated with the train
					for (Wagon w:getSelectedTrain().getWagonlist()){
						tc.deleteWagon(w);
					}
					tc.deleteTrain(getSelectedTrain());
					loadTrains();
				
				}
			}
		});
		
		btnDeleteWagon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Happens when button is pressed
				if (cbAllWagons.getSelectedItem() != null) {
					deleteWagon(cbAllWagons.getSelectedItem().toString());
					//System.out.println(cbAllWagons.getSelectedItem());
				}
			}
		});
		
		btnAddWagon1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addwagon("Passengerswagon",tfSeats);
			}
		});

		btnAddWagon2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addwagon("SolidCargowagon",tfSolid);
			}
		});
		
		btnAddWagon3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addwagon("LiquidCargowagon",tfLiquid);
			}
		});
	}
////
	private void addwagon(String wagontype, JTextField input) {
		try{
		String S=input.getText();
		
		int content= Integer.parseInt(S);

		int maxid=tc.getMaxWagonId();
		String id=Integer.toString((maxid+1));
		
		if (cbAllTrains.getSelectedItem() != null) {
			if (wagontype.equals("Passengerswagon")) {
				tc.createPassengerWagon(id,content);
			} else if (wagontype.equals("SolidCargowagon")) {
				tc.createSolidCargoWagon(id, content);
			} else {
				//(wagontype.equals("Liquid")) {
				tc.createLiquidCargoWagon(id, content);
			}
			tc.addWagon(tc.getWagon(id), getSelectedTrain());
			System.out.println("created " + wagontype + " with id " +id+ " and added it to train"
					+ getSelectedTrain().getTrainid());
			 loadWagons();	
		}}
		catch(Exception e){
			//TODO add popup invalid number
			System.out.println(e.getMessage());
		}
	}

	private void deleteWagon(String toDelete) {
		//System.out.println(toDelete);
				Wagon w= tc.getWagon(toDelete);
				tc.deleteWagon(w);
				loadWagons();
	}

	public void loadTrains() {
		//empty the dropdown and fill with train+ wagons
		cbAllTrains.removeAllItems();
		for (Train t : tc.getTrains()) {
			cbAllTrains.addItem(t.getTrainid());
			
		}
		loadWagons();
	}
	
	public void drawTrain(Train train) throws IOException {
		
		//Create locomotive
		JLabel locoLabel = new JLabel();
		locoLabel.setSize(200, 200);
		locoLabel.setLocation(5, 5);
		ImageIcon locoIcon = new ImageIcon();
	    BufferedImage locoImg = ImageIO.read(new File("loc.png"));
	    locoIcon.setImage(locoImg);
	    locoLabel.setIcon(locoIcon);
	    
		//Create the cart if they exist
	    int numberOfCarts = tc.getLengthOfTrain(train);
		for(int x = 0;x < numberOfCarts;x++) {
			ImageIcon cartIcon = new ImageIcon();
		    BufferedImage cartImg = ImageIO.read(new File("cart.png"));
		    cartIcon.setImage(cartImg);
		    //jlabel.setIcon(icon);
		}
		
	}

	public void loadWagons(){
		cbAllWagons.removeAllItems();
		Train selected = getSelectedTrain();
		for (Wagon w :selected.getWagonlist()){
			if (w.getClass() != Locomotive.class) {
				cbAllWagons.addItem(w.getWagonid());//+"-"+w.getClass().getSimpleName());
			}
		}
		//TODO Draw the train
	}
	public Train getSelectedTrain() {
		return tc.getTrain(cbAllTrains.getSelectedItem().toString());
	}

	public static void main(String args[]) {
		TrainGUI gui = new TrainGUI();
		gui.loadTrains();
		
		//Graphics g = drawPanel.getGraphics();
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
