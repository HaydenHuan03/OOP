import java.util.Scanner;

abstract class Hostel {
    protected String block;
    protected String[] matric;
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
    public abstract boolean isAvailable();
    public abstract void setPayment(int type);
    public abstract void makePayment(Scanner inp);
    public abstract boolean register(Scanner inp);
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
        matric[0] = m;
    }

    public boolean isAvailable() {
        return (available > 0);
    }

    public void setPayment(int type) {
        double amount = 6 * 3 * 30;
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
        System.out.println("Total need to pay : RM" + payment.getTotal());

        payment.paymentProccess(inp);
    }

    public boolean register(Scanner inp) {
        if(available > 0) {
            available--;
        }
        else return false;

        makePayment(inp);
        return true;
    }

    public String toString() {
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
        if(matric[0].equals("-")){
            matric[0] = m;
        }
        else {
            matric[1] = m;
        }
    }

    public boolean isAvailable() {
        return (available[0] > 0 || available[1] > 0);
    }

    public void setPayment(int type) {
        double amount = 4 * 3 * 30;
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
        System.out.println("Total need to pay : RM" + payment.getTotal());

        payment.paymentProccess(inp);
    }

    public boolean register(Scanner inp) {
        if(available[0] > 0) {
            available[0]--;
        }
        else if(available[1] > 0) {
            available[1]--;
        }
        else return false;

        makePayment(inp);
        return true;
    }

    public String toString() {
        return (block + ", " + roomNumber + ", " + available[0] + ", " + available[1] + ", " + matric[0] + ", " + matric[1]);
    }
}
