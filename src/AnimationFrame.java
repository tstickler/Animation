/* 
 * Programmer's name: Tyler Stickler
 * Email address:     Stickler@csu.fullerton.edu
 * Course:            CPSC223J
 * Assignment number: 3
 * Due date:          Oct 18, 2015
 * Title:             Animation
 * Purpose:           Constructs the frame for the animation
 * This file name:    AnimationFrame.java
*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationFrame extends JFrame {
	
	// Title Panel
	JPanel titlePanel = new JPanel();
	JLabel titleLabel = new JLabel("Animation by Tyler Stickler");
	
	// Graphics Panel
	GraphicInterface graphicsPanel = new GraphicInterface();
	
	// Bottom Panel
	JPanel bottomPanel = new JPanel(new GridBagLayout());
	JPanel buttonsPanel = new JPanel(new GridBagLayout());
	JButton goButton = new JButton("Go");
	JButton resetButton = new JButton("Reset");
	JButton exitButton = new JButton("Exit");
	JLabel speedLabel = new JLabel("Speed(Hz)");
	JTextField speedField = new JTextField(10);
	JPanel outputPanel = new JPanel(new GridBagLayout());
	JLabel directionLabel = new JLabel("Direction:");
	JTextField direction = new JTextField(10);
	JLabel coordinatesLabel = new JLabel("Coordinates of the ball(X, Y): ");
	JTextField coordinates = new JTextField(10);
	ButtonHandler buttonHandler = new ButtonHandler();
	ClockHandler clockHandler = new ClockHandler();
	Timer clock;
	int delayInterval = 1;
	
	public AnimationFrame() {
		// Add the title panel to the frame.
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
		
		// Add the graphics panel to the frame.
		add(graphicsPanel, BorderLayout.CENTER);
		
		// Attach the buttons and speed compnents to their panel.
		addItem(buttonsPanel, speedLabel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addItem(buttonsPanel, speedField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addItem(buttonsPanel, goButton, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addItem(buttonsPanel, resetButton, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(buttonsPanel, exitButton, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		// Attach all the output components to their panel.
		addItem(outputPanel, directionLabel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(outputPanel, direction, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addItem(outputPanel, coordinatesLabel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(outputPanel, coordinates, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		direction.setEditable(false);
		direction.setText("Right");
		coordinates.setEditable(false);
		coordinates.setText("(0, 0)");
		
		// Attach the input and output panels to the bottom panel.
		addItem(bottomPanel, buttonsPanel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(bottomPanel, outputPanel, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		add(bottomPanel, BorderLayout.SOUTH);
		
		// Set up button and clock for interactability.
		goButton.addActionListener(buttonHandler);
		resetButton.addActionListener(buttonHandler);
		exitButton.addActionListener(buttonHandler);
		clock = new Timer(delayInterval, clockHandler);
	}// End constructor
	
	private void addItem(JPanel myPanel, JComponent item, int xGrid, int yGrid, int width, int height, int location, int fill) {
		GridBagConstraints cons = new GridBagConstraints();
		
		// Sets the constraints for the grid
		
		cons.gridx = xGrid;
		cons.gridy = yGrid;
		cons.gridwidth = width;
		cons.gridheight = height;
		cons.weightx=1;
		cons.weighty=1;
		cons.anchor = location;
		cons.fill = fill;
		
		myPanel.add(item, cons);
			
	} // End addItem
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Happens if you click the go button.
			if(event.getSource() == goButton) {
				// If the ball is not currently in motion
				if(goButton.getText() == "Go"){
					// Read from the speed field to set ball speed
					try{
						delayInterval = 1000 / Integer.parseInt(speedField.getText());
						
						// Handles if a negative is inputted
						if (delayInterval < 0){
							delayInterval = 1;
						}
					}catch(ArithmeticException e){
						// Handles if a 0 is inputted
						delayInterval = 1;
					}catch(NumberFormatException e){
						// Handles if a non number is inputted
						delayInterval = 1;
					}
					
					// Sets the delay, starts the clock, changes Go button tex to Pause.
					clock.setDelay(delayInterval);
					clock.start();
					goButton.setText("Pause");
				} 
				// If the ball is in motion.
				else{
					if(goButton.getText() == "Pause"){
						
						// Stops the clock and resets the Go button text.
						clock.stop();
						goButton.setText("Go");
					}
				}
			}
			// Happens if you click the reset button.
			if(event.getSource() == resetButton) {
				// The clock stops so the ball stops moving and all items are reset to intial positions.
				clock.stop();
				goButton.setText("Go");
				speedField.setText("");
				graphicsPanel.ballXCoordinate = 0;
				graphicsPanel.ballYCoordinate = 0;
				coordinates.setText("(0, 0)");
				direction.setText("Right");
				graphicsPanel.repaint();
			}
			// Happens if you click the exit button
			if(event.getSource() == exitButton){
				// Closes the program
				System.exit(0);
			}
		}// End actionPerformed
	} // End ButtonHandler class
	
	private class ClockHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// This happens if the clock is running.
			if(event.getSource() == clock){
				// The ball is moved and the graphic field is repainted
				graphicsPanel.moveBall();
				graphicsPanel.repaint();
				
				// Displays the ball's current x and y position in for (X, Y).
				coordinates.setText("(" + graphicsPanel.ballXCoordinate + ", " + graphicsPanel.ballYCoordinate + ")");
				
				// Sets the direction field according to the location of the ball.
				if(graphicsPanel.ballXCoordinate == graphicsPanel.getWidth()-25)
					direction.setText("Down");
				if(graphicsPanel.ballYCoordinate == graphicsPanel.getHeight()-25)
					direction.setText("Left");
				if(graphicsPanel.ballXCoordinate == 0)
					direction.setText("Up");
				if(graphicsPanel.ballYCoordinate == 0)
					direction.setText("Right");
			}
		}// End actionPerformed
	}// End ClockHandler class
}// End AnimationFrame class
