package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
//clase rango
//clase tarifa
//clase descuento
//modificar la tarifa a los estratos
//modificar rangos (rango de inicio a fin )
//clase descuento

public class PropertyManager {
	private List<Property>registeredProperties;

	public PropertyManager() {
		registeredProperties = new ArrayList<Property>();
		createProperties();
	}

	public void createProperties() {
		// Crear y almacenar 6 propiedades residenciales (una para cada estrato)
		saveProperty(new Property("CN001", "Calle 101", PropertyType.RESIDENCIAL, 100, 50000000.0, 1));
		saveProperty(new Property("CN002", "Calle 102", PropertyType.RESIDENCIAL, 150, 75000000.0, 2));
		saveProperty(new Property("CN003", "Calle 103", PropertyType.RESIDENCIAL, 200, 10000000.0, 3));
		saveProperty(new Property("CN004", "Calle 104", PropertyType.RESIDENCIAL, 250, 15000000.0, 4));
		saveProperty(new Property("CN005", "Calle 105", PropertyType.RESIDENCIAL, 300, 20000000.0, 5));
		saveProperty(new Property("CN006", "Calle 106", PropertyType.RESIDENCIAL, 350, 25000000.0, 6));

		// Crear y almacenar 6 propiedades comerciales (una para cada rango de avalúo)
		saveProperty(new Property("CN007", "Avenida 201", PropertyType.COMERCIAL, 400, 5000000.0, 0)); // Hasta $10,000,000
		saveProperty(new Property("CN008", "Avenida 202", PropertyType.COMERCIAL, 450, 20000000.0, 0)); // De $10,000,001 a $30,000,000
		saveProperty(new Property("CN009", "Avenida 203", PropertyType.COMERCIAL, 500, 40000000.0, 0)); // De $30,000,001 a $50,000,000
		saveProperty(new Property("CN010", "Avenida 204", PropertyType.COMERCIAL, 550, 75000000.0, 0)); // De $50,000,001 a $100,000,000
		saveProperty(new Property("CN011", "Avenida 205", PropertyType.COMERCIAL, 600, 150000000.0, 0)); // De $100,000,001 a $200,000,000
		saveProperty(new Property("CN012", "Avenida 206", PropertyType.COMERCIAL, 650, 300000000.0, 0)); // Más de $200,000,000
	}

	public Property createNewResidentialProperty(String cadastralNumber,String address,PropertyType propertyType,int area,Double cadastralValue,int socioEconomicStatus) {
		Property createdProperty = new Property();
		createdProperty.setCadastralNumber(cadastralNumber);
		createdProperty.setAddress(address);
		createdProperty.setPropertyType(propertyType);
		createdProperty.setArea(area);
		createdProperty.setCadastralValue(cadastralValue);
		createdProperty.setSocioEconomicStatus(socioEconomicStatus);
		return createdProperty;
	}

	public Property createNewCommercialProperty(String cadastralNumber,String address,PropertyType propertyType,int area,Double cadastralValue) {
		Property createdProperty = new Property();
		createdProperty.setCadastralNumber(cadastralNumber);
		createdProperty.setAddress(address);
		createdProperty.setPropertyType(propertyType);
		createdProperty.setArea(area);
		createdProperty.setCadastralValue(cadastralValue);
		return createdProperty;
	}

	public void saveProperty(Property property) {
		registeredProperties.add(property);
	}


	public Property getPropertyFromDb(String cadastralNumber) {
		Property foundProperty = null;

		foundProperty = registeredProperties.stream().filter(property->property.getCadastalNumber().equals(cadastralNumber))
				.findFirst()
				.orElseThrow();
		return foundProperty;
	}

	public Property getPropertyFromAuxList(List<Property>auxPropertiesList,String cadastralNumber) {
		Property foundProperty = null;

		foundProperty = auxPropertiesList.stream().filter(property->property.getCadastalNumber().equals(cadastralNumber))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
		return foundProperty;
	}


	public String getPropertyListString() {
		StringBuilder listAsString = new StringBuilder();
		for(Property property:registeredProperties){
			listAsString.append(property.toString()).append("\n");
		}
		return listAsString.toString();
	}

	public List<Property>getPropertiesList(){
		return registeredProperties;
	}
}
