import java.util.Scanner;

public class Vehicle{
    protected String vehicleOwner ;
	protected String ic ;

    //Constructor
    public Vehicle(String vehicleOwner, String ic){
		this.vehicleOwner = vehicleOwner ;
		this.ic = ic ;
	}

	//Default Constructor
	public Vehicle(){}

	//Accessor
	public String getOwner(){return vehicleOwner ;}
	public String getIC(){return ic ;}

	//Normal function
	public Vehicle setDetail(Scanner input){
		String name, ICnum;
        boolean isValid = true;

		//Get owner name
        System.out.println("\n-- Vehicle Sticker Application --");
        System.out.print("Please enter vehicle owner's name: ");
        name = input.nextLine();

		do{ //Looping for checking validity of IC number
            //Get IC number
            System.out.print("Please enter your IC(010101010101): ");
            ICnum = input.nextLine();
            System.out.println();

            //Check validity of IC number - does use enter letters or not
            for (int i = 0; i < ICnum.length(); i++) {
                isValid = Character.isDigit(ICnum.charAt(i));
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
            if (ICnum.length() != 12) {
                System.out.println("Please provide a valid IC number\n");
                isValid = false;
            }
        }while(isValid == false) ;

		// Add "-" to IC number
        //010101010101 -> 010101-01-0101
        StringBuilder addDash = new StringBuilder(ICnum);
        addDash.insert(6, '-');
        addDash.insert(9, '-');
        ICnum = addDash.toString();

		Vehicle vehicle = new Vehicle(name, ICnum);

		return vehicle;
	}

	public int vehicleType(Scanner input){
		int type;
		boolean validType; //Use to check the validity of type

		do{ //Looping for checking the validity of type of vehicle
            //Get vehicle type
            System.out.println("What type of vehicle do you need to register?");
            System.out.println("1. Motorbike");
            System.out.println("2. Car");
            System.out.print("Type: ");
            type = input.nextInt();
            input.nextLine(); // Consume newline
            System.out.println();

            validType = true ;

            //Check the validity of vehicle type
            if (1 > type || type > 2) {
                System.out.println("Invalid. Please choose again...\n");
                validType = false;
            }
        }while (validType == false);

		return type;
	}
}