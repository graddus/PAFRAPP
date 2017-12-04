package data;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TrainUI{
TrainController tc=new TrainController();

    private void displayGUI()
    {
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
        
        JLabel label2 = new JLabel(
                "Command:", JLabel.LEFT);
            label2.setSize(100, 20);
            label2.setLocation(5, 910);
        
        TextArea textarea = new TextArea();
        textarea.setSize(1000, 400);
        textarea.setLocation(1, 500);
        textarea.setEditable(false);
        
        TextArea textarea2 = new TextArea();
        textarea2.setSize(1199, 400);
        textarea2.setLocation(1, 50);
        textarea2.setEditable(false);
        
        TextField textfield = new TextField();
        textfield.setSize(375, 20);
        textfield.setLocation(105, 910);

        JButton button = new JButton("EXECUTE");
        button.setSize(100, 30);
        button.setLocation(500, 910);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String command=textfield.getText();
            	textarea.append(command+"\n");
            	if (command.toLowerCase().contains("new")){
            		if (command.toLowerCase().contains("train") && idcontrol(command)==true){
            			String execution = command.replace("new train ","");
            			createTrain(execution);
            			textarea2.append("train "+execution+" created.");
            			createLocomotive(execution);
            		}
            		if (command.toLowerCase().contains("wagon") && idcontrol(command)==true){
            			System.out.println("NEGER");
            			if (command.toLowerCase().contains("numseats")){
            				String numseats=command.substring(23,command.length());
            				System.out.println(numseats);
            		}
            	}
            }
            }
        });
        

        contentPane.add(label);
        contentPane.add(label2);
        contentPane.add(button);
        contentPane.add(textarea);
        contentPane.add(textarea2);
        contentPane.add(textfield);

        frame.setContentPane(contentPane);
        frame.setSize(1200, 1000);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    protected boolean idcontrol(String command){
    	String id = command.replace("new train ","");
    	id = id.replace("new wagon ","");
    	boolean result=false;
    	if (Character.isLetter(id.charAt(0))&& Character.isLetter(id.charAt(1)) && Character.isDigit(id.charAt(2))){
    		result=true;
    	}
    	return result;
    }
    
    protected void createTrain(String id) {
    	tc.createTrain(id);
		
	}
    protected void createLocomotive(String id) {
    	tc.createWagon(id,1, "Locomotive");
		
	}
    protected void createPassengerWagon(String id,int numseats) {
    	tc.createWagon(id,numseats, "Passenger");
		
	}
    protected void createPassengerWagon(String id) {
    	tc.createWagon(id,20, "Passenger");
		
	}
    protected void createSolidCargoWagon(String id,int content) {
    	tc.createWagon(id,content, "SolidCargo");
		
	}
    protected void createLiquidCargoWagon(String id,int content) {
    	tc.createWagon(id,content, "LiquidCargo");
		
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