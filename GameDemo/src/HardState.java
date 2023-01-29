import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class HardState extends GameState{
	
boolean active;
	
	float deltaTimeAverage;
	
	float x_leftBat, y_leftBat, x_rightBat, y_rightBat, x_ball, y_ball;
	float x_obstacle1, y_obstacle1, x_obstacle2, y_obstacle2;
	int moveDistance = 15;
	
	int diff;
	
	int leftScore, rightScore;
	
	boolean left = true;
	
	public HardState() {
		x_leftBat = 5;
		x_rightBat = 630;
		x_ball = 310;
		y_leftBat = 180;
		y_rightBat = 180;
		y_ball = 250;
		x_obstacle1 = 310;
		y_obstacle1 = 0;
		x_obstacle2 = 310;
		y_obstacle2 = 400;
		
	}
	
	public void enter(Object memento) {
		active = true;
		deltaTimeAverage = 0;
		diff = 0;
		rightScore = 0;
		leftScore = 0;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if(aKeyCode == KeyEvent.VK_W) {
			if(!(y_leftBat-this.moveDistance <= 0))
				this.y_leftBat -= this.moveDistance;
		}
		
		if(aKeyCode == KeyEvent.VK_S) {
			if(!(y_leftBat+this.moveDistance >= 380))
				this.y_leftBat += this.moveDistance;
		}
			
	}
	
	public void update(long deltaTime) {
		deltaTimeAverage = deltaTimeAverage* 0.9f + 0.1f*(float)deltaTime;
		
		if(y_rightBat + 50 <= y_ball ) {
			if(y_rightBat <= 380)
			y_rightBat += 2;
		}
		
		else
			if(y_rightBat >= 0)
				y_rightBat -= 2;
		
		
		if(y_ball <= 7)
			diff = 2;
		if(y_ball >= 470)
			diff = -2;
		
		
		if(this.left) {
			x_ball = x_ball + 0.2f*deltaTime;
			y_ball = y_ball + diff ;
			if(x_ball >= x_rightBat -8 && y_ball >= y_rightBat && y_ball <= y_rightBat +100) {
				if(y_ball <= y_rightBat +50)
					diff = -2;
				else
					diff = 2;
				this.left = false;
			}
		}
		else {
			x_ball = x_ball - 0.2f*deltaTime;
			y_ball = y_ball + diff ;
			if(x_ball <= x_leftBat+8 && y_ball >= y_leftBat && y_ball <= y_leftBat +100) {
				if(y_ball <= y_leftBat +50)
					diff = -2;
				else
					diff = 2;
				this.left = true;
			}
		}
		// up left --
		if(x_ball >= x_obstacle1 - 4 && x_ball <= x_obstacle1 && y_ball <= y_obstacle1 + 80) {
			if(y_ball <= y_obstacle1 + 40)
				diff = -1;
			else
				diff = 1;
			x_ball = x_ball - 0.2f*deltaTime;
			y_ball = y_ball + diff ;
			left = false;
		}
		// up right --
		if(x_ball <= x_obstacle1 + 6 && x_ball >= x_obstacle1 && y_ball <= y_obstacle1 + 80) {
			if(y_ball <= y_obstacle1 + 40)
				diff = -1;
			else
				diff = 1;
			x_ball = x_ball + 0.2f*deltaTime;
			y_ball = y_ball + diff ;
			left = true;
			
		}
		//down left --
		if(x_ball >= x_obstacle2 - 4 && x_ball <= x_obstacle2 && y_ball >= y_obstacle2 ) {
			if(y_ball >= y_obstacle2 + 40)
				diff = 1;
			else
				diff = -1;
			x_ball = x_ball - 0.2f*deltaTime;
			y_ball = y_ball + diff ;
			left = false;
		}
		// down right
		if(x_ball <= x_obstacle2 + 4 && x_ball >= x_obstacle2 && y_ball >= y_obstacle2) {
			if(y_ball >= y_obstacle2 + 40)
				diff = 1;
			else
				diff = -1;
			x_ball = x_ball + 0.21f*deltaTime;
			y_ball = y_ball + diff ;
			
			left = true;
			
		}
		
		
		if(x_ball < 0) {
			rightScore ++;
			resetGameState();
		}
			
		if(x_ball > 640) {
			leftScore ++;
			resetGameState();
		}
		
		if(rightScore == 3 || leftScore == 3)
			this.active = false;
		
	}
	
	public void resetGameState() {
		x_leftBat = 5;
		x_rightBat = 630;
		x_ball = 310;
		y_leftBat = 180;
		y_rightBat = 180;
		y_ball = 250;
		deltaTimeAverage = 0;
		diff = 0;
	}
	
public boolean isActive() { return active; }
	
	public String next() {
		return "Finish"; // ToDo - change next state
	}
	public Object memento() {
		int[] scores = {rightScore, leftScore};
		return scores;
	}
	
	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics s1 = aGameFrameBuffer.graphics();
		Graphics s2 = aGameFrameBuffer.graphics();
		Graphics g = aGameFrameBuffer.graphics();
		Graphics w = aGameFrameBuffer.graphics();
		Graphics w2 = aGameFrameBuffer.graphics();
		Graphics o1 = aGameFrameBuffer.graphics();
		Graphics o2 = aGameFrameBuffer.graphics();
		
		w.setColor(Color.white);
		w.draw3DRect(5, (int)this.y_leftBat, 2, 100, false);
		
		o1.setColor(Color.white);
		o1.draw3DRect((int)x_obstacle1, (int)y_obstacle1, 4, 80, false);
		
		o2.setColor(Color.white);
		o2.draw3DRect((int)x_obstacle2,(int)y_obstacle2, 4, 80, false);
		
		w2.setColor(Color.white);
		w2.draw3DRect(630, (int)this.y_rightBat, 2, 100, false);
		
		g.setColor(Color.white);
		g.drawOval((int)x_ball, (int)y_ball, 10, 10);
		
		s1.drawString(String.valueOf(leftScore), 5, 12);
		s2.drawString(String.valueOf(rightScore), 630, 12);
	}

}


