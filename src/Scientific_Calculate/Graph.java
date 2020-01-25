/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scientific_Calculate;

/**
 *
 * @author abheeth kotelawala
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class Graph extends JFrame implements ActionListener{
 
	GraphComponent graphArea;
	JTextField inputField;
	
	public Graph() {
		super("Graph maker");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Main panel
		JPanel mainPanel = new JPanel(new BorderLayout());
		//Inputs: text field and Draw button
		JPanel inputs = new JPanel();
		//text field
		inputField = new JTextField(30);
		//button
		JButton button = new JButton("Draw");
		//Add a function to the button
		button.addActionListener(this);
		//Add the text field and the button to the inputs panel
		inputs.add(inputField);
		inputs.add(button);
		//Add the inputs panel to the main one
		mainPanel.add(inputs, BorderLayout.NORTH);
		//Add an area for functions' graphs
		graphArea = new GraphComponent();
		//Add it to the main panel
		mainPanel.add(graphArea);
		//Add the main panel to the default panel
		super.getContentPane().add(mainPanel);
		//Set automatically the best size for each element
		super.pack();
		//Set the frame visible
		super.setVisible(true);
	}
	
	
 
	@Override
	public void actionPerformed(ActionEvent arg0) {
			/*We added this listener only to the Draw button
			 therefore we know for sure that was the element clicked
			 */
			//Update the equation
			graphArea.equation = inputField.getText();
			//repaint the graph area
			graphArea.repaint();
	}
}

