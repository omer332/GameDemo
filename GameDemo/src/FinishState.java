import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class FinishState extends GameState{
	
boolean active;
	
	String next;
	int playerScore;
	int computerScore;
	String winner;
	
	public void enter(Object memento) {
		active = true;
		int[] scores = (int[])memento;
		playerScore = scores[0];
		computerScore = scores[1];
		
		if(playerScore > computerScore)
			winner = "You won";
		else
			winner = "You lost";
		
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (aKeyCode == KeyEvent.VK_1)
			this.next = "Welcome";
		
		active = false;
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return this.next;
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		String text1 = "Your score: " + playerScore;
		String text2 = "PC score: " + computerScore;
		String text3 = "Press 1 to return to main menu";
		String text4 = "Press esc to exit";
		int textWidth = g.getFontMetrics().stringWidth(text1);
		g.setColor(Color.white);
		g.drawString(text1, (aGameFrameBuffer.getWidth()-textWidth)/4, aGameFrameBuffer.getHeight()/4);
		g.drawString(text2, (aGameFrameBuffer.getWidth()-textWidth)-150, aGameFrameBuffer.getHeight()/4);
		g.drawString(winner, (aGameFrameBuffer.getWidth()-textWidth)/2 , aGameFrameBuffer.getHeight()/2 - 80);
		g.drawString(text3, (aGameFrameBuffer.getWidth()-textWidth)/2 -40 , aGameFrameBuffer.getHeight()/2 +100);
		g.drawString(text4, (aGameFrameBuffer.getWidth()-textWidth)/2 -30, aGameFrameBuffer.getHeight()/2 +150);
		

	}

}
