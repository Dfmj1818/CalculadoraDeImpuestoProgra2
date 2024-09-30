package co.edu.uptc.model;

public class Property {
	private String cadastralNumber;
	private String address;
	private PropertyType propertyType;
	private int area;
	private double cadastralValue;
	private int socioEconomicStatus;
	private Double propertyTax;


	public Property(String cadastralNumber,String address,PropertyType propertyType,int area,double cadastralValue,int socioEconomicStatus) {
		this.cadastralNumber = cadastralNumber;
		this.address = address;
		this.propertyType = propertyType;
		this.area = area;
		this.cadastralValue = cadastralValue;
		this.socioEconomicStatus = socioEconomicStatus;
	}
	
	public Property(String cadastralNumber,String address,PropertyType propertyType,int area,double cadastralValue) {
		this.cadastralNumber = cadastralNumber;
		this.address = address;
		this.propertyType = propertyType;
		this.area = area;
		this.cadastralValue = cadastralValue;
	}

	public Property() {

	}
	

	public void setCadastralNumber(String cadastralNumber) {
		this.cadastralNumber = cadastralNumber;
	}

	public String getCadastalNumber() {
		return cadastralNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getArea() {
		return area;
	}

	public void setCadastralValue(double cadastralValue) {
		this.cadastralValue = cadastralValue;
	}

	public double getCadastralValue() {
		return cadastralValue;
	}
	
	public String setCadastralValueFormat(Double cadastralValue){
		return String.format("%,.2f COP", cadastralValue);
	}

	public void setSocioEconomicStatus(int setSocioEconomicStatus) {
		this.socioEconomicStatus = setSocioEconomicStatus;
	}

	public int getSocioEconomicStatus() {
		return socioEconomicStatus;
	}

	public void setPropertyTax(Double propertyTax) {
		this.propertyTax = propertyTax;
	}

	public Double getPropertyTax() {
		return propertyTax;
	}
	
	public void setPropertyType(PropertyType propertyType){
		this.propertyType = propertyType;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}
 
	
	
	@Override
	public String toString() {
	    StringBuilder propertyString = new StringBuilder();
	    // Concatenamos los atributos con comas como delimitadores
	    propertyString.append(getCadastalNumber()).append(",")
	        .append(getAddress()).append(",")
	        .append(getPropertyType().toString()).append(",")
	        .append(getArea()).append(",")
	        .append(getCadastralValue());

	    // Si es de tipo RESIDENCIAL, añadimos el estrato socioeconómico
	    if (getPropertyType().equals(PropertyType.RESIDENCIAL)) {
	        propertyString.append(",").append(getSocioEconomicStatus());
	    }

	    return propertyString.toString();
	}


}
