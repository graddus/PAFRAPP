package data;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class TrainUI
{
    private void displayGUI()
    {
    	TrainController tc=new TrainController();
        JFrame frame = new JFrame("RichRail");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        JLabel label = new JLabel(
            "This JPanel uses Absolute Positioning"
                                    , JLabel.LEFT);
        label.setSize(300, 30);
        label.setLocation(5, 5);
        
        JComboBox<Integer> jComboBox1 = new JComboBox<Integer>();
        for (Train t:tc.getTrains()){
        jComboBox1.addItem(t.getTrainid());
        }
        
        jComboBox1.addActionListener (new ActionListener () {
        	@Override
            public void actionPerformed(ActionEvent e) {
            	Integer selected=(Integer)jComboBox1.getSelectedItem();
            	Train train=tc.getTrainbyID(selected);
            	for (Wagon w:train.getWagonlist()){
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
        });
        

        jComboBox1.setSize(50, 25);
        jComboBox1.setLocation(200, 200);

        JButton button = new JButton("USELESS");
        button.setSize(100, 30);
        button.setLocation(95, 45);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Train t1=tc.getTrains().get(0);
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
        });

        contentPane.add(label);
        contentPane.add(button);
        contentPane.add(jComboBox1);

        frame.setContentPane(contentPane);
        frame.setSize(1000, 800);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new TrainUI().displayGUI();
            }
        });
    }
}