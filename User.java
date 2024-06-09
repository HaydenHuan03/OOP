import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class User {
    private String name;
    private String email;
    private String contact;
    private String password;
    private boolean adminPrivileges; //to determine the user is student(0) or staff(1)
    
    public User(String name, String email, String contact, String password, boolean adminPrivileges) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.adminPrivileges = adminPrivileges;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }

    public boolean getAdminPrivileges() {
        return adminPrivileges;
    }

    public boolean login(String name, String password) throws Exception{
        // input file to read all the user data from file
        File file = new File("userData.txt");
        Scanner inputFile = new Scanner(file);
        

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            if(name == userInfo[1] && password == userInfo[4]) { // if the name and password is correct, return true which mean successful login
                inputFile.close();
                return true;
            }
        }
        
        inputFile.close();
        return false; // if there is a wrong on name or password, return false which mean failed to login
    }

    public boolean register() throws Exception{
        Scanner inputFile = new Scanner(new File("userData.txt"));
        
        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            // check whether the new user's name, email or contact number is same with the exist user or not
            // if there is same, return false
            if((name == userInfo[1]) || (email == userInfo[2]) || (contact == userInfo[3])) { 
                inputFile.close();
                return false;
            }
        }

        inputFile.close();
        return true;
    }
    
}

class Student extends User {
    private int studentID;
    private String matricNum;
    private Appointment appointment;
    
    public Student(String name, String email, String contact, String password, int studentID, String matricNum) {
        super(name, email, contact, password, false);
        this.studentID = studentID;
        this.matricNum = matricNum;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMatricNum() {
        return matricNum;
    }

    public int calculateStudentID() throws Exception{
        Scanner inputFile = new Scanner(new File("userData.txt"));
        int highest = 0;

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            // find the highest student id among current user 
            // use Integer.valueOf to convert String to integer
            if(Integer.valueOf(userInfo[0]) > highest) { 
                highest = Integer.valueOf(userInfo[0]);
            }
        }
        studentID = highest + 1; //assign the incremented highest id to studentId

        inputFile.close();
        return studentID;
    }

    @Override
    public boolean login(String name, String password) throws Exception{
        if(super.login(name, password)) {
            System.out.println("Welcome, " + name);
            return true;
        }
        else {
            System.out.println("Invalid user name or password !");
            return false;
        }
    }

    @Override
    public boolean register() throws Exception{
        if(super.register()) {
            Scanner inputFile = new Scanner(new File("userData.txt"));

            while(inputFile.hasNextLine()) {
                String line = inputFile.nextLine(); // read a whole line in file
                String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point
    
                if(matricNum == userInfo[6]) { // compare the key in matric number with registered matric number
                    System.out.println("User Existed !");
                    inputFile.close();
                    return false; // if same, return false since the user already exist
                }
            }

            // open output file to assign user data to userData.txt
            FileWriter outputFile = new FileWriter("userData.txt", true); 
            outputFile.write(calculateStudentID() + "," +
                             getName() + "," +
                             getEmail() + "," +
                             getContact() + "," +
                             getPassword() + "," +
                             getAdminPrivileges() + "," +
                             matricNum + "\n");
            

            outputFile.close();
            inputFile.close();

            System.out.println("Congrats, your registration successful !");
            return true;
        }
        else {
            System.out.println("User Existed !");
            return false;
        }
    }

    //*Appointment
    public void makeAppointment() throws Exception{
        Scanner input = new Scanner(System.in);
        int type;
        String date, time;

        // ask student to fill in the neccessary input to make an appointment
        do { 
            System.out.println("Enter appointment type : ");
            System.out.println("1. Administration Appointment");
            System.out.println("2. Faculty Appointment");
            System.out.println("3. Staff Appointment");
            System.out.print("type : ");
            type = input.nextInt(); //set the appointment type in Integer 
            input.nextLine();

            if(type < 0 || type > 3) {
                System.out.println("Invalid input");
            }

        } while(type <= 0 || type > 3); //since there are 3 type only, so student should key in again if there is an invalid input

        System.out.print("Enter appointment data (1/1/2024) : ");
        date = input.nextLine(); //set the date

        System.out.print("Enter appointment time (continential time) : ");
        time = input.nextLine(); //set the time


        input.close();
        appointment = new Appointment(type, date, time, matricNum); 
        appointment.addAppointment(); //save appointment made into file

    }

    public void displayAppointment() throws Exception{
        Scanner inputFile = new Scanner(new File("appointmentData.txt"));

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in fil
            String[] appointmentInfo = line.split(", "); // saperate this line into few string by using "," as break point

            // display the appointment information if this appointment is made by this student
            // by comparing the appointment owner's matric and this student's matric number
            if(appointmentInfo[5] == matricNum) { 
                for(int i = 0; i < 5; i++) {
                    System.out.print(appointmentInfo[i] + ", ");
                }
                System.out.println();
            }
        }

        inputFile.close();
    }
}

class Staff extends User {
    private int staffID;
    private ArrayList<Appointment> appointment;
    
    public Staff(String name, String email, String contact, String password, int staffID) {
        super(name, email, contact, password, true);
        appointment = new ArrayList<>();
        this.staffID = staffID;
    }
    
    public int getStaffID() {
        return staffID;
    }

    public int calculateStaffID() throws Exception{
        Scanner inputFile = new Scanner(new File("userData.txt"));
        int highest = 0;

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            // find the highest staff id among current user 
            // use Integer.valueOf to convert String to integer
            if(Integer.valueOf(userInfo[0]) > highest) { 
                highest = Integer.valueOf(userInfo[0]);
            }
        }
        staffID = highest + 1; //assign the incremented highest id to staffId

        inputFile.close();
        return staffID;
    }

    @Override
    public boolean login(String name, String password) throws Exception{
        if(super.login(name, password)) {
            System.out.println("Welcome, " + name);
            return true;
        }
        else {
            System.out.println("Invalid user name or password !");
            return false;
        }
    }

    @Override
    public boolean register() throws Exception{
        if(super.register()) {
            // open output file to assign user data to userData.txt
            FileWriter outputFile = new FileWriter("userData.txt", true);
            outputFile.write(calculateStaffID() + "," +
                             getName() + "," +
                             getEmail() + "," +
                             getContact() + "," +
                             getPassword() + "," +
                             getAdminPrivileges() + "\n");

            outputFile.close();

            System.out.println("Congrats, your registration successful !");
            return true;
        }
        else {
            System.out.println("User Existed !");
            return false;
        }
    }

    //*Apointment
    public void readAppointment() throws Exception{
        Scanner inputFile  = new Scanner(new File("appointmentData.txt"));
        int i = 0; // use i to allocate the appointment array list

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] appointmentInfo = line.split(", "); // saperate this line into few string by using "," as break point

            //read all the data from apointmentData file and assign those to an array list
            appointment.get(i).setId(Integer.valueOf(appointmentInfo[0]));
            appointment.get(i).setType(appointmentInfo[1]);
            appointment.get(i).setDate(appointmentInfo[2]);
            appointment.get(i).setTime(appointmentInfo[3]);
            appointment.get(i).setStatus(appointmentInfo[4]);
            appointment.get(i).setOwnerMatric(appointmentInfo[5]);
            i++;
        }

        inputFile.close();
    }

    public void checkAppointment() throws Exception{
        // display all the appointment made
        for(Appointment app : appointment) {
            System.out.println(app.toString());
        }
    }

    public void approveAppointment() throws Exception{
        Scanner input = new Scanner(System.in);
        int appointNum, decision; // decision decide the status of appointment
        checkAppointment();

        System.out.print("\nSelect an Appointment (appointment number) : ");
        appointNum = input.nextInt(); // make decision to approve which appointment
        input.nextLine();

        do{
            System.out.println("0. Declined");
            System.out.println("1. Approved");
            System.out.print("Decision : ");
            decision = input.nextInt(); // decide whether the appointment is approved or not
            input.nextLine();
    
        } while(decision != 0 && decision != 1); //since status has only 2 type, so staff need to key in again decision if invalid
        
        for(Appointment app : appointment) {
            if(app.getId() == appointNum) { 
                app.appointmentApproval(decision); //change the appointment status when found the target appointment
                break;
            }
        }

        modifyAppointmentFile(); // save all the change to appointment file in this function
        input.close();
    }

    public void modifyAppointmentFile() throws Exception{
        FileWriter outputFile = new FileWriter("appointmentData.txt");

        for(Appointment app : appointment) {
            outputFile.write(app.toString() + ", " + app.getOwnerMatric() + "\n"); // save all the change into appointmentData file
        }

        outputFile.close();
    }
}