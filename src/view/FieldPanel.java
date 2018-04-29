package view;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ein einzelnes Minenfeld
 * @author David Meyer
 *
 */
public class FieldPanel extends JPanel {

	int value;
	JLabel label;
	
	public static Icon MineIcon;
	
	public FieldPanel() {
		label = new JLabel();
		this.add(label);
	}
	
	public boolean isMine() {
		return getValue() < 0;
	}
	
	public void setMine() {
		this.setValue(-1);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void incrementValue() {
		if(getValue() < 8) {
			setValue(getValue()+1);
		}
		else {
			System.out.println("Fehler: Wollte Value höher als 8 setzen");
		}
	}
	
	public void updateValue() {
		if(isMine()) {
			this.label.setText("");
			this.label.setIcon(MineIcon);
			this.setBackground(new Color(255, 255, 255));
		}
		else {
			this.label.setText("" + this.value);
			if(this.value > 0) {
				double percent = this.value/8.0;
				this.setBackground(new Color((int)(255.0*percent), (int)(255.0*(1-percent)), 0));
			}
			else {
				this.setBackground(new Color(255, 255, 255));
			}
		}
	}
}