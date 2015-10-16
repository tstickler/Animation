/* 
 * Programmer's name: Tyler Stickler
 * Email address:     Stickler@csu.fullerton.edu
 * Course:            CPSC223J
 * Assignment number: 3
 * Due date:          Oct 18, 2015
 * Title:             Animation
 * Purpose:           Sets up the window and runs the program
 * This file name:    AnimationDriver.java
*/

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;

public class AnimationDriver {
	public static void main(String[] args) {		
		AnimationFrame myFrame = new AnimationFrame();
		
		//Sets the window to open in the middle of the screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dm = tk.getScreenSize();
		int width = 1000;
		int height = 1000;
		int xPos = (dm.width / 2) - width/2;
		int yPos = (dm.height / 2) - height/2;
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocation(xPos,yPos);
		myFrame.setSize(width, height);
		myFrame.setVisible(true);
		myFrame.setTitle("Animation");
	}
}