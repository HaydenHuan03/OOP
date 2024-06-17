import java.util.Scanner;
import java.io.*;

abstract class Vehicle{
    protected String vehicleOwner ;
	protected String ic ;

    //Constructor
    public Vehicle(String vehicleOwner, String ic){
		this.vehicleOwner = vehicleOwner ;
        this.ic = ic;
	}

	//Accessor
	public String getOwner(){return vehicleOwner ;}
	public String getIC(){return ic ;}

	//Normal function
	public void setIC(Scanner input){
        boolean isValid = true;

		do{ //Looping for checking validity of IC number
            //Get IC number
            System.out.print("Please enter your IC(010101010101): ");
            ic = input.nextLine();
            System.out.println();

            //Check validity of IC number - does use enter letters or not
            for (int i = 0; i < ic.length(); i++) {
                isValid = Character.isDigit(ic.charAt(i));
                if (isValid == false) {
                    System.out.println("Please provide a valid IC number\n");
                    break;
                }
            }

            //If not valid, directly looping again to get IC number
            if(isValid == false){
                continue;
            }

            //Check the length of IC number
            if (ic.length() != 12) {
                System.out.println("Please provide a valid IC number\n");
                isValid = false;
            }
        }while(isValid == false) ;

		// Add "-" to IC number
        //010101010101 -> 010101-01-0101
        StringBuilder addDash = new StringBuilder(ic);
        addDash.insert(6, '-');
        addDash.insert(9, '-');
        ic = addDash.toString();
	}

    public abstract void register(Scanner input);
    public abstract String toString();
    public abstract void setModel(String model);
    public abstract void setBrand(String brand);
    public abstract void setPlateNumber(String plateNum);
    public abstract void setLicense(String license);
}

class Motorbike extends Vehicle{  //Inheritance
	private	Payment payment ; //Composition
	private	String motorModel ;
	private	String motorBrand ;
	private	String motorPlateNumber ;
	private	String licenseClassB2 ;

    public Motorbike(String vehicleOwner, String ic){
		super(vehicleOwner, ic);
	}

    public void setModel(String model) {
        motorModel = model;
    }

    public void setBrand(String brand) {
        motorBrand = brand;
    }

    public void setPlateNumber(String plateNum) {
        motorPlateNumber = plateNum;
    }

    public void setLicense(String license) {
        licenseClassB2 = license;
    }

    //Normal function
    public void setPayment(int type) {
        double amount = 5;
        if(type == 1) {
            payment = new TnG(amount);
        }
        else if(type == 2) {
            payment = new OnlineBanking(amount);
        }
        else if(type == 3) {
            payment = new CreditCard(amount);
        }
        else {
            payment = new VISA(amount);
        }
    }

    public void makePayment(Scanner inp) {
        System.out.println("Choose the type of payment");
        System.out.println("1. TnG");
        System.out.println("2. Online Banking");
        System.out.println("3. Credit Card");
        System.out.println("4. VISA");
        System.out.print("\nType : ");
        int type = inp.nextInt();
        inp.nextLine();

        setPayment(type);
        System.out.println("Price need to pay: RM" + payment.getTotal() + "\n");

        payment.paymentProccess(inp);
    }

    public void setDetail(Scanner input){
        boolean isValid = true;

        //Get owner IC
        setIC(input);

        //Get motorbike details
        System.out.print("Please enter the motorbike model (Yamaha): ");
        motorModel = input.nextLine();
        System.out.print("Please enter the motorbike brand (MT-15): ");
        motorBrand = input.nextLine();
        System.out.print("Please enter the motorbike plate number (ABC 1234): ");
        motorPlateNumber = input.nextLine();
        System.out.println();

        //Get lisence number
        do{
            System.out.println("Please provide your license number (12345678)");
            System.out.println("(The license number is located at the upper right corner of the back side of the card)");
            System.out.print("License number: ");
            licenseClassB2 = input.nextLine();
            System.out.println();

            isValid = true;

            for (int i = 0; i < licenseClassB2.length(); i++) {
                isValid = Character.isDigit(licenseClassB2.charAt(i)) ;
                if (isValid == false) {
                    System.out.println("Please provide a valid license number\n");
                    break;
                }
            }

            if(isValid == false){
                continue;
            }

            if (licenseClassB2.length() != 8) {
                System.out.println("Please provide a valid license number\n");
                isValid = false;
            }
        }while (isValid == false);
    }

    public void getDetail(){
        //Inform user the details that he insert
        System.out.println("--Your Details--\n");
        System.out.println("Matric No: " + getOwner());
        System.out.println("IC number: " + getIC());
        System.out.println("Motorbike model: " + motorModel);
        System.out.println("Motorbike brand: " + motorBrand);
        System.out.println("Plate number: " + motorPlateNumber);
        System.out.println("License number: " + licenseClassB2);
    }

    public void storeDetail(){
        //Store the details into a file
        try (BufferedWriter file = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
            file.write(toString() + "\n");
        } catch (IOException e) {
            System.out.println("File cannot be opened");
        }
    }

    public void register(Scanner inp) {
        setDetail(inp);// set the detail information of motorbike

        System.out.println("Press any key to continue");
        inp.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();

        getDetail();//Inform user the details that he insert
        System.out.println("Continue to make payment");
        System.out.println("\033[H\033[2J");
        System.out.flush();

        makePayment(inp);//for payment purpose
        System.out.println("Press any key to continue");
        inp.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();

        storeDetail();//Store the details into a file
    }

    public String toString() {
        return (getOwner() + ", " + getIC() + ", " + "Motorbike" + ", " + motorModel + ", " + motorBrand + ", " + motorPlateNumber + ", " + licenseClassB2);
    }
}

class Car extends Vehicle{  //Inheritance
	private	Payment payment ; //Composition
	private	String carModel ;
	private	String carBrand ;
	private	String carPlateNumber ;
	private	String licenseClassD ;

    public Car(String vehicleOwner, String ic){
		super(vehicleOwner, ic);
	}

    public void setModel(String model) {
        carModel = model;
    }

    public void setBrand(String brand) {
        carBrand = brand;
    }

    public void setPlateNumber(String plateNum) {
        carPlateNumber = plateNum;
    }

    public void setLicense(String license) {
        licenseClassD = license;
    }

	//Normal function
	public void setPayment(int type) {
        double amount = 10;
        if(type == 1) {
            payment = new TnG(amount);
        }
        else if(type == 2) {
            payment = new OnlineBanking(amount);
        }
        else if(type == 3) {
            payment = new CreditCard(amount);
        }
        else {
            payment = new VISA(amount);
        }
    }

    public void makePayment(Scanner inp) {
        System.out.println("Choose the type of payment");
        System.out.println("1. TnG");
        System.out.println("2. Online Banking");
        System.out.println("3. Credit Card");
        System.out.println("4. VISA");
        System.out.print("\nType : ");
        int type = inp.nextInt(); 
        inp.nextLine();
    
        setPayment(type);
        System.out.println("Price need to pay: RM" + payment.getTotal() + "\n");

        payment.paymentProccess(inp);
    }

	public void setDetail(Scanner input){
        boolean isValid = true;

        //Set owner IC
        setIC(input);

        //Get motorbike details
        System.out.print("Please enter the car model (Toyota): ");
        carModel = input.nextLine();
        System.out.print("Please enter the car brand (Vios): ");
        carBrand = input.nextLine();
        System.out.print("Please enter the car plate number (ABC 1234): ");
        carPlateNumber = input.nextLine();
        System.out.println();

        //Get lisence number
        do{
            System.out.println("Please provide your license number (12345678)");
            System.out.println("(The license number is located at the upper right corner of the back side of the card)");
            System.out.print("License number: ");
            licenseClassD = input.nextLine();
            System.out.println();

            isValid = true;

            for (int i = 0; i < licenseClassD.length(); i++) {
                isValid = Character.isDigit(licenseClassD.charAt(i)) ;
                if (isValid == false) {
                    System.out.println("Please provide a valid license number\n");
                    break;
                }
            }

            if(isValid == false){
                continue;
            }

            if (licenseClassD.length() != 8) {
                System.out.println("Please provide a valid license number\n");
                isValid = false;
            }
        }while (isValid == false);
    }

    public void getDetail(){
        //Inform user the details that he insert
        System.out.println("--Your Details--\n");
        System.out.println("Name: " + getOwner());
        System.out.println("IC number: " + getIC());
        System.out.println("Car model: " + carModel);
        System.out.println("Car brand: " + carBrand);
        System.out.println("Plate number: " + carPlateNumber);
        System.out.println("License number: " + licenseClassD);
    }

    public void storeDetail(){
        //Store the details into a file
        try (BufferedWriter file = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
            file.write(toString() + "\n");
        } catch (IOException e) {
            System.out.println("File cannot be opened");
        }
    }

    public void register(Scanner inp) {
        setDetail(inp); // set the detail information of car

        System.out.println("Press any key to continue");
        inp.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();

        getDetail();//Inform user the details that he insert
        System.out.println("Continue to make payment");
        System.out.println("\033[H\033[2J");
        System.out.flush();

        makePayment(inp);//for payment purpose
        System.out.println("Press any key to continue");
        inp.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();

        storeDetail();//Store the details into a file
    }

    public String toString() {
        return (getOwner() + ", " + getIC() + ", " + "Car" + ", " + carModel + ", " + carBrand + ", " + carPlateNumber + ", " + licenseClassD);
    }
}