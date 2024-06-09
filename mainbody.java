import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

public class mainbody {
    public static void main(String[] args)
    {
        System.out.println();
        Scanner input = new Scanner(System.in);
        String name, ic, model, brand, plateNumber, license;
        int type;
        boolean isValid = true;

        //Get owner name
        System.out.println("-- Vehicle Sticker Application --");
        System.out.print("Please enter vehicle owner's name: ");
        name = input.nextLine();
        
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
            if (type < 1 || type > 2) {
                System.out.println("Invalid. Please choose again...\n");
                validType = false;
            } 
        }while (validType == false);

        //Type 1: Motorbike, Type 2(default): Car
        switch (type) {
            case 1: {
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

                Motorbike userMotor = new Motorbike(name, ic, model, brand, plateNumber, license);
                userMotor.calcPrice(); //Motorbike price - RM 5

                //Inform user the details that he insert
                System.out.println("--Your Details--\n");
                System.out.println("Name: " + userMotor.getOwner());
                System.out.println("IC number: " + userMotor.getIC());
                System.out.println("Motorbike model: " + userMotor.getModel());
                System.out.println("Motorbike brand: " + userMotor.getBrand());
                System.out.println("Plate number: " + userMotor.getPlateNumber());
                System.out.println("License number: " + userMotor.getLicense());
                System.out.println("Price need to pay: RM" + userMotor.getPriceNum() + "\n");

                userMotor.payment(); //for payment purpose

                //Store the details into a file
                try (BufferedWriter file = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
                    file.write(userMotor.getOwner() + "\t" +
                               userMotor.getIC() + "\t" +
                               "Motorbike" + "\t" +
                               userMotor.getModel() + "\t" +
                               userMotor.getBrand() + "\t" +
                               userMotor.getPlateNumber() + "\t" +
                               userMotor.getLicense() + "\n");
                } catch (IOException e) {
                    System.out.println("File cannot be opened");
                }
                break;
            }
        
            default: {
                //Get car details
                System.out.print("Please enter the car model (Proton): ");
                model = input.nextLine();
                System.out.print("Please enter the car brand (Saga): ");
                brand = input.nextLine();
                System.out.print("Please enter the car plate number (ABC 1234): ");
                plateNumber = input.nextLine();
                System.out.println();

                //Get license number
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
                        isValid = false ;
                    }
                }while(isValid == false);

                Car userCar = new Car(name, ic, model, brand, plateNumber, license);
                userCar.calcPrice(); //Car price - RM 10

                //Inform user the details that he insert
                System.out.println("--Your Details--\n");
                System.out.println("Name: " + userCar.getOwner());
                System.out.println("IC number: " + userCar.getIC());
                System.out.println("Motorbike model: " + userCar.getModel());
                System.out.println("Motorbike brand: " + userCar.getBrand());
                System.out.println("Plate number: " + userCar.getPlateNumber());
                System.out.println("License number: " + userCar.getLicense());
                System.out.println("Price need to pay: RM" + userCar.getPriceNum() + "\n");

                userCar.payment(); //for payment purpose

                //Store the details into a file
                try (BufferedWriter file = new BufferedWriter(new FileWriter("vehicleInfo.txt", true))) {
                    file.write(userCar.getOwner() + "\t" +
                               userCar.getIC() + "\t" +
                               "Car" + "\t" +
                               userCar.getModel() + "\t" +
                               userCar.getBrand() + "\t" +
                               userCar.getPlateNumber() + "\t" +
                               userCar.getLicense() + "\n");
                } catch (IOException e) {
                    System.out.println("File cannot be opened");
                }
                break;
            }
        }

        input.close();
    }
}
