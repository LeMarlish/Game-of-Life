package classes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import others.MyMouseListener;

public class Mouse implements MouseListener, MouseMotionListener, MyMouseListener {
	int x, y;
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x;
		int y;
		x = e.getPoint().x;
		y = e.getPoint().y;
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int x;
		int y;
		x = e.getPoint().x;
		y = e.getPoint().y;
		
		
	}
	
	// The methods below are not used.
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Do nothing.
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Do nothing.
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Do nothing.
		
	}

	

	@Override
	public void mouseReleased(MouseEvent e) {
		// Do nothing.
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
