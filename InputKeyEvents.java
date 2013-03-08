package rogue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.JFrame;
public class InputKeyEvents extends JFrame implements KeyListener {
	private int PlayerX=0, PlayerY=0;
	
	public static void main (String[] args) {
		new InputKeyEvents();
	}
	
	public InputKeyEvents() {
		super("InputKeyEvents: ");
		setSize(500,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		}
		public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle(0,0,500,400));
		g2d.setColor(Color.BLACK);
		g2d.drawString("Movement on the : ", 20, 40);
		g2d.drawString("X-axis: " + PlayerX, 20, 60);
		g2d.drawString("Y-axis " + PlayerY, 20, 80);
		}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch (keycode) {
			case (KeyEvent.VK_UP):
				{PlayerY--; repaint();
				break;}
			case (KeyEvent.VK_DOWN):
				{PlayerY++; repaint();
				break;}
			case (KeyEvent.VK_LEFT):
				{PlayerX--; repaint();
				break;}
			case (KeyEvent.VK_RIGHT):
				{PlayerX++; repaint();
				break;}
		}
		
		
	}
}