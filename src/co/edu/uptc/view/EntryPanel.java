package co.edu.uptc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.colorchooser.ColorSelectionModel;

public class EntryPanel extends JPanel {
    
	private JLabel titleLabel;
	
	public EntryPanel() {
	     initComponents();
	     createPanelTitle();
	}
	
	public void initComponents() {
		this.setBackground(new Color(173,216,230));
		this.setLayout(new BorderLayout());
	}
	
	public void createPanelTitle() {
		titleLabel = new JLabel("Bienvenido a la app de calculo de Impuestos Prediales");
		titleLabel.setFont(new Font("Arial",Font.BOLD,24));
		titleLabel.setForeground(Color.BLACK);
		this.add(titleLabel,BorderLayout.NORTH);
	}

	
	
}
