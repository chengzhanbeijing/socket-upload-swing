package socket.utils;

import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;

public class DragJWindow {

	private Window fWindow;
	private Component fComponent;
	private int dX;
	private int dY;
	public DragJWindow(Window fWindow, Component fComponent) {
		super();
		this.fWindow = fWindow;
		this.fComponent = fComponent;
		fComponent.addMouseListener(createMouseListener());
		fComponent.addMouseMotionListener(createMouseMotionListener());
	}
	private MouseMotionAdapter  createMouseMotionListener() {
		return new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point dragPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(dragPoint, fComponent);
                fWindow.setLocation(dragPoint.x - dX, dragPoint.y - dY);
            }
        };
	}
	private MouseListener createMouseListener() {
		return new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Point clickPoint = new Point(e.getPoint());
				SwingUtilities.convertPointToScreen(clickPoint, fComponent);
				dX = clickPoint.x-fWindow.getX();
				dY = clickPoint.y-fWindow.getY();
			}
			
		};
	}
	
	
}
