package co.edu.uptc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.model.ButtonConstants;
import co.edu.uptc.model.Property;
import co.edu.uptc.presenter.Presenter;

public class ConsultNorthPanel extends JPanel {

	private JLabel cadastralNumberLabel;
	private JTextField cadastralNumberTxtField;
	private JButton searchButton;
	private JLabel panelTitle;
    private Presenter presenter;
    private IoManager ioManager;
    
	public ConsultNorthPanel(Presenter presenter){
		this.ioManager = new IoManager();
		this.presenter = presenter;
		this.setBackground(new Color(173,216,230));
		this.setLayout(new FlowLayout());
		initComponents();
		
	}


	public void initComponents() {
		cadastralNumberLabel = new JLabel("Numero Catastral");
		cadastralNumberTxtField = new JTextField(20);
		searchButton = new JButton("Buscar");
		
		searchButton.setFont(new Font("Arial",Font.BOLD,12));
		add(cadastralNumberLabel);
		add(cadastralNumberTxtField);
		add(searchButton);
	}
	

	
	public String getCadastralNumber(){
		String cadastralNumber = cadastralNumberTxtField.getText();
		if(cadastralNumber.isEmpty()){
			ioManager.printErrorMessage("Por favor complete el campo de numero Catastral");
		}
       return cadastralNumber;
	}
	
	public JButton getSearchButton() {
		return searchButton;
	}


}
