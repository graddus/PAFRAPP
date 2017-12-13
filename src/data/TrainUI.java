package data;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrainUI {
	TrainController tc = new TrainController();
	TextArea console = new TextArea();
	TextArea output = new TextArea();

	public void displayGUI() {
		//Setup of GUI
		JFrame frame = new JFrame("RichRail");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setOpaque(true);
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Output", JLabel.LEFT);
		label.setSize(300, 30);
		label.setLocation(5, 5);

		JLabel label2 = new JLabel("Command:", JLabel.LEFT);
		label2.setSize(100, 20);
		label2.setLocation(5, 910);

		console.setSize(1000, 400);
		console.setLocation(1, 500);
		console.setEditable(false);

		output.setSize(1199, 400);
		output.setLocation(1, 50);
		output.setEditable(false);

		TextField input = new TextField();
		input.setSize(375, 20);
		input.setLocation(105, 910);

		JButton execute = new JButton("EXECUTE");
		execute.setSize(100, 30);
		execute.setLocation(500, 910);

		//Command button functions
		execute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = input.getText();
				console.append(command + "\n");
				tc.executeCommand(command);
			}
		});

		contentPane.add(label);
		contentPane.add(label2);
		contentPane.add(execute);
		contentPane.add(console);
		contentPane.add(output);
		contentPane.add(input);

		frame.setContentPane(contentPane);
		frame.setSize(1200, 1000);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
}