package control;

import java.util.Random;

/**
 * Minesweeper in der Konsole mit Laufzeitparametern
 * @author David Meyer
 */
public class MainConsole {

	static int xMax = 5;
	static int yMax = 8;
	static int anzPunkte = 3;
	static int[][] spielfeld;
	
	public static void main(String[] args) {
		
		if(args != null) {
			if(args.length > 2) {
				xMax = Integer.parseInt(args[0]);
				yMax = Integer.parseInt(args[0]);
				anzPunkte = Integer.parseInt(args[0]);
			}
		}
		if(anzPunkte >= (xMax*yMax)) {
			System.out.println("Die Anzahl der Bomben ist mehr als Platz ist");
			anzPunkte = (xMax*yMax);
		}
		
		spielfeld = new int[yMax][xMax];
		
		for(int i = 0; i < spielfeld.length; i++) {
			for(int j = 0; j < spielfeld[i].length; j++) {
				spielfeld[i][j] = 0;
			}
		}
		
		int randX;
		int randY;
		Random rand = new Random();
		for(int i = 0; i < anzPunkte; i++) {
			do {
				randX = rand.nextInt(xMax);
				randY = rand.nextInt(yMax);
			}
			while(spielfeld[randY][randX] == -1);
			setPunkt(randX, randY);
		}
		
		for(int i = 0; i < spielfeld.length; i++) {
			for(int j = 0; j < spielfeld[i].length; j++) {
				
				if(spielfeld[i][j] == -1) System.out.print("*\t");
				else System.out.print(spielfeld[i][j]+"\t");
			}
			System.out.println();
		}		
	}
	
	public static void setPunkt(int x, int y) {
		spielfeld[y][x] = -1;
		int yMin = y-1;
		int yMax = y+1;
		if(yMin < 0) yMin = 0;
		if(yMax > (MainConsole.yMax-1)) yMax = MainConsole.yMax-1;
		
		int xMin = x-1;
		int xMax = x+1;
		if(xMin < 0) xMin = 0;
		if(xMax > (MainConsole.xMax-1)) xMax = MainConsole.xMax-1;
		
		for(int i = yMin; i <= yMax; i++) {
			for(int j = xMin; j <= xMax; j++) {
				if(spielfeld[i][j] != -1) {
					spielfeld[i][j]++;
				}
			}
		}
	}
}