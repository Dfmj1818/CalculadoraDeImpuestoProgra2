package co.edu.uptc.model;

public class Discount {
	
	private DiscountType discountType;
    private Double discountValue;
    
    
    public Discount() {
       	
    }
	
    public void setDiscountType(DiscountType discountType){
    	this.discountType = discountType;
    
    }
    
    public DiscountType getDiscountType() {
    	return discountType;
    }
    
    public void setDiscountValue(Double discountValue) {
    	this.discountValue = discountValue;
    }
    
    public Double getDiscountValue(){
    	return discountValue;
    }
    

}
