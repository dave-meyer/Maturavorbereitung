package control;

import java.awt.Dimension;
import java.awt.Toolkit;

import view.Spielfeld;
import view.Start;

/**
 * Minesweeper GUI
 * @author David Meyer
 *
 */
public class Main {

	public final static int minX = 5;
	public final static int minY = 5;
	public final static int maxX = 20;
	public final static int maxY = 20;
	public final static float minMinenPercent = 0;
	public final static float maxMinenPercent = 70;
	
	public static void main(String[] args) {
		
		int x = 5;
		int y = 5;
		int anzP = 2;
		if(args != null) {
			if(args.length > 2) {
				x = Integer.parseInt(args[0]);
				y = Integer.parseInt(args[1]);
				anzP = Integer.parseInt(args[2]);
			}
		}

		Spielfeld feld = new Spielfeld();
		Start start = new Start(feld);
		start.init();
		start.setX(x);
		start.setY(y);
		start.setAnzMinen(anzP);
	}
	
	public static Dimension getMiddleCoordinates(int width, int height) {
		Dimension d = new Dimension();
		d = Toolkit.getDefaultToolkit().getScreenSize();
		d.setSize(d.getWidth()/2-width/2, d.getHeight()/2-height/2);
		return d;
	}
	
}
