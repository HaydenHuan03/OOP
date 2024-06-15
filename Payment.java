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

        while(!phoneNumFormat) {
            System.out.println("Please enter your phone number");
            System.out.print(  "> ");
            phoneNum = inp.nextLine();

            if(phoneNum.length() == 11 || phoneNum.length() == 12) {
                for(int i = 0; i < phoneNum.length(); i++) {
                    if(i != 3) {
                        phoneNumFormat = Character.isDigit(phoneNum.charAt(i));
                    }
                    else {
                        phoneNumFormat = (phoneNum.charAt(i) == '-');
                    }
                }
            }

            if(!phoneNumFormat) {
                System.out.println("Format Error! Please type again...");
            }
        }

        printReceipt();
    }

    public void printReceipt() {
        System.out.println("--Your Payment Detail--");
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

        while(!accountNumFormat) {
            System.out.println("Please enter your account number");
            System.out.print(  "> ");
            accountNum = inp.nextLine();

            System.out.println("Please enter your bank name");
            System.out.print(  "> ");
            bankName = inp.nextLine();

            if(accountNum.length() >= 12 && accountNum.length() <= 16) {
                for(int i = 0; i < accountNum.length(); i++) {
                    accountNumFormat = Character.isDigit(accountNum.charAt(i));
                }
            }

            if(!accountNumFormat) {
                System.out.println("Format Error! Please type again...");
            }
        }

        printReceipt();
    }

    public void printReceipt() {
        System.out.println("--Your Payment Detail--");
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

        while(!cardNumFormat) {
            System.out.println("Please enter your phone number");
            System.out.print(  "> ");
            Credit_cardNum = inp.nextLine();

            if(Credit_cardNum.length() == 16) {
                for(int i = 0; i < Credit_cardNum.length(); i++) {
                    cardNumFormat = Character.isDigit(Credit_cardNum.charAt(i));
                }    
            }
            
            if(!cardNumFormat) {
                System.out.println("Format Error! Please type again...");
            }
        }

        printReceipt();
    }

    public void printReceipt() {
        System.out.println("--Your Payment Detail--");
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

        while(!cardNumFormat) {
            System.out.println("Please enter your phone number");
            System.out.print(  "> ");
            VISA_cardNum = inp.nextLine();

            if(VISA_cardNum.length() == 16) {
                for(int i = 0; i < VISA_cardNum.length(); i++) {
                    cardNumFormat = Character.isDigit(VISA_cardNum.charAt(i));
                }    
            }
            
            if(!cardNumFormat) {
                System.out.println("Format Error! Please type again...");
            }
        }

        printReceipt();
    }

    public void printReceipt() {
        System.out.println("--Your Payment Detail--");
        System.out.println("VISA Card Number: " + VISA_cardNum);
        System.out.println("Total payment: RM " + amount + "\n");
        System.out.println("Thank You");
    }
}