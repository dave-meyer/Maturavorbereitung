package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Main;

/**
 * Startfenster mit Dateneingabe
 *
 */
public class Start extends JFrame {

	JPanel pnlMain;
	
	JLabel lblX;
	JLabel lblY;
	JLabel lblAnzPkt;
	JLabel lblError;
	
	JTextField txtX;
	JTextField txtY;
	JTextField txtAnzPkt;
	
	JButton btnOK;
	
	Spielfeld feld;
	
	public Start(Spielfeld feld) {
		this.feld = feld;
	}
	
	final static String errorWrapper1 = "<html><body style='text-align: center'>";
	final static String errorWrapper2 = "</body></html>";
	
	public void init() {
		
		pnlMain = new JPanel();
		pnlMain.setLayout(new GridLayout(3, 3));
		
		this.setLayout(new BorderLayout());
		
		lblX = new JLabel("Anzahl Spalten");
		txtX = new JTextField();
		lblY = new JLabel("Anzahl Zeilen");
		txtY = new JTextField();
		lblAnzPkt = new JLabel("Anzahl Minen");
		txtAnzPkt = new JTextField();
		btnOK = new JButton("Okay");
		lblError = new JLabel("");
		lblError.setForeground(new Color(220, 0, 0));
		lblError.setHorizontalAlignment((int) Label.CENTER_ALIGNMENT);
		
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(checkInputs()) {
					transferInputs();
					System.out.println("Feld X " + Integer.parseInt(txtX.getText()));
					System.out.println("Feld Y " + Integer.parseInt(txtX.getText()));
					System.out.println("Feld anzMinen " + Integer.parseInt(txtX.getText()));
					Start.this.setVisible(false);
				}
			}
		});
		
		pnlMain.add(lblX);
		pnlMain.add(txtX);
		pnlMain.add(lblY);
		pnlMain.add(txtY);
		pnlMain.add(lblAnzPkt);
		pnlMain.add(txtAnzPkt);
		this.add(pnlMain, BorderLayout.CENTER);
		this.add(lblError, BorderLayout.NORTH);
		this.add(btnOK, BorderLayout.SOUTH);
		
		this.setTitle("Minesweeper Dateneingabe");
		Dimension d = Main.getMiddleCoordinates(400, 200);
		this.setBounds((int)d.getWidth(), (int)d.getHeight(), 400, 200);
		this.validate();
		this.setVisible(true);
	}
	
	public void setX(int x) {
		this.txtX.setText(""+x);
	}
	
	public void setY(int y) {
		this.txtY.setText(""+y);
	}
	
	public void setAnzMinen(int minen) {
		this.txtAnzPkt.setText(minen+"");
	}
	
	public boolean checkInputs() {
		
		int x = -1;
		int y = -1;
		int anz = -1;
		try {
			x = Integer.parseInt(txtX.getText());
		}
		catch(NumberFormatException e) {
			lblError.setText(errorWrapper1+"Fehler: Der Wert für die Spalten ist keine Zahl"+errorWrapper2);
			return false;
		}
		try {
			y = Integer.parseInt(txtY.getText());
		}
		catch(NumberFormatException e) {
			lblError.setText(errorWrapper1+"Fehler: Der Wert für die Zeilen ist keine Zahl"+errorWrapper2);
			return false;
		}
		try {
			anz = Integer.parseInt(txtAnzPkt.getText());
		}
		catch(NumberFormatException e) {
			lblError.setText(errorWrapper1+"Fehler: Der Wert für die Anzahl der Minen ist keine Zahl"+errorWrapper2);
			return false;
		}
		
		double percent = (x*y);
		percent = anz/percent;
		System.out.println("Checking inputs with X="+x+", Y="+y+", anzMinen="+anz+" and percent="+percent);
		if(x < Main.minX) {
			lblError.setText(errorWrapper1+"Fehler: Die Anzahl der Spalten ist zu klein (mind. 5)"+errorWrapper2);
			return false;
		}
		else if(x > Main.maxX) {
			lblError.setText(errorWrapper1+"Fehler: Die Anzahl der Spalten ist zu groß (max. 20)"+errorWrapper2);
			return false;
		}
		else if(y < Main.minY) {
			lblError.setText(errorWrapper1+"Fehler: Die Anzahl der Zeilen ist zu klein (mind. 5)"+errorWrapper2);
			return false;
		}
		else if(y > Main.maxY) {
			lblError.setText(errorWrapper1+"Fehler: Die Anzahl der Zeilen ist zu groß (max. 20)"+errorWrapper2);
			return false;
		}
		else if((percent < Main.minMinenPercent) || (percent > Main.maxMinenPercent)) {
			lblError.setText(errorWrapper1+"Fehler: Die Anzahl der Minen ist nicht gültig<br> (zwischen 0% und 70% der Felder)"+errorWrapper2);
			return false;
		}
		return true;
	}
	
	public void transferInputs() {
		int x = Integer.parseInt(txtX.getText());
		int y = Integer.parseInt(txtY.getText());
		int anz = Integer.parseInt(txtAnzPkt.getText());
		feld.setX(x);
		feld.setY(y);
		feld.setAnzMinen(anz);
		feld.setupGrid();
	}	
}
