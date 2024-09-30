package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.model.ButtonConstants;
import co.edu.uptc.model.PropertyType;
import co.edu.uptc.presenter.Presenter;

public class CreatePropertyPanel extends JPanel {

	private Presenter presenter;
	private JLabel panelTitleLabel;
	private JLabel cadastralNumberLabel;
	private JTextField cadastralNumberTxtField;
	private JLabel addressLabel;
	private JTextField addressTextField;
	private JComboBox<String>propertyTypeOptions;
	private JLabel propertyAreaLabel;
	private JTextField areaTxtField;
	private JLabel cadastraValueLabel;
	private JTextField cadastralValueTxtField;
	private JLabel socioEconomicStatusLabel;
	private JTextField socioEconomicStatusTxtField;
	private JButton createPropertyButton;
	private IoManager ioManager;
	private PropertyType propertyType;

	public CreatePropertyPanel(Presenter presenter) {
		this.presenter = presenter;
		ioManager = new IoManager();
		this.setBackground(new Color(173,216,230));
		setLayout(new GridBagLayout());
		initComponents();

	}

	public void initComponents() {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);  // Margen entre los componentes
		gbc.anchor = GridBagConstraints.CENTER; // Alineación centrada
		// Fila 1: Número catastral
		//grid x-columnas
		//grid y-filas
		gbc.gridx = 0;//[0,0]
		gbc.gridy = 0;
		gbc.gridwidth = 2;

		panelTitleLabel = new JLabel("Formulario para creacion del predio");
		panelTitleLabel.setFont(new Font("Arial",Font.BOLD,20));
		add(panelTitleLabel,gbc);
		//--------------------------------------------------------------------------------------------		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 1;
		gbc.gridx = 0; //[0,1] Label Numero Catastral 
		gbc.gridy = 1;
		cadastralNumberLabel = new JLabel("Numero Catastral");
		add(cadastralNumberLabel, gbc);
		//-------------------------------------
		gbc.gridx = 1;// [1,1] Jtextfield
		cadastralNumberTxtField = new JTextField(10);
		add(cadastralNumberTxtField, gbc);
		//--------------------------------------------------------------------------------------
		// Fila 2: Dirección
		gbc.gridx = 0; //[0,2] Direccion
		gbc.gridy = 2;
		addressLabel = new JLabel("Direccion");
		add(addressLabel, gbc);
		//----------------------------------------------------------------------------------------
		gbc.gridx = 1;//[1,2]JtextField
		addressTextField = new JTextField(20);
		add(addressTextField, gbc);
		//----------------------------------------------------------------------------------------
		// Fila 3: Área
		gbc.gridx = 0;  //[0,3]
		gbc.gridy = 3;
		propertyAreaLabel = new JLabel("Area");
		add(propertyAreaLabel, gbc);
		//----------------------------------------------------------------------------------------
		gbc.gridx = 1;//[1,3]
		areaTxtField = new JTextField(10);
		add(areaTxtField, gbc);
		//----------------------------------------------------------------------------------------
		// Fila 4: Tipo
		gbc.gridx = 0;//[0,4]
		gbc.gridy = 4;
		add(new JLabel("Tipo:"), gbc);
		//------------------------------------------------------------------------------------------
		gbc.gridx = 1;//[1,4]
		propertyTypeOptions = new JComboBox<>(new String[]{"Residencial","Comercial"});
		propertyTypeOptions.setSelectedIndex(0);
		addComboBoxListener();
		add(propertyTypeOptions, gbc);

		// Fila 5: Valor
		gbc.gridx = 0;//[0,6] 
		gbc.gridy = 6;
		cadastraValueLabel = new JLabel("Valor");
		add(cadastraValueLabel, gbc);
		//------------------------------------------------------------------------------------------
		gbc.gridx = 1;//[1,6]
		cadastralValueTxtField = new JTextField(10);
		add(cadastralValueTxtField, gbc);

		// Fila 6: Estrato
		gbc.gridx = 0; //[0,5]
		gbc.gridy = 5;
		socioEconomicStatusLabel = new JLabel("Estrato");
		add(socioEconomicStatusLabel, gbc);
		//-----------------------------------------------------------------------------------------
		gbc.gridx = 1;//[1,5]
		socioEconomicStatusTxtField = new JTextField(10);
		add(socioEconomicStatusTxtField, gbc);
		//------------------------------------------------------------------------------------------	

		//------------------------------------------------------------------------------
		// Fila 7: Botón Crear
		gbc.gridx = 1;//[1,7]
		gbc.gridy = 7;
		createPropertyButton = new JButton("Crear Predio");
		addListenerToCreatePropertyBtn();
		add(createPropertyButton, gbc);
		//--------------------------------------------------------------------------------
	}


	public void addComboBoxListener() {
		propertyTypeOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedPropertyType = (String) propertyTypeOptions.getSelectedItem();

				if(selectedPropertyType.equals("Residencial")){
					propertyType = PropertyType.RESIDENCIAL;   
					socioEconomicStatusLabel.setVisible(true);
					socioEconomicStatusTxtField.setVisible(true);
				}
				else if(selectedPropertyType.equals("Comercial")){
					propertyType = PropertyType.COMERCIAL;
					socioEconomicStatusLabel.setVisible(false);
					socioEconomicStatusTxtField.setVisible(false);
				}
			} 		
			
		});
	}

	public void addListenerToCreatePropertyBtn() {
		createPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener los valores de los campos
				String cadastralNumber = cadastralNumberTxtField.getText();
				String address = addressTextField.getText();
				int area = getAreaValue();
				double cadastralValue = getCadastralValue();
				String message = " ";

				if (cadastralNumber.isEmpty() || address.isEmpty() || propertyType == null || getAreaValue() == 0 || getCadastralValue() == 0) {
					ioManager.printErrorMessage("Por favor complete todos los campos.");
				} 

				else if (propertyType == PropertyType.COMERCIAL) {
					message = presenter.createNewComercialProperty(cadastralNumber, address, propertyType, getAreaValue(), getCadastralValue());
					ioManager.printOkMessage(message);	
				} 
				else if (propertyType == PropertyType.RESIDENCIAL) {
					if (socioEconomicStatusTxtField.getText().isEmpty()) {
						ioManager.printErrorMessage("Por favor ingrese el estrato socioeconómico.");

					} else {
						message = presenter.createNewResidentialProperty(cadastralNumber, address, propertyType, getAreaValue(), getCadastralValue(), getSocioEconomicStatusValue());
						ioManager.printOkMessage(message);
					}
				}

			}
		});
	}


	public String getCadastralNumber() {
		return cadastralNumberTxtField.getText();
	}

	public String getAddress() {
		return addressTextField.getText();
	}

	public String getArea() {
		return areaTxtField.getText();
	}

	public String getCadastralValueText() {
		return cadastralValueTxtField.getText();
	}

	public Double getCadastralValue() {
		String cadastralValueText = cadastralValueTxtField.getText();
		if (cadastralValueText.isEmpty()) {
			ioManager.printErrorMessage("El campo Valor catastral no puede estar vacío.");
			return 0.0;  // Devuelve un valor predeterminado en caso de que esté vacío
		}
		Double cadastralValue = ioManager.castStringToDouble(cadastralValueText);
		return cadastralValue;
	}

	public String getSocioEconomicStatusText(){
		return socioEconomicStatusTxtField.getText();
	}

	public int getSocioEconomicStatusValue() {
		String socioEconomicStatusText = socioEconomicStatusTxtField.getText();
		if (socioEconomicStatusText.isEmpty()) {
			ioManager.printErrorMessage("El campo Estrato no puede estar vacío.");
			return 0; // O puedes manejar el error como sea adecuado
		}
		int socioEconomicStatusValue = ioManager.castStringToInt(socioEconomicStatusText);
		return socioEconomicStatusValue;
	}


	public int getAreaValue(){
		String areaTxt = areaTxtField.getText();
		if (areaTxt.isEmpty()) {
			ioManager.printErrorMessage("El campo Área no puede estar vacío.");
			return 0; // Devuelve un valor predeterminado en caso de que esté vacío
		}
		else {
			int areaValue = ioManager.castStringToInt(areaTxt);
			return areaValue;
		}
	}


}
