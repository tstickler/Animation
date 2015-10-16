/* 
 * Programmer's name: Tyler Stickler
 * Email address:     Stickler@csu.fullerton.edu
 * Course:            CPSC223J
 * Assignment number: 3
 * Due date:          Oct 18, 2015
 * Title:             Animation
 * Purpose:           Sets up and moves the items in the graphics panel
 * This file name:    GraphicInterface.java
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GraphicInterface extends JPanel {
	int panelWidth;
	int panelHeight;
	final int ballSize = 24;
	final int lineSize = 1;
	int ballXCoordinate = 0;
	int ballYCoordinate = 0;
	
	public void paint(Graphics g){
		super.paint(g);
		
		// Gets the size of the graphics panel to know be able to draw the rectangle and the ball
		panelWidth = getWidth();
		panelHeight = getHeight();
		
		// Draws a black rectangle starting at x=12, y=12.
		// The width and height of the rectangle are found by removing 12 on every side of
		// the rectangle and 1 more for the line density.
		g.setColor(Color.BLACK);
		g.drawRect(12, 12, panelWidth-25, panelHeight-25);
		
		// Draws a red ball that is 24 pixels. It starts at position x=0, y=0.
		g.setColor(Color.RED);
		g.fillOval(ballXCoordinate, ballYCoordinate, ballSize, ballSize);
	}
	
	public void moveBall(){
		
		// If the ball is on the top and is not at the top right corner,
		// increase x by one (move the ball one pixel to the right).
		if(ballYCoordinate == 0 && ballXCoordinate != panelWidth-25){
			ballXCoordinate = ballXCoordinate + 1;
			
			// If we hit the top right corner, move the ball down one pixel.
			// Sets up for the next if statement.
			if(ballXCoordinate == panelWidth - 25)
				ballYCoordinate = ballYCoordinate+1;
		}
		// If the ball is not at the top and is on the far right,
		// increase y by one (move the ball one pixel down).
		else if(ballYCoordinate != 0 && ballXCoordinate == panelWidth-25){
			ballYCoordinate = ballYCoordinate + 1;
			
			// If we hit the bottom right corner, move the ball left one pixel.
			// Sets up for the next if statement.
			if(ballYCoordinate == panelHeight-25)
				ballXCoordinate = panelWidth-26;
		}
		
		// If the ball is on the bottom and is not on the far right,
		// decrease x by one (move the ball one pixel to the left).
		else if(ballYCoordinate == panelHeight-25 && ballXCoordinate != panelWidth-25){
			ballXCoordinate = ballXCoordinate - 1;
			
			// If we hit the bottom left corner, move the ball up one pixel.
			// Sets up for the next if statement.
			if(ballXCoordinate == 0)
				ballYCoordinate = panelHeight - 26;
		}
		
		// If the ball is not at the bottom and is on the far left,
		// decrease y by one (move the ball one pixel up).
		else if(ballYCoordinate != panelHeight - 25 && ballXCoordinate == 0){
			ballYCoordinate = ballYCoordinate - 1;
			
			// If we hit the top right corner, move the ball right one pixel.
			// Sets up for the next if statement.
			if(ballYCoordinate == 0)
				ballXCoordinate = 1;
		}
	}
}