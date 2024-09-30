package co.edu.uptc.presenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.text.DecimalFormat;

import co.edu.uptc.config.Configuration;
import co.edu.uptc.model.Discount;
import co.edu.uptc.model.Property;
import co.edu.uptc.model.PropertyManager;
import co.edu.uptc.model.PropertyType;
import co.edu.uptc.model.Range;
import co.edu.uptc.model.ResidentialPropertyRate;
import co.edu.uptc.model.TaxCalculator;
import co.edu.uptc.persistence.FileManager;
import co.edu.uptc.persistence.FilePaths;
import co.edu.uptc.view.IoManager;
import co.edu.uptc.view.MainFrame;
import co.edu.uptc.view.MainPanel;
import java.util.List;

public class Presenter {

	private TaxCalculator taxCalculator;
	private PropertyManager propertyManager;
	private MainFrame mainFrame;
	private MainPanel mainPanel;
	private DecimalFormat decimalFormatt;
	private IoManager ioManager;	
	private Configuration filesConfiguration;
	private FileManager fileManager;

	public Presenter() {
		filesConfiguration = new Configuration();
		taxCalculator = new TaxCalculator();
		ioManager = new IoManager();
		propertyManager = new PropertyManager();
		mainPanel = new MainPanel(this);
		mainFrame = new MainFrame(mainPanel);
		fileManager = new FileManager();
		loadCommercialRangesData();
		loadResidentialsPropertiesRates();
		loadProperties();
	}

	public void loadCommercialRangesData(){
		try {
			Range lowerRange = filesConfiguration.loadCommercialPropertiesRates("lowRange");
			Range lowerMiddleRange = filesConfiguration.loadCommercialPropertiesRates("lowerMiddleRange");
			Range middleRange = filesConfiguration.loadCommercialPropertiesRates("middleRange");
			Range upperMiddleRange = filesConfiguration.loadCommercialPropertiesRates("upperMiddleRange");
			Range upperRange = filesConfiguration.loadCommercialPropertiesRates("upperRange");

			taxCalculator.saveCommercialRate(lowerRange);
			taxCalculator.saveCommercialRate(lowerMiddleRange);
			taxCalculator.saveCommercialRate(middleRange);
			taxCalculator.saveCommercialRate(upperMiddleRange);
			taxCalculator.saveCommercialRate(upperRange);

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void loadResidentialsPropertiesRates() {

		try {
			ResidentialPropertyRate residentialRateOne =  filesConfiguration.loadResidentialPropertyRate("residentialPropertyRateSocioEconomicStatus1");
			ResidentialPropertyRate residentialRateTwo = filesConfiguration.loadResidentialPropertyRate("residentialPropertyRateSocioEconomicStatus2");
			ResidentialPropertyRate residentialRateThree = filesConfiguration.loadResidentialPropertyRate("residentialPropertyRateSocioEconomicStatus3");
			ResidentialPropertyRate residentialRateFour = filesConfiguration.loadResidentialPropertyRate("residentialPropertyRateSocioEconomicStatus4");
			ResidentialPropertyRate residentialRateFive = filesConfiguration.loadResidentialPropertyRate("residentialPropertyRateSocioEconomicStatus5");
			ResidentialPropertyRate residentialRateSix = filesConfiguration.loadResidentialPropertyRate("residentialPropertyRateSocioEconomicStatus6");

			taxCalculator.saveResidentialRate(residentialRateOne);
			taxCalculator.saveResidentialRate(residentialRateTwo);
			taxCalculator.saveResidentialRate(residentialRateThree);
			taxCalculator.saveResidentialRate(residentialRateFour);
			taxCalculator.saveResidentialRate(residentialRateFive);
			taxCalculator.saveResidentialRate(residentialRateSix);

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	public void loadProperties() {
		List<Property>propertiesList = null;

		try {
			propertiesList = propertyManager.getPropertiesList();
			fileManager.storePropertiesInFile(propertiesList,FilePaths.TEXTFILESROUTE);	
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}	


	public String createNewResidentialProperty(String cadastralNumber,String address,PropertyType propertyType,int area,Double cadastralValue,int socioEconomicStatus){
		Property createdResidentialProperty = propertyManager.createNewResidentialProperty(cadastralNumber, address, propertyType, area, cadastralValue, socioEconomicStatus);
		String message = " ";

		savePropertyInTextFile(createdResidentialProperty);
		message = "Predio creado Correctamente :-)";
		return message;
	}


	public String createNewComercialProperty(String cadastralNumber,String address,PropertyType propertyType,int area,Double cadastralValue){
		Property createdCommercialProperty = propertyManager.createNewCommercialProperty(cadastralNumber, address, propertyType, area, cadastralValue);
		String message = " ";

		savePropertyInTextFile(createdCommercialProperty);
		message = "Predio creado Correctamente :-)";
		return message;
	}

	public void savePropertyInTextFile(Property propertyString){
		
		try {
			List<Property>propertiesList = propertyManager.getPropertiesList();
			propertiesList.add(propertyString);
			fileManager.storePropertiesInFile(propertiesList,FilePaths.TEXTFILESROUTE);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	public String calculatePropertyTax(String digitedCadastralNumber) {
		Property property = getPropertyFromDb(digitedCadastralNumber);
		String temporalTaxValue = selectPropertyType(property);
		return temporalTaxValue;
	}

	public Property readProperty(String cadastralNumber){
		Property auxProperty = null;
		Property property = null;

		try {
			List<Property>auxPropertiesList = fileManager.readPropertiesFromFile(FilePaths.TEXTFILESROUTE);
			auxProperty = propertyManager.getPropertyFromAuxList(auxPropertiesList,cadastralNumber);
			propertyManager.saveProperty(property);
			property = getPropertyFromDb(cadastralNumber);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return property;
	}

	
	public Property getPropertyFromDb(String digitedCadastralNumber) {
		Property selectedProperty = null;

		selectedProperty = propertyManager.getPropertyFromDb(digitedCadastralNumber);
		return selectedProperty;
	}




	public String showPropertyInformation(Property selectedProperty) {
		String selectedPropertyAsString = " ";
		selectedPropertyAsString = selectedProperty.toString();
		return selectedPropertyAsString;
	}


	public String selectPropertyType(Property selectedProperty) {
		PropertyType propertyType = selectedProperty.getPropertyType();
		String taxValue = " ";

		switch(propertyType){
		case RESIDENCIAL:
			taxValue = calculateResidentialTax(selectedProperty);
			break;

		case COMERCIAL:
			taxValue = calculateCommercialTax(selectedProperty);
			break;

		default:
			ioManager.printMessage("Tipo de Propiedad no Reconocido");            	
			break;
		}
		return taxValue;
	}

	public String calculateResidentialTax(Property property) {
		int socioEconomicStatus = 0;
		ResidentialPropertyRate residentialPropertyRate = null;
		Double residentialTax = 0.0;
		Double cadastralValue = 0.0;
		Double temporaryResidentialPropertyTax = 0.0; 
		String formattedResidentialTax = " ";

		cadastralValue = property.getCadastralValue();
		socioEconomicStatus = property.getSocioEconomicStatus();
		residentialPropertyRate = taxCalculator.getResidentialPropertyRate(socioEconomicStatus);
		residentialTax = residentialPropertyRate.getResidentialTax(); 
		temporaryResidentialPropertyTax =  taxCalculator.calculateResidentialTax(cadastralValue,residentialTax);
		formattedResidentialTax = ioManager.formatDouble(temporaryResidentialPropertyTax);
		return formattedResidentialTax;
	}

	public String calculateCommercialTax(Property property) {
		Double cadastralValue = 0.0;
		Double commercialTax = 0.0;
		Double propertyCommercialRate = 0.0;
		Double temporaryCommercialTax = 0.0;
		String formattedTemporaryCommercialTax = " ";

		cadastralValue = property.getCadastralValue();
		propertyCommercialRate = taxCalculator.getCommercialApplicableRate(cadastralValue);
		temporaryCommercialTax = taxCalculator.calculateCommercialTax(cadastralValue,propertyCommercialRate);
		formattedTemporaryCommercialTax = ioManager.formatDouble(temporaryCommercialTax);
		return formattedTemporaryCommercialTax;
	}

	public String applyEarlyPaymentDiscount(Property property){
		Double finalTaxValue = 0.0; // Ejemplo de descuento
		Discount discountObject = taxCalculator.getEarlyPaymentDiscount(property);
		Double discountValue = discountObject.getDiscountValue();
		String formattedFinalTxValue = " ";

		finalTaxValue = taxCalculator.applyEarlyDiscount(property,discountValue);
		formattedFinalTxValue = ioManager.formatDouble(finalTaxValue);
		return formattedFinalTxValue;
	}

	public String applyExemptDiscount(Property property){
		Double finalTaxValue = 0.0;
		Discount discountObject = taxCalculator.getExcentPaymentDiscount(property); 
		Double discountValue = discountObject.getDiscountValue();
		String formattedTaxValue = " ";

		finalTaxValue = taxCalculator.applyExemptDiscount(property, discountValue);
		formattedTaxValue = ioManager.formatDouble(finalTaxValue);
		return formattedTaxValue;
	}


	public String formatDouble(Double doubleToFormat){
		String formattedDouble = ioManager.formatDouble(doubleToFormat);
		return formattedDouble;
	}
	


	public MainPanel getMainPanel() {
		return mainPanel;
	}


	public static void main(String[]args){
		new Presenter();
	}
}
