import java.io.*;
import java.util.Scanner;

class HostelRegistration {
    private Student student;
    private Staff staff;
    private double price;
    private boolean registered;
    private String registrationDate;
    private String contactNum;

    public HostelRegistration(Student student, Staff staff, double price, boolean registered, String registrationDate, String contactNum) {
        this.student = student;
        this.staff = staff;
        this.price = price;
        this.registered = registered;
        this.registrationDate = registrationDate;
        this.contactNum = contactNum;
    }

    public boolean Register(String[][] Info) {
        // Verify user and register
        for (String[] userInfo : Info) {
            if (student != null && student.getMatricNum().equals(userInfo[0]) && student.getContact().equals(userInfo[1])) {
                registered = true;
                return true;
            } else if (staff != null && staff.getContact().equals(userInfo[1])) {
                registered = true;
                return true;
            }
        }
        registered = false;
        return false;
    }

    public double offerPrice() {
        return price;
    }

    public void printRoomInfo(String[][] Info) {
        for (String[] roomInfo : Info) {
            System.out.println("Name: " + roomInfo[0] + ", Matric: " + roomInfo[1] + ", Block: " + roomInfo[2] + ", Room Number: " + roomInfo[3] + ", Price: " + roomInfo[4] + ", Registartion Date: " + registrationDate);
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}

class SingleRoom extends HostelRegistration {
    private Payment roomPayment;
    private String block;
    private String roomNumber;

    public SingleRoom(Student student, Staff staff, double price, boolean registered, String registrationDate, String contactNum, String block, String roomNumber) {
        super(student, staff, price, registered, registrationDate, contactNum);
        this.block = block;
        this.roomNumber = roomNumber;
    }

    public boolean Register() throws IOException {
        // Logic for Single Room registration
        Scanner inputFile = new Scanner(new File("roomData.txt"));
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();
            String[] roomInfo = line.split(", ");
            if (roomInfo[0].equals(block) && roomInfo[1].equals(roomNumber) && roomInfo[2].equals("available")) {
                FileWriter outputFile = new FileWriter("roomData.txt", true);
                outputFile.write(getStudent().getName() + ", " + getStudent().getMatricNum() + ", " + block + ", " + roomNumber + ", " + "single" + ", " + offerPrice() + "\n");
                outputFile.close();
                return true;
            }
        }
        inputFile.close();
        return false;
    }


    public void calcPrice(boolean isStaff) {
        if (isStaff) {
            super.setPrice(4 * 0.9); // 10% discount for staff
        } else {
            super.setPrice(4);
        }
    }

    public void checkAvailableRoom() throws IOException {
        Scanner inputFile = new Scanner(new File("roomData.txt"));
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();
            String[] roomInfo = line.split(", ");
            if (roomInfo[4].equals("single") && roomInfo[2].equals("available")) {
                System.out.println("Block: " + roomInfo[0] + ", Room Number: " + roomInfo[1]);
            }
        }
        inputFile.close();
    }

    public Payment getRoomPayment() {
        return roomPayment;
    }

    public void setRoomPayment(Payment roomPayment) {
        this.roomPayment = roomPayment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}

class DoubleRoom extends HostelRegistration {
    private Payment roomPayment;
    private String block;
    private String roomNumber;

    public DoubleRoom(Student student, Staff staff, double price, boolean registered, String registrationDate, String contactNum, String block, String roomNumber) {
        super(student, staff, price, registered, registrationDate, contactNum);
        this.block = block;
        this.roomNumber = roomNumber;
    }

    public boolean Register() throws IOException {
        // Logic for Double Room registration
        Scanner inputFile = new Scanner(new File("roomData.txt"));
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();
            String[] roomInfo = line.split(", ");
            if (roomInfo[0].equals(block) && roomInfo[1].equals(roomNumber) && roomInfo[2].equals("available")) {
                FileWriter outputFile = new FileWriter("roomData.txt", true);
                outputFile.write(getStudent().getName() + ", " + getStudent().getMatricNum() + ", " + block + ", " + roomNumber + ", " + "double" + ", " + offerPrice() + "\n");
                outputFile.close();
                return true;
            }
        }
        inputFile.close();
        return false;
    }

    public void calcPrice(boolean isStaff) {
        if (isStaff) {
            super.setPrice(6 * 0.9); // 10% discount for staff
        } else {
            super.setPrice(6);
        }
    }

    public void checkAvailableRoom() throws IOException {
        Scanner inputFile = new Scanner(new File("roomData.txt"));
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();
            String[] roomInfo = line.split(", ");
            if (roomInfo[4].equals("double") && roomInfo[2].equals("available")) {
                System.out.println("Block: " + roomInfo[0] + ", Room Number: " + roomInfo[1]);
            }
        }
        inputFile.close();
    }

    public Payment getRoomPayment() {
        return roomPayment;
    }

    public void setRoomPayment(Payment roomPayment) {
        this.roomPayment = roomPayment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}