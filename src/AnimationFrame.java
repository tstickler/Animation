import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationFrame extends JFrame {
	JPanel titlePanel = new JPanel();
	JLabel titleLabel = new JLabel("Animation by Tyler Stickler");
	
	GraphicInterface graphicsPanel = new GraphicInterface();
	
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
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
		
		add(graphicsPanel, BorderLayout.CENTER);
		
		addItem(buttonsPanel, speedLabel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addItem(buttonsPanel, speedField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addItem(buttonsPanel, goButton, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addItem(buttonsPanel, resetButton, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(buttonsPanel, exitButton, 2, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		addItem(outputPanel, directionLabel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(outputPanel, direction, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		addItem(outputPanel, coordinatesLabel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(outputPanel, coordinates, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		direction.setEditable(false);
		direction.setText("Right");
		coordinates.setEditable(false);
		coordinates.setText("(0, 0)");
		
		addItem(bottomPanel, buttonsPanel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		addItem(bottomPanel, outputPanel, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		add(bottomPanel, BorderLayout.SOUTH);
		
		goButton.addActionListener(buttonHandler);
		resetButton.addActionListener(buttonHandler);
		exitButton.addActionListener(buttonHandler);
		clock = new Timer(delayInterval, clockHandler);
	}
	
	private void addItem(JPanel myPanel, JComponent item, int xGrid, int yGrid, int width, int height, int location, int fill) {
		GridBagConstraints cons = new GridBagConstraints();
		
		// Sets the constraints for the grid
		
		cons.gridx = xGrid;
		cons.gridy = yGrid;
		cons.gridheight = height;
		cons.gridwidth = width;
		cons.weightx=1;
		cons.weighty=1;
		cons.anchor = location;
		cons.fill = fill;
		
		myPanel.add(item, cons);
			
	} // End addItem function
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == goButton) {
				if(goButton.getText() == "Go"){
					try{
						delayInterval = 1000 / Integer.parseInt(speedField.getText());
					}catch(ArithmeticException e){
						delayInterval = 1;
					}catch(NumberFormatException e){
						delayInterval = 1;
					}
					clock.setDelay(delayInterval);
					clock.start();
					goButton.setText("Pause");
				} else{
					if(goButton.getText() == "Pause"){
						clock.stop();
						goButton.setText("Go");
					}
				}
			}
			if(event.getSource() == resetButton) {
				clock.stop();
				goButton.setText("Go");
				speedField.setText("");
				graphicsPanel.ballXCoordinate = 0;
				graphicsPanel.ballYCoordinate = 0;
				coordinates.setText("(0, 0)");
				direction.setText("Right");
				graphicsPanel.repaint();
			}
			if(event.getSource() == exitButton){
				System.exit(0);
			}
		}
	}
	
	private class ClockHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()== clock)
			{
				graphicsPanel.moveBall();
				graphicsPanel.repaint();
				
				coordinates.setText("(" + graphicsPanel.ballXCoordinate + ", " + graphicsPanel.ballYCoordinate + ")");
				
				if(graphicsPanel.ballXCoordinate == graphicsPanel.getWidth()-25)
					direction.setText("Down");
				if(graphicsPanel.ballYCoordinate == graphicsPanel.getHeight()-25)
					direction.setText("Left");
				if(graphicsPanel.ballXCoordinate == 0)
					direction.setText("Up");
				if(graphicsPanel.ballYCoordinate == 0)
					direction.setText("Right");
			}
		}
		
	}

}
