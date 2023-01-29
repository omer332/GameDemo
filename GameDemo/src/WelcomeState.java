import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class WelcomeState extends GameState {

	boolean active;
	
	String next;
	
	public void enter(Object memento) {
		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (aKeyCode == KeyEvent.VK_1)
			this.next = "Easy";
		if(aKeyCode == KeyEvent.VK_2)
			this.next = "Hard";
		
		active = false;
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return this.next;
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		
		String text1 = "Welcome to Pong";
		String text2 = "Press 1 to play easy mode";
		String text3 = "Press 2 to play hard mode";
		int textWidth = g.getFontMetrics().stringWidth(text1);
		g.setColor(Color.white);
		g.drawString(text1, (aGameFrameBuffer.getWidth()-textWidth)/2, aGameFrameBuffer.getHeight()/4);
		g.drawString(text2, (aGameFrameBuffer.getWidth()-textWidth)/2 -20, aGameFrameBuffer.getHeight()/2);
		g.drawString(text3, (aGameFrameBuffer.getWidth()-textWidth)/2 -20, aGameFrameBuffer.getHeight()/2 +50);

	}
}
