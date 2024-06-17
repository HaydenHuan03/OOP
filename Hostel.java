import java.util.Scanner;

abstract class Hostel {
    protected String block;
    protected String[] matric; //array of matric number to solve the problem of having two occupants in a double room
    protected int roomNumber;

    public Hostel(String block, int roomNumber, String[] matric) {
        this.block = block;
        this.roomNumber = roomNumber;
        this.matric = matric;
    }

    public abstract String getBlock();
    public abstract int getRoomNum();
    public abstract String getMatric(int index);
    public abstract void setMatric(String m);
    public abstract boolean isAvailable(); // to check the room is now full or not
    public abstract void setPayment(int type); // initialize the payment type
    public abstract void makePayment(Scanner inp);// user decide which type of payment to use, and continue payment
    public abstract boolean register(Scanner inp);// user register the room by enter the room number and block they want
    public abstract String toString();
}

class Single_Room extends Hostel {
    private int available;
    private Payment payment;

    public Single_Room(String block, int roomNumber, String[] matric, int available) {
        super(block, roomNumber, matric);
        this.available = available;
    }

    public String getBlock() {
        return block;
    }

    public int getRoomNum() {
        return roomNumber;
    }
    
    public String getMatric(int index) {
        return matric[index];
    }

    public void setMatric(String m) {
        //store the student matric number after they register a room
        matric[0] = m;
    }

    public boolean isAvailable() { 
        //if the available data stored in file is greater then 1, return true 
        //true means the room is not full yet
        return (available > 0);
    }

    public void setPayment(int type) {
        double amount = 6 * 3 * 30; //RM6 per day * 3 months and each month count for 30 days
        if(type == 1) {
            payment = new TnG(amount);// user choose to use TnG to make payment
        }
        else if(type == 2) {
            payment = new OnlineBanking(amount);// user choose to use Online Banking to make payment
        }
        else if(type == 3) {
            payment = new CreditCard(amount);// user choose to use Credit Card to make payment
        }
        else {
            payment = new VISA(amount);// user choose to use VISA to make payment
        }
    }

    public void makePayment(Scanner inp) {
        System.out.println("Choose the type of payment");
        System.out.println("1. TnG");
        System.out.println("2. Online Banking");
        System.out.println("3. Credit Card");
        System.out.println("4. VISA");
        System.out.print("\nType : ");
        int type = inp.nextInt(); //user enter which type they want to use to make payment
        inp.nextLine();
    
        setPayment(type);// set the payment type
        System.out.print("Total need to pay: RM");
        System.out.printf("%.2f%n", payment.getTotal());
        System.out.println(); //display the total amount of payment to user

        // continue to do the payment proccess
        payment.paymentProccess(inp); // user enter their payment detail to complete the payment
    }

    public boolean register(Scanner inp) {
        if(available > 0) { //when the room is available to rent, set the room to unavailable
            available--;
        }
        else return false; //when the room is not available, tell tenant fail to register

        makePayment(inp);//after set the room to unavailable, user should continue to make payment
        return true; //after make payment, tenant registration successful
    }

    public String toString() {// in order to simpifly the single room output display and easy to store into file
        return (block + ", " + roomNumber + ", " + available + ", " + matric[0]);
    }
}

class Double_Room extends Hostel {
    private int[] available;
    private Payment payment;

    public Double_Room(String block, int roomNumber, String[] matric, int[] available) {
        super(block, roomNumber, matric);
        this.available = available;
    }

    public String getBlock() {
        return block;
    }

    public int getRoomNum() {
        return roomNumber;
    }
    
    public String getMatric(int index) {
        return matric[index];
    }

    public void setMatric(String m) {
        //store the student matric number after they register a room
        //check whether the first bunk or second bunk is empty
        if(matric[0].equals("-")){ // "-" means the bunk still empty
            matric[0] = m;
        }
        else {
            matric[1] = m;
        }
    }

    public boolean isAvailable() {
        //if the available data stored in file is greater then 1, return true 
        //check twice because double room can content 2 students
        //true means the room is not full yet
        return (available[0] > 0 || available[1] > 0);
    }

    public void setPayment(int type) {
        double amount = 4 * 3 * 30; //RM6 per day * 3 months and each month count for 30 days
        if(type == 1) {
            payment = new TnG(amount); // user choose to use TnG to make payment
        }
        else if(type == 2) {
            payment = new OnlineBanking(amount);// user choose to use Online Banking to make payment
        }
        else if(type == 3) {
            payment = new CreditCard(amount);// user choose to use Credit Card to make payment
        }
        else {
            payment = new VISA(amount);// user choose to use VISA to make payment
        }
    }

    public void makePayment(Scanner inp) {
        System.out.println("Choose the type of payment");
        System.out.println("1. TnG");
        System.out.println("2. Online Banking");
        System.out.println("3. Credit Card");
        System.out.println("4. VISA");
        System.out.print("\nType : ");
        int type = inp.nextInt(); //user enter which type they want to use to make payment
        inp.nextLine();
    
        setPayment(type);// set the payment type
        System.out.print("Total need to pay: RM");
        System.out.printf("%.2f%n", payment.getTotal());
        System.out.println();//display the total amount of payment to user

        // continue to do the payment proccess
        payment.paymentProccess(inp);// user enter their payment detail to complete the payment
    }

    public boolean register(Scanner inp) {
        if(available[0] > 0) {//when the first room bunk is available to rent, set the bunk to unavailable
            available[0]--;
        }
        else if(available[1] > 0) {//when the second room bunk is available to rent, set the bunk to unavailable
            available[1]--;
        }
        else return false;//when the room is not available, tell tenant fail to register

        makePayment(inp);//after set the room to unavailable, user should continue to make payment
        return true;//after make payment, tenant registration successful
    }

    public String toString() {// in order to simpifly the double room output display and easy to store into file
        return (block + ", " + roomNumber + ", " + available[0] + ", " + available[1] + ", " + matric[0] + ", " + matric[1]);
    }
}