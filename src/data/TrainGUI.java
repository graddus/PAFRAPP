package data;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrainGUI {
		   TrainGUI()
		   {
		      //Creating Frame    
		      Frame fr=new Frame();       
		      
		      fr.addWindowListener( new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent we) {

		                System.exit(0);
		            }
		        } );
		      //Creating a label
		      Label lb = new Label("UserId: "); 
		      
		      //adding label to the frame
		      fr.add(lb);           
		      
		      //Creating Text Field
		      TextField t = new TextField();
		      
		      //adding text field to the frame
		      fr.add(t);
		      
		      //setting frame size
		      fr.setSize(1000, 800);  
		      
		      //Setting the layout for the Frame
		      fr.setLayout(new FlowLayout());
		            
		      fr.setVisible(true);                
		   }
		   public static void main(String args[])
		   {
		       TrainGUI ex = new TrainGUI(); 
		   }
		}
