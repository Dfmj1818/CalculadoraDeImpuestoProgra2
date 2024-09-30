package co.edu.uptc.model;

public class ResidentialPropertyRate {

	private int socioEconomicStatus;
	private Double residentialTax;
	
	
	
	public ResidentialPropertyRate() {
		
	}
	
	public void setSocioEconomicStatus(int socioEconomicStatus) {
		this.socioEconomicStatus = socioEconomicStatus;
	}
	
	public int getSocioEconomicStatus() {
		return socioEconomicStatus;
	}
	
	public void setResidentialTax(Double residentialTax) {
		this.residentialTax = residentialTax;
	}
	
	public Double getResidentialTax() {
		return residentialTax;
	}
}
