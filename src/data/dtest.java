package data;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class dtest {

	private Frame mainFrame;
	   private Panel controlPanel;
	   private JLabel msglabel;

	   public dtest() throws IOException{
	      prepareGUI();
	   }

	   public static void main(String[] args) throws IOException{
	      dtest  awtContainerDemo = new dtest();          
	      awtContainerDemo.showPanelDemo();
	   }

	   private void prepareGUI() throws IOException{
	      mainFrame = new Frame();
	      mainFrame.setSize(700,700);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	   
	      msglabel = new JLabel();
	      msglabel.setSize(800, 800);

	      controlPanel = new Panel();
	      controlPanel.setLayout(new FlowLayout());

	      ImageIcon icon = new ImageIcon();
	      BufferedImage img = ImageIO.read(new File("loc.png"));
	      icon.setImage(img);
	      msglabel.setIcon(icon);
	     
	      mainFrame.add(controlPanel);
	      mainFrame.setVisible(true);  
	   }

	   private void showPanelDemo(){

	      Panel panel = new Panel();
	      panel.setBackground(Color.WHITE);
	      panel.setLayout(new FlowLayout());        
	      panel.add(msglabel);

	      controlPanel.add(panel);

	      mainFrame.setVisible(true);  
	   }
}
