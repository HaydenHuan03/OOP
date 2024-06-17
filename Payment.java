import java.util.Scanner;

interface Payment {
    double PROCCESSING_FEE = 1.00;

    double getTotal();
    void paymentProccess(Scanner inp);
    void printReceipt();
}

class TnG implements Payment {
    private String phoneNum;
    private double amount;

    public TnG(double amount) {
        this.amount = amount + PROCCESSING_FEE;
    }

    public double getTotal() {
        return amount;
    }

    public void paymentProccess(Scanner inp) {
        boolean phoneNumFormat = false;

        while(!phoneNumFormat) { //when the phone number format is incorrect, repeat this loop
            System.out.println("Please enter your phone number (012-3456789)");
            System.out.print(  "> ");
            phoneNum = inp.nextLine();

            if(phoneNum.length() == 11 || phoneNum.length() == 12) { //check the phone number format
                for(int i = 0; i < phoneNum.length(); i++) {
                    if(i != 3) {
                        phoneNumFormat = Character.isDigit(phoneNum.charAt(i));
                    }
                    else {
                        phoneNumFormat = (phoneNum.charAt(i) == '-');
                    }
                }
            }

            if(!phoneNumFormat) {//if the phone number format is incorrect, tell user that the format is error
                System.out.println("Format Error! Please type again...\n");
            }
        }

        printReceipt();//after the format correct, print the receipt to payer
    }

    public void printReceipt() { //print all data related to payment details
        System.out.println("\n--Your Payment Detail--");
        System.out.println("TnG Number: " + phoneNum);
        System.out.println("Total payment: RM " + amount + "\n");
        System.out.println("The amount payable will be automatically deducted from your TnG");
        System.out.println("Thank You");
    }
}

class OnlineBanking implements Payment {
    private String bankName;
    private String accountNum;
    private double amount;

    public OnlineBanking(double amount) {
        this.amount = amount + PROCCESSING_FEE;
    }

    public double getTotal() {
        return amount;
    }

    public void paymentProccess(Scanner inp) {
        boolean accountNumFormat = false;

        while(!accountNumFormat) {//when the bank account number format is incorrect, repeat this loop
            System.out.println("Please enter your account number (111122223333)");
            System.out.print(  "> ");
            accountNum = inp.nextLine();

            System.out.println("Please enter your bank name(CIMB Bank)");
            System.out.print(  "> ");
            bankName = inp.nextLine();

            if(accountNum.length() >= 12 && accountNum.length() <= 16) {//check the account number format
                for(int i = 0; i < accountNum.length(); i++) {
                    accountNumFormat = Character.isDigit(accountNum.charAt(i));
                }
            }

            if(!accountNumFormat) {//if the account number format is incorrect, tell user that the format is error
                System.out.println("Format Error! Please type again...\n");
            }
        }

        printReceipt();//after the format correct, print the receipt to payer
    }

    public void printReceipt() {//print all data related to payment details
        System.out.println("\n--Your Payment Detail--");
        System.out.println("Bank: " + bankName);
        System.out.println("Account Number: " + accountNum);
        System.out.println("Total payment: RM " + amount + "\n");
        System.out.println("The amount payable will be automatically deducted from your bank account");
        System.out.println("Thank You");

    }
}

class CreditCard implements Payment {
    private String Credit_cardNum;
    private double amount;

    public CreditCard(double amount) {
        this.amount = amount + PROCCESSING_FEE;
    }

    public double getTotal() {
        return amount;
    }

    public void paymentProccess(Scanner inp) {
        boolean cardNumFormat = false;

        while(!cardNumFormat) {//when the credit card number format is incorrect, repeat this loop
            System.out.println("\nPlease enter your credit card number (1111222233334444)");
            System.out.print(  "> ");
            Credit_cardNum = inp.nextLine();

            if(Credit_cardNum.length() == 16) {//check the credit card number format
                for(int i = 0; i < Credit_cardNum.length(); i++) {
                    cardNumFormat = Character.isDigit(Credit_cardNum.charAt(i));
                }
            }

            if(!cardNumFormat) {//if the credit number format is incorrect, tell user that the format is error
                System.out.println("Format Error! Please type again...\n");
            }
        }

        printReceipt();//after the format correct, print the receipt to payer
    }

    public void printReceipt() {//print all data related to payment details
        System.out.println("\n--Your Payment Detail--");
        System.out.println("Credit Card Number: " + Credit_cardNum);
        System.out.println("Total payment: RM " + amount + "\n");
        System.out.println("Thank You");
    }
}

class VISA implements Payment {
    private String VISA_cardNum;
    private double amount;

    public VISA(double amount) {
        this.amount = amount + PROCCESSING_FEE;
    }

    public double getTotal() {
        return amount;
    }

    public void paymentProccess(Scanner inp) {
        boolean cardNumFormat = false;

        while(!cardNumFormat) {//when the VISA number format is incorrect, repeat this loop
            System.out.println("Please enter your VISA card number (1111222233334444)");
            System.out.print(  "> ");
            VISA_cardNum = inp.nextLine();

            if(VISA_cardNum.length() == 16) {//check the VISA card number format
                for(int i = 0; i < VISA_cardNum.length(); i++) {
                    cardNumFormat = Character.isDigit(VISA_cardNum.charAt(i));
                }
            }

            if(!cardNumFormat) {//if the VISA number format is incorrect, tell user that the format is error
                System.out.println("Format Error! Please type again...\n");
            }
        }

        printReceipt();//after the format correct, print the receipt to payer
    }

    public void printReceipt() {//print all data related to payment details
        System.out.println("--Your Payment Detail--");
        System.out.println("VISA Card Number: " + VISA_cardNum);
        System.out.println("Total payment: RM " + amount + "\n");
        System.out.println("Thank You");
    }
}