package co.edu.uptc.view;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import co.edu.uptc.model.ButtonConstants;
import co.edu.uptc.model.Property;
import co.edu.uptc.presenter.Presenter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatePanel extends JPanel {
    private Presenter presenter;
    private CalculateNorthPanel calculateNorthPanel;
    private CalculateSouthPanel calculateSouthPanel;
    private CalculateCenterPanel calculateCenterPanel;
    
	public CalculatePanel(Presenter presenter) {
		this.presenter = presenter;
		this.setLayout(new BorderLayout());
		addPanels();
	    	
	}
	
	public void addPanels() {
	    calculateNorthPanel = new CalculateNorthPanel(presenter);
	    addListenerToNorthPanelButton();
	    calculateSouthPanel = new CalculateSouthPanel(presenter);
	    calculateCenterPanel = new CalculateCenterPanel(presenter);
	    add(calculateNorthPanel,BorderLayout.NORTH);
	    add(calculateSouthPanel,BorderLayout.SOUTH);
	    add(calculateCenterPanel,BorderLayout.CENTER);
	}
	
	public void addListenerToNorthPanelButton() {
	  	calculateNorthPanel.getCalculateTaxButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temporalTaxValue = presenter.calculatePropertyTax(calculateNorthPanel.getCadastralCodeText());
				calculateSouthPanel.setTaxResultText(temporalTaxValue);
			}
		});
	}
	
	public void addListeners() {
		calculateCenterPanel.getRButton1().setActionCommand(ButtonConstants.EARLYDISCOUNT);
		calculateCenterPanel.getRButton1().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   
				
			}
		});
		
		calculateCenterPanel.getRButton2().setActionCommand(ButtonConstants.EXCENTDISCOOUNT);
		calculateCenterPanel.getRButton2().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}
}
	
	
	
	
	
	

