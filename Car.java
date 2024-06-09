public class Car extends Vehicle{  //Inheritance
	private	Payment carPayment ; //Composition
	private	String carModel ;
	private	String carBrand ;
	private	String carPlateNumber ;
	private	String licenseClassD ;

	//Constructor
	public Car(String owner, String ic, String carModel, 
				  String carBrand, String carPlateNumber, String licenseClassD)
	{
		super(owner, ic) ; 
		this.carModel = carModel ;
		this.carBrand = carBrand ;
		this.carPlateNumber = carPlateNumber ;
		this.licenseClassD = licenseClassD ;
        carPayment = new Payment() ;
	}
		
	//Accessor
    public String getModel(){return carModel ; }
    public String getBrand(){return carBrand ; }
    public String getPlateNumber(){return carPlateNumber ; }
    public String getLicense(){return licenseClassD ; }
		
	//Normal function
	public void calcPrice(){carPayment.setPrice(10.00) ;}
    public void payment(){carPayment.paymentMethod() ;}
    public double getPriceNum()
	{
		double price = carPayment.getPrice() ;
		return price ;
	}
}
