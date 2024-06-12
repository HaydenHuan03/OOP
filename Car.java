import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Car extends Vehicle{  //Inheritance
	private	Payment carPayment ; //Composition
	private	String carModel ;
	private	String carBrand ;
	private	String carPlateNumber ;
	private	String licenseClassD ;

	//Constructor
	public Car(String owner, String ic, String carModel, String carBrand, String carPlateNumber, String licenseClassD){
		super(owner, ic) ;
		this.carModel = carModel ;
		this.carBrand = carBrand ;
		this.carPlateNumber = carPlateNumber ;
		this.licenseClassD = licenseClassD ;
        carPayment = new Payment() ;
	}

	//Default Constructor
    public Car(){
        super(null, null) ;
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
    public double getPriceNum(){return carPayment.getPrice() ;}

	public Car setDetail(Vehicle vehicle){
        String model, brand, plateNumber, license;
        Scanner input = new Scanner(System.in);
        boolean isValid = true;

        //Get motorbike details
        System.out.print("Please enter the motorbike model (Yamaha): ");
        model = input.nextLine();
        System.out.print("Please enter the motorbike brand (MT-15): ");
        brand = input.nextLine();
        System.out.print("Please enter the motorbike plate number (ABC 1234): ");
        plateNumber = input.nextLine();
        System.out.println();

        //Get lisence number
        do{
            System.out.println("Please provide your license number (12345678)");
            System.out.println("(The license number is located at the upper right corner of the back side of the card)");
            System.out.print("License number: ");
            license = input.nextLine();
            System.out.println();

            isValid = true;

            for (int i = 0; i < license.length(); i++) {
                isValid = Character.isDigit(license.charAt(i)) ;
                if (isValid == false) {
                    System.out.println("Please provide a valid license number\n");
                    break;
                }
            }

            if(isValid == false){
                continue;
            }

            if (license.length() != 8) {
                System.out.println("Please provide a valid license number\n");
                isValid = false;
            }
        }while (isValid == false);

        input.close();

        Car car = new Car(vehicle.vehicleOwner, vehicle.ic, model, brand, plateNumber, license);

        return car;
    }

    public void getDetail(){
        //Inform user the details that he insert
        System.out.println("--Your Details--\n");
        System.out.println("Name: " + getOwner());
        System.out.println("IC number: " + getIC());
        System.out.println("Motorbike model: " + carModel);
        System.out.println("Motorbike brand: " + carBrand);
        System.out.println("Plate number: " + carPlateNumber);
        System.out.println("License number: " + licenseClassD);
        System.out.println("Price need to pay: RM" + getPriceNum() + "\n");
    }

    public void storeDetail(){
        //Store the details into a file
        try (BufferedWriter file = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
            file.write(getOwner() + "\t" +
                        getIC() + "\t" +
                        "Motorbike" + "\t" +
                        carModel + "\t" +
                        carBrand + "\t" +
                        carPlateNumber + "\t" +
                        licenseClassD + "\n");
        } catch (IOException e) {
            System.out.println("File cannot be opened");
        }
    }
}
