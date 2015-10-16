import javax.swing.*;

import java.awt.*;

public class GraphicInterface extends JPanel {
	int panelWidth;
	int panelHeight;
	final int ballSize = 24;
	final int lineSize = 1;
	int ballXCoordinate=0;
	int ballYCoordinate=0;
	
	public void paint(Graphics g){
		super.paintComponent(g);
		
		panelWidth = getWidth();
		panelHeight = getHeight();
		
		g.setColor(Color.BLACK);
		g.drawRect(12, 12, panelWidth-25, panelHeight-25);
		g.setColor(Color.RED);
		g.fillOval(ballXCoordinate, ballYCoordinate, ballSize, ballSize);
	}
	
	public void moveBall(){
		// Make sure to clean this function up
		if(ballYCoordinate == 0 && ballXCoordinate != panelWidth-25){
			ballXCoordinate = ballXCoordinate + 1;
			if(ballXCoordinate == panelWidth - 25)
				ballYCoordinate = ballYCoordinate+1;
		}else if(ballYCoordinate != 0 && ballXCoordinate == panelWidth-25){
			ballYCoordinate = ballYCoordinate + 1;
			if(ballYCoordinate == panelHeight-25)
				ballXCoordinate = panelWidth-26;
		}else if(ballYCoordinate == panelHeight-25 && ballXCoordinate != panelWidth-25){
			ballXCoordinate = ballXCoordinate - 1;
			if(ballXCoordinate == 0)
				ballYCoordinate = panelHeight - 26;
		}else if(ballYCoordinate != panelHeight - 25 && ballXCoordinate == 0){
			ballYCoordinate = ballYCoordinate - 1;
			if(ballYCoordinate == 0)
				ballXCoordinate = 1;
		}
	}
}