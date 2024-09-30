package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculator {
	private List<Range>commercialRates;
	private List<ResidentialPropertyRate>residentialRates;
	private List<Discount>discounts;

	public TaxCalculator() {
		residentialRates = new ArrayList<ResidentialPropertyRate>();
		commercialRates = new ArrayList<Range>();
		discounts = new ArrayList<Discount>();
		setDiscounts();
	}

	/*public void setResidentialRates() {
		//Asignacion de Tarifas de los predios residenciales
		ResidentialPropertyRate residentialRateStatusOne = new ResidentialPropertyRate();
		residentialRateStatusOne.setSocioEconomicStatus(1);
		residentialRateStatusOne.setResidentialTax(0.004);

		ResidentialPropertyRate residentialRateStatusTwo = new ResidentialPropertyRate();
		residentialRateStatusTwo.setSocioEconomicStatus(2);
		residentialRateStatusTwo.setResidentialTax(0.0055);

		ResidentialPropertyRate residentialRateStatusThree = new ResidentialPropertyRate();
		residentialRateStatusThree.setSocioEconomicStatus(3);
		residentialRateStatusThree.setResidentialTax(0.00675);

		ResidentialPropertyRate residentialRateStatusFour = new ResidentialPropertyRate();
		residentialRateStatusFour.setSocioEconomicStatus(4);
		residentialRateStatusFour.setResidentialTax(0.00775);

		ResidentialPropertyRate residentialRateStatusFive = new ResidentialPropertyRate();
		residentialRateStatusFive.setSocioEconomicStatus(5);
		residentialRateStatusFive.setResidentialTax(0.0085);

		ResidentialPropertyRate residentialRateStatusSix = new ResidentialPropertyRate();
		residentialRateStatusSix.setSocioEconomicStatus(6);
		residentialRateStatusSix.setResidentialTax(0.0105);

		residentialRates.add(residentialRateStatusOne);
		residentialRates.add(residentialRateStatusTwo);
		residentialRates.add(residentialRateStatusThree);
		residentialRates.add(residentialRateStatusFour);
		residentialRates.add(residentialRateStatusFive);
		residentialRates.add(residentialRateStatusSix);

	}*/

	public void saveResidentialRate(ResidentialPropertyRate residentialPropertyRate){
		residentialRates.add(residentialPropertyRate);
	}

	/*public void setCommercialRates(Range range){
		// Rango inferior: Hasta $10,000,000
		Range lowerRange = new Range();
		lowerRange.setMinimumValue(0.0);            // Minimo valor
		lowerRange.setMaximumValue(10000000.0);     // Maximo valor
		lowerRange.setTax(0.005);               // 5x1000

		// Rango intermedio bajo: De $10,000,001 a $30,000,000
		Range lowerMiddleRange = new Range();
		lowerMiddleRange.setMinimumValue(10000001.0);
		lowerMiddleRange.setMaximumValue(30000001.0);
		lowerMiddleRange.setTax(0.0075);        // 7.5x1000

		// Rango medio: De $30,000,001 a $50,000,000
		Range middleRange = new Range();
		middleRange.setMinimumValue(30000001.0);
		middleRange.setMaximumValue(50000000.0);
		middleRange.setTax(0.0105);             // 10.5x1000

		// Rango intermedio alto: De $50,000,001 a $100,000,000
		Range upperMiddleRange = new Range();
		upperMiddleRange.setMinimumValue(50000001.0);
		upperMiddleRange.setMaximumValue(100000000.0);
		upperMiddleRange.setTax(0.0115);        // 11.5x1000

		// Rango superior: De $100,000,001 a $200,000,000
		Range upperRange = new Range();
		upperRange.setMinimumValue(100000001.0);
		upperRange.setMaximumValue(200000000.0);
		upperRange.setTax(0.012);               // 12x1000

		// Rango más alto: Más de $200,000,000
		Range highestRange = new Range();
		highestRange.setMinimumValue(200000001.0);
		highestRange.setMaximumValue(Double.MAX_VALUE); // Sin límite superior definido
		highestRange.setTax(0.015);
		// 15x1000
		commercialRates.add(lowerRange);
		commercialRates.add(lowerMiddleRange);
		commercialRates.add(middleRange);
		commercialRates.add(upperMiddleRange);
		commercialRates.add(upperRange);
		commercialRates.add(highestRange);
	}*/

	public void saveCommercialRate(Range range){
		commercialRates.add(range);
	}

	public void setDiscounts(){
		Discount earlyPaymentDiscount = new Discount();
		earlyPaymentDiscount.setDiscountValue(0.15);
		earlyPaymentDiscount.setDiscountType(DiscountType.PRONTOPAGO);

		Discount exemptDiscount =  new Discount();
		exemptDiscount.setDiscountValue(0.80);
		exemptDiscount.setDiscountType(DiscountType.EXCENTO);

		discounts.add(earlyPaymentDiscount);
		discounts.add(exemptDiscount);
	}

	public Double getCommercialApplicableRate(Double cadastralvalue) {
		return commercialRates.stream()
				.filter(range -> cadastralvalue >= range.getMinimumValue() && cadastralvalue <= range.getMaximumValue())
				.map(Range::getTax)
				.findFirst()
				.orElse(-1.0); 
	}


	public ResidentialPropertyRate getResidentialPropertyRate(int socioEconomicStatus){
		return residentialRates.stream().filter(residentialPropertyRate->residentialPropertyRate.getSocioEconomicStatus()==socioEconomicStatus)
				.findFirst()
				.orElseThrow();
	}


	public Double calculateCommercialTax(Double cadastralValue,Double applicableRate){
		Double commercialTax = cadastralValue*applicableRate;
		return commercialTax;
	}

	public Double calculateResidentialTax(Double cadastralValue,Double propertyResidentialTax){
		Double residentialTax = cadastralValue *propertyResidentialTax ;
		return residentialTax;
	}
	//Periodo de Pronto pago
	public Discount getEarlyPaymentDiscount(Property property) {
		return discounts.stream().filter(discount->discount.getDiscountType().equals(DiscountType.PRONTOPAGO))
				.findFirst()
				.orElseThrow();

	}

	public Discount getExcentPaymentDiscount(Property property ){
		return discounts.stream().filter(discount->discount.getDiscountType().equals(DiscountType.EXCENTO))
				.findFirst()
				.orElseThrow();
	}

	//Predio excento o excluido
	public Double applyExemptDiscount(Property property,Double exemptDiscount) { // Ejemplo de descuento
		Double finalTaxValue = property.getCadastralValue()*exemptDiscount;
		return  finalTaxValue;
	}

	public Double applyEarlyDiscount(Property property,Double earlyPaymentDiscount){
		Double finalTaxValue = property.getCadastralValue()*earlyPaymentDiscount;
		return finalTaxValue;
	}



	public void modifyRankTable() {

	}



}
