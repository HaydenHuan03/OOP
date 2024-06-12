import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Motorbike extends Vehicle{  //Inheritance
	private	Payment motorPayment ; //Composition
	private	String motorModel ;
	private	String motorBrand ;
	private	String motorPlateNumber ;
	private	String licenseClassB2 ;

    //Constructor
    public Motorbike(String owner, String ic, String motorModel, String motorBrand, String motorPlateNumber, String licenseClassB2){
        super(owner, ic) ;
        this.motorModel = motorModel ;
        this.motorBrand = motorBrand ;
        this.motorPlateNumber = motorPlateNumber ;
        this.licenseClassB2 = licenseClassB2 ;
        motorPayment = new Payment() ;
    }

    //Default Constructor
    public Motorbike()
    {
        super(null, null) ;
        motorPayment = new Payment() ;
    }

    //Accessor
    public String getModel(){return motorModel ; }
    public String getBrand(){return motorBrand ; }
    public String getPlateNumber(){return motorPlateNumber ; }
    public String getLicense(){return licenseClassB2 ; }

    //Normal function
    public void calcPrice(){motorPayment.setPrice(5.00) ; }
    public void payment(){motorPayment.paymentMethod() ;}
    public double getPriceNum(){return motorPayment.getPrice() ; }

    public Motorbike setDetail(Vehicle vehicle){
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

        Motorbike motor = new Motorbike(vehicle.vehicleOwner, vehicle.ic, model, brand, plateNumber, license);

        return motor;
    }

    public void getDetail(){
        //Inform user the details that he insert
        System.out.println("--Your Details--\n");
        System.out.println("Name: " + getOwner());
        System.out.println("IC number: " + getIC());
        System.out.println("Motorbike model: " + motorModel);
        System.out.println("Motorbike brand: " + motorBrand);
        System.out.println("Plate number: " + motorPlateNumber);
        System.out.println("License number: " + licenseClassB2);
        System.out.println("Price need to pay: RM" + getPriceNum() + "\n");
    }

    public void storeDetail(){
        //Store the details into a file
        try (BufferedWriter file = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
            file.write(getOwner() + "\t" +
                        getIC() + "\t" +
                        "Motorbike" + "\t" +
                        motorModel + "\t" +
                        motorBrand + "\t" +
                        motorPlateNumber + "\t" +
                        licenseClassB2 + "\n");
        } catch (IOException e) {
            System.out.println("File cannot be opened");
        }
    }
}
