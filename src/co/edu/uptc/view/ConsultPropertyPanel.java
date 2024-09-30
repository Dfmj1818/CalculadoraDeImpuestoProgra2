package co.edu.uptc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.model.ButtonConstants;
import co.edu.uptc.model.Property;
import co.edu.uptc.model.PropertyType;
import co.edu.uptc.presenter.Presenter;

public class ConsultPropertyPanel extends JPanel{
	private Presenter presenter;
	private JLabel cadastralNumberLabel;
	private JLabel propertyTypeLabel;
	private JLabel propertyArea;
	private JLabel cadastralValue;
	private JTextField cadastralNumberTxtField;
	private ConsultNorthPanel consultNorthPanel;
	private ConsultCenterPanel consultCenterPanel;
	
	public ConsultPropertyPanel(Presenter presenter) {
		this.presenter = presenter;
		this.setLayout(new BorderLayout());
		addPanels();
		addListenerToSearchButton();
	}

	public void addPanels() {
		consultNorthPanel = new ConsultNorthPanel(presenter);
		add(consultNorthPanel,BorderLayout.NORTH);
		consultCenterPanel = new ConsultCenterPanel();
		add(consultCenterPanel,BorderLayout.CENTER);
	}
    
	public void addListenerToSearchButton() {
		  consultNorthPanel.getSearchButton().setActionCommand(ButtonConstants.CONSULT);
		  consultNorthPanel.getSearchButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Property foundProperty = presenter.readProperty(consultNorthPanel.getCadastralNumber());
				String address = foundProperty.getAddress();
				int area = foundProperty.getArea();
				
				Double cadastralValue = foundProperty.getCadastralValue();
				String cadastralValueText = foundProperty.setCadastralValueFormat(cadastralValue);
				String areaText = String.valueOf(area);
				String propertyTypeText =  foundProperty.getPropertyType().toString();
				
			    consultCenterPanel.setCadastralValueTxtField(cadastralValueText);
			    consultCenterPanel.setAreaText(areaText);
			    consultCenterPanel.setPropertyTypeValue(propertyTypeText);
			    consultCenterPanel.setAddressText(address);
			}
		});
			
		}

	
	

}
