import java.util.Scanner ;

public class Payment {
    private double price ;
	
	//Default Constructor
	public Payment(){this.price = 0.00 ;}
		
	//Mutator
	public void setPrice(double price){this.price = price ;}
		
	//Accessor
	public double getPrice(){return price ;}
	
	//Normal function - Main function of class Payment
    public void paymentMethod()
	{
		int type ; //Get the type for payment that use choose
        boolean validMethod = true; //Use to check the validity of type
        Scanner input = new Scanner(System.in) ;
			
		do{ //For looping if user key in "type" other than 1 and 2
            System.out.println("Choose the type of payment");
            System.out.println("1. TnG");
            System.out.println("2. Bank");
            System.out.print("\nType : ");
            type = input.nextInt();
            input.nextLine();
            System.out.println();

            //Choose TnG
            if(type == 1)
            {
                String contactNumber ; //Get the phone number
                int length ; //Use to find the length of phone number
                boolean isValid = true; //Use to check the validity of phone number
                    
                do{ //For looping if user key in invalid phone number
                    System.out.print("Contact Number (0123456789): ");
                    contactNumber = input.nextLine();
                    length = contactNumber.length();
                    System.out.println();
                        
                    //Retake contact number if key in character other than digit
                    for(int i = 0 ; i < length ; i++)
                    {
                        isValid = Character.isDigit(contactNumber.charAt(i));
                        if(isValid == false){
                            System.out.println("Please provide a valid contact number\n");
                            break;
                        }
                    }

                    if(isValid == false){
                        continue;
                    }

                    //Retake contact number if key in wrong phone number					
                    if(length < 10 || length > 11){
                        System.out.println("Please provide a valid contact number\n");
                        isValid = false;
                    }

                    if(isValid == false){
                        continue;
                    }

                    //Retake contact number if key in wrong phone number
                    //Malaysia phone number starts from "01"		
                    //Check if the phone number starts with "01"
                    if (contactNumber.substring(0, 2).equals("01")) {
                        break;
                    } else {
                        System.out.println("Please provide a valid contact number\n");
                        isValid = false;
                    }

                    if(isValid == false){
                        continue;
                    }
                }while(isValid == false); 

                //Add "-" to contact number
                //0123456789 -> 012-3456789
                StringBuilder addDash = new StringBuilder(contactNumber);
                addDash.insert(3, '-') ;
                contactNumber = addDash.toString();
                    
                //Show the payment details of user 
                System.out.println("--Your Payment Detail--");
                System.out.println("TnG Number: " + contactNumber);
                System.out.println("Total payment: RM " + price + "\n");
                System.out.println("The amount payable will be automatically deducted from your TnG");
                System.out.println("Thank You");

                validMethod = true;
            }

            //Choose Bank
            else if(type == 2)
            {
                String bankAccount ; //Get bank account number
                boolean isValid = true; //Use to check validity of bank account number
                    
                do{ //For looping if user key in invalid account number
                    System.out.print("Account Number: ");
                    bankAccount = input.nextLine();
                    System.out.println();

                    //Retake account number if key in character other than digit
                    for(int i = 0 ; i <bankAccount.length() ; i++)
                    {
                        isValid = Character.isDigit(bankAccount.charAt(i));
                        if(isValid == false){
                            System.out.println("Please provide a valid contact number\n");
                            break;
                        }
                    }

                    if(isValid == false){
                        continue;
                    }
                }while(isValid == false);

                //Show the payment details of user 
                System.out.println("--Your Payment Detail--");
                System.out.println("Account Number: " + bankAccount);
                System.out.println("Total payment: RM " + price + "\n");
                System.out.println("The amount payable will be automatically deducted from your bank account");
                System.out.println("Thank You");

                validMethod = true;
            }
                
            //Choose other than TnG and Bank
            else{
                System.out.println("Invalid. Please choose again...\n");
                validMethod = false;
            }
        }while(validMethod == false) ;
        
        input.close();
	}
}
