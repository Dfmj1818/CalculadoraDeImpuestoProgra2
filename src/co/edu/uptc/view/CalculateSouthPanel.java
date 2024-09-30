package co.edu.uptc.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.presenter.Presenter;

public class CalculateSouthPanel extends JPanel{
    private JLabel calculateLabel;
	private JTextField taxResultTxtField;
    private Presenter presenter;
    
	public CalculateSouthPanel(Presenter presenter) {
		this.presenter = presenter;
		this.setBackground(new Color(176,216,230));
		this.setLayout(new FlowLayout());
		initComponents();
	}
	
	public void initComponents() {
		calculateLabel = new JLabel("Impuesto Final");
		taxResultTxtField = new JTextField(20);
		add(calculateLabel);
		add(taxResultTxtField);
	}
	
	public void setTaxResultText(String taxResult){
		taxResultTxtField.setText(taxResult);
	}
	
}
