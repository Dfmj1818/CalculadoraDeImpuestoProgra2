package co.edu.uptc.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.presenter.Presenter;

public class CalculateNorthPanel extends JPanel{

	private JLabel cadastralNumber;
	private JTextField cadastralCodeTextField;
	private JButton calculateTaxButton;
	private Presenter presenter;

	
	public CalculateNorthPanel(Presenter presenter) {
		this.presenter = presenter;
		this.setBackground(new Color(173,216,230));
		this.setLayout(new FlowLayout());
		initComponents();
	}
	
	public void initComponents() {
		cadastralNumber = new JLabel("Codigo Catastral");
		cadastralCodeTextField = new JTextField(20);
	    calculateTaxButton = new JButton("Calcular Impuesto");
	    calculateTaxButton.setFont(new Font("Arial",Font.BOLD,12));
		add(cadastralNumber);
		add(cadastralCodeTextField);
		add(calculateTaxButton);
	}
	
	public JButton getCalculateTaxButton() {
		return calculateTaxButton;
	}

	
	public String getCadastralCodeText() {
		return cadastralCodeTextField.getText();
	}
	
}
