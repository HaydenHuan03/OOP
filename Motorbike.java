public class Motorbike extends Vehicle{  //Inheritance
	private	Payment motorPayment ; //Composition
	private	String motorModel ;
	private	String motorBrand ;
	private	String motorPlateNumber ;
	private	String licenseClassB2 ;

    //Constructor
    public Motorbike(String owner, String ic, String motorModel, 
                     String motorBrand, String motorPlateNumber, String licenseClassB2)
    {
        super(owner, ic) ;
        this.motorModel = motorModel ;
        this.motorBrand = motorBrand ;
        this.motorPlateNumber = motorPlateNumber ;
        this.licenseClassB2 = licenseClassB2 ;
        motorPayment = new Payment() ;
    }
    
    //Accessor
    public String getModel(){return motorModel ; }
    public String getBrand(){return motorBrand ; }
    public String getPlateNumber(){return motorPlateNumber ; }
    public String getLicense(){return licenseClassB2 ; }
    
    //Normal function
    void calcPrice(){motorPayment.setPrice(5.00) ;}
    void payment(){motorPayment.paymentMethod() ;}
    double getPriceNum()
    {
        double price = motorPayment.getPrice() ;
        return price ;
    }
}
