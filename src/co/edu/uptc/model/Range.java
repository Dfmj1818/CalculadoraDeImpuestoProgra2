package co.edu.uptc.model;

public class Range {

	private int id;
	private Double minimumValue;
	private Double maximumValue;
	private Double tax;

	public Range(){

	}

	public void setId(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setMinimumValue(Double minimumValue) {
		this.minimumValue = minimumValue;
	}

	public Double getMinimumValue() {
		return minimumValue;
	}

	public void setMaximumValue(Double maximumValue) {
		this.maximumValue = maximumValue;
	}

	public Double getMaximumValue() {
		return maximumValue;
	}

	public void setTax(Double tax){
		this.tax = tax;
	}

	public Double getTax() {
		return tax;
	}
	
	@Override
	public String toString() {
	    return "Range [min=" + minimumValue + ", max=" + maximumValue + ", tax=" + tax + "]";
	}

}
