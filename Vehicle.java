public class Vehicle{
    protected String vehicleOwner ;
	protected String ic ;	

    //Constructor
    public Vehicle(String vehicleOwner, String ic)
	{
		this.vehicleOwner = vehicleOwner ;
		this.ic = ic ;
	}
	
		//Accessor
		String getOwner(){return vehicleOwner ;}
		String getIC(){return ic ;}	
}