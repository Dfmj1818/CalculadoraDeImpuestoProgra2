package co.edu.uptc.config;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import co.edu.uptc.model.Range;
import co.edu.uptc.model.ResidentialPropertyRate;

public class Configuration {

	private Properties properties;

	public Configuration() {
		this.properties = new Properties();
		
	}

	public Range loadCommercialPropertiesRates(String rangeName) throws FileNotFoundException, IOException{
		properties.load(new FileReader("resources/configuration.properties"));
		String rangeStrings = properties.getProperty(rangeName);
		String [] rangeValues = rangeStrings.split("-");
		
		int rangeId = Integer.parseInt(rangeValues[0]);
		Double minimumValue = Double.parseDouble(rangeValues[1]);
		Double maximumValue = Double.parseDouble(rangeValues[2]);
		Double taxValue = Double.parseDouble(rangeValues[3]);
		
		Range rangeObject = new Range(); 
		rangeObject.setId(rangeId);
		rangeObject.setMinimumValue(minimumValue);
		rangeObject.setMaximumValue(maximumValue);
		rangeObject.setTax(taxValue);
		
		
		return rangeObject;
	}

	public ResidentialPropertyRate loadResidentialPropertyRate(String residentialRateName)throws FileNotFoundException,IOException {
		properties.load(new FileReader("resources/configuration.properties"));
		String residentialPropertyRatesString = properties.getProperty(residentialRateName);
		String [] residentialPropertyRates = residentialPropertyRatesString.split("-");
		
		int socioEconomicStatus = Integer.parseInt(residentialPropertyRates[0]);
		Double residentialTax = Double.parseDouble(residentialPropertyRates[1]);
		
		ResidentialPropertyRate residentialPropertyRate = new ResidentialPropertyRate();
		residentialPropertyRate.setSocioEconomicStatus(socioEconomicStatus);
		residentialPropertyRate.setResidentialTax(residentialTax);
		
		return residentialPropertyRate;
	}

}
