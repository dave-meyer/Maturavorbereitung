package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Main;

/**
 * Hauptminenfeld
 * @author David Meyer
 *
 */
public class Spielfeld extends JFrame {

	FieldPanel[][] panels;
	JPanel mainPanel;
	JButton btnAdjustment;
	
	int x;
	int y;
	int anzMinen;
	
	boolean initialized = false;
	
	public Spielfeld() {
		initialized = false;
		try {
			FieldPanel.MineIcon = new ImageIcon(ImageIO.read((getClass().getResource("icons\\Mine.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Spielfeld(int x, int y, int anzMinen) {
		initialized = false;
		this.x = x;
		this.y = y;
		this.anzMinen = anzMinen;
	}
	
	public void init() {
		
		this.setLayout(new BorderLayout());
		
		if(initialized)	resetGrid();
		
		btnAdjustment = new JButton("Einstellungen");
		btnAdjustment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Start s = new Start(Spielfeld.this);
				s.init();
			}
		});
		this.add(btnAdjustment, BorderLayout.SOUTH);
		
		this.setTitle("Minesweeper");
		this.setVisible(true);
		this.validate();
		Dimension d = Main.getMiddleCoordinates(700, 700);
		this.setBounds((int)d.getWidth(), (int)d.getHeight(), 700, 700);
		initialized = true;
	}
	
	public void setupGrid() {
		if(!initialized) {
			init();
		}
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(y, x));
		this.add(mainPanel, BorderLayout.CENTER);
		resetGrid();
		setRandomMinen();
		showGrid();
		System.out.println("Anz in Feld " + panels.length + " " + panels[0].length);
	}
	
	public void resetGrid() {
		panels = new FieldPanel[y][x];
		for(int i = 0; i < panels.length; i++) {
			for(int j = 0; j < panels[i].length; j++) {
				panels[i][j] = new FieldPanel();
				panels[i][j].setValue(0);
				mainPanel.add(panels[i][j]);
			}
		}
	}
	
	public void setRandomMinen() {
		int randX;
		int randY;
		Random rand = new Random();
		for(int i = 0; i < anzMinen; i++) {
			do {
				randX = rand.nextInt(x);
				randY = rand.nextInt(y);
			}
			while(panels[randY][randX].isMine());
			setPunkt(randX, randY);
		}
	}
	
	public void setPunkt(int x, int y) {
		panels[y][x].setMine();
		int yMin = y-1;
		int yMax = y+1;
		if(yMin < 0) yMin = 0;
		if(yMax > (this.y-1)) yMax = this.y-1;
		
		int xMin = x-1;
		int xMax = x+1;
		if(xMin < 0) xMin = 0;
		if(xMax > (this.x-1)) xMax = this.x-1;
		
		for(int i = yMin; i <= yMax; i++) { 
			for(int j = xMin; j <= xMax; j++) {
				if(!panels[i][j].isMine()) {
					panels[i][j].incrementValue();
				}
			}
		}
	}
	
	public void showGrid() {
		for(int i = 0; i < y; i++) { 
			for(int j = 0; j < x; j++) {
				panels[i][j].updateValue();
			}
		}
	}

	public FieldPanel[][] getPanels() {
		return panels;
	}

	public void setPanels(FieldPanel[][] panels) {
		this.panels = panels;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		System.out.println("Set X " + x);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		System.out.println("Set X " + x);
		this.y = y;
	}

	public int getAnzMinen() {
		return anzMinen;
	}

	public void setAnzMinen(int anzMinen) {
		this.anzMinen = anzMinen;
	}	
}
