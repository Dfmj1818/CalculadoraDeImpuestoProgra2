package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConsultCenterPanel extends JPanel {
	private JLabel cadastralValue;
	private JTextField cadastralValueTextField;
	private JLabel propertyTypeLabel;
	private JTextField propertyTypeTextField;
	private JLabel addressLabel;
	private JTextField addressTextField;
	private JLabel propertyAreaLabel;
	private JTextField propertyAreaTextField;
	private JLabel consultTitleLabel;

	public ConsultCenterPanel() {
		this.setBackground(new Color(173,216,230));
		this.setLayout(new GridBagLayout());
		initComponents();
	}

	public void initComponents() {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);//margen entre componentes
		gbc.anchor = GridBagConstraints.CENTER;

		//grid x-columnas
		//grid y-filas
		gbc.gridx = 0;//[0,0]
		gbc.gridy = 0;
		gbc.gridwidth = 2;

		consultTitleLabel = new JLabel("Informacion del Predio");
		consultTitleLabel.setFont(new Font("Arial",Font.BOLD,20));
		add(consultTitleLabel,gbc);
		//------------------------------------------------------------------------------------		
		gbc.anchor = GridBagConstraints.WEST;//RESTABLECER EL ALINEADO DEL GBC
		gbc.gridwidth = 1;//reestablecer el espacio que ocupan los elementos en celdas
		gbc.gridx = 0;//[0,1]
		gbc.gridy = 1;

		cadastralValue = new JLabel("Avaluo Catastral");
		add(cadastralValue,gbc);
		//_------------------------------------------------------------------------------------------------		
		gbc.gridx = 1;//[1,1]
		cadastralValueTextField = new JTextField(20);
		cadastralValueTextField.setEditable(false);
		add(cadastralValueTextField,gbc);
		//----------------------------------------------------------------------------------------		
		gbc.gridx = 0;
		gbc.gridy = 2; //[0,2]

		propertyTypeLabel = new JLabel("Tipo de Uso");
		add(propertyTypeLabel,gbc);
		//------------------------------------------------------------------------------------------
		gbc.gridx = 1;//[1,2]
		propertyTypeTextField = new JTextField(20);
		propertyTypeTextField.setEditable(false);
		add(propertyTypeTextField,gbc);
		//-------------------------------------------------------------------------------------------
		gbc.gridx = 0;//[0,3]
		gbc.gridy = 3;

		addressLabel = new JLabel("Direccion");
		add(addressLabel,gbc);
		//-------------------------------------------------------------------------------------------
		gbc.gridx = 1;//[1,3]
		addressTextField = new JTextField(20);
		addressTextField.setEditable(false);
		add(addressTextField,gbc);
		//--------------------------------------------------------------------------------------------
		gbc.gridx = 0;
		gbc.gridy = 4;//[0,4]
		propertyAreaLabel = new JLabel("Area");
		add(propertyAreaLabel,gbc);
		//--------------------------------------------------------------------------------------------	
		gbc.gridx = 1; //[1,4]
		propertyAreaTextField = new JTextField(20);
		propertyAreaTextField.setEditable(false);
		add(propertyAreaTextField,gbc);
		//----------------------------------------------------------------------------------------------

	}

	public void setCadastralValueTxtField(String cadastralValue) {
		cadastralValueTextField.setText(cadastralValue);
	}

	public String getCadastralValue() {
		return cadastralValue.getText();
	}

	public void setPropertyTypeValue(String propertyType){
		propertyTypeTextField.setText(propertyType);
	}

	public String getPropertyTypeValue() {
		return propertyTypeTextField.getText();
	}


	public void setAddressText(String address) {
		this.addressTextField.setText(address);
	}

	public String getAddress() {
		return addressTextField.getText();
	}

	public void setAreaText(String area){
		propertyAreaTextField.setText(area);
	}

	public String getArea() {
		return propertyAreaTextField.getText();
	}
}
