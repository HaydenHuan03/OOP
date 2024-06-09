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
	public String getOwner(){return vehicleOwner ;}
	public String getIC(){return ic ;}
}