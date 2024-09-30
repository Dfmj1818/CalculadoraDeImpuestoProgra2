package co.edu.uptc.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.edu.uptc.model.Property;
import co.edu.uptc.model.PropertyType;

public class FileManager {


	public FileManager() {

	}


	public void writeFile(String filePath,String objectString)throws FileNotFoundException,IOException {
		FileWriter fileWriter = new FileWriter(filePath,true);
		fileWriter.write(objectString+"\n");
		fileWriter.close();
	}

	public void storePropertiesInFile(List<Property>propertiesList,String filePath) throws FileNotFoundException,IOException {
		
		File directory = new File(filePath).getParentFile();
		Set<String> existingCadastralNumbers = new HashSet<>();
        List<Property>existingPropertiesInFile = readPropertiesFromFile(filePath);
        
        
		if(directory != null && !directory.exists()){
			directory.mkdirs();// Crea la carpeta si no existe
		}	

		for(Property property:existingPropertiesInFile){
			String cadastralNumber = " ";
			cadastralNumber = property.getCadastalNumber();
			existingCadastralNumbers.add(cadastralNumber);
		}

		for(Property property:propertiesList){
			if(!existingCadastralNumbers.contains(property.getCadastalNumber())){
				String propertyString = property.toString();
				writeFile(filePath,propertyString); // Llamamos a writeFile para escribir cada propiedad
			}
		}
	}


	public List<Property> readPropertiesFromFile(String filePath) throws FileNotFoundException, IOException {

		List<Property> propertiesList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;

		while ((line = reader.readLine()) != null) {
			// Evitar procesar líneas vacías o mal formateadas
			if (line.trim().isEmpty()) {
				continue; // Salta líneas vacías
			}

			// Dividir la línea por comas
			String[] propertyArray = line.split(",");

			// Validar si es residencial o comercial
			if (propertyArray.length == 6) { // Caso para propiedades residenciales
				String cadastralNumber = propertyArray[0].trim();
				String address = propertyArray[1].trim();
				PropertyType propertyType = PropertyType.valueOf(propertyArray[2].trim());
				int area = Integer.parseInt(propertyArray[3].trim());
				double cadastralValue = Double.parseDouble(propertyArray[4].trim());
				int socioEconomicStatus = Integer.parseInt(propertyArray[5].trim());

				Property property = new Property(cadastralNumber, address, propertyType, area, cadastralValue, socioEconomicStatus);
				propertiesList.add(property);

			} else if (propertyArray.length == 5) { // Caso para propiedades comerciales
				String cadastralNumber = propertyArray[0].trim();
				String address = propertyArray[1].trim();
				PropertyType propertyType = PropertyType.valueOf(propertyArray[2].trim());
				int area = Integer.parseInt(propertyArray[3].trim());
				double cadastralValue = Double.parseDouble(propertyArray[4].trim());

				Property property = new Property(cadastralNumber, address, propertyType, area, cadastralValue);
				propertiesList.add(property);

			} else {
				System.out.println("Línea malformada: " + line);
			}
		}

		reader.close();
		return propertiesList;
	}


}
