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

    public int getAdminPrivileges() {
        return (adminPrivileges ? 1 : 0);
    }

    public boolean login() throws Exception{
        // input file to read all the user data from file
        File file = new File("userData.txt");
        Scanner inputFile = new Scanner(file);
        

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            // separate the student and staff data by checking the adminPrivileges
            // if the name and password is correct, return true which mean successful login
            if(name.equals(userInfo[1]) && password.equals(userInfo[4]) && getAdminPrivileges() == Integer.parseInt(userInfo[5])) { 
                inputFile.close();
                email = userInfo[2];
                contact = userInfo[3];
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
            if((name.equals(userInfo[1])) || (email.equals(userInfo[2])) || (contact.equals(userInfo[3]))) { 
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
    
    public Student(String name, String email, String contact, String password, String matricNum) throws Exception{
        super(name, email, contact, password, false);
        this.matricNum = matricNum;
        calculateStudentID();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMatricNum() {
        return matricNum;
    }

    public void calculateStudentID() throws Exception{
        Scanner inputFile = new Scanner(new File("userData.txt"));
        int highest = 0;

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            // find the highest student id among current user 
            // use Integer.valueOf to convert String to integer
            // check the id number to avoid to assign an staff id to student
            if((Integer.parseInt(userInfo[0]) > highest) && (Integer.parseInt(userInfo[0])/9000000 < 1)) { 
                highest = Integer.parseInt(userInfo[0]);
            }
        }
        studentID = highest + 1; //assign the incremented highest id to studentId

        inputFile.close();
    }

    public void findStud() throws Exception{ // function to complete the private data initialization
        Scanner inputFile = new Scanner(new File("userData.txt"));

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            if(userInfo[1].equals(super.getName())) { // check the name is same with user key in
                matricNum = userInfo[6];
                studentID = Integer.valueOf(userInfo[0]);
                inputFile.close();
                break;
            }
        }

        return;
    }

    @Override
    public boolean login() throws Exception{
        if(super.login()) {
            System.out.println("Welcome, " + super.getName());
            findStud();
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
                String[] userInfo = line.split(",");// saperate this line into few string by using "," as break point
    
                if(matricNum.equals(userInfo[6])) { // compare the key in matric number with registered matric number
                    System.out.println("User Existed ! Please retype your information");
                    inputFile.close();
                    return false; // if same, return false since the user already exist
                }
            }

            // open output file to assign user data to userData.txt
            FileWriter outputFile = new FileWriter("userData.txt", true); 
            outputFile.write(studentID + "," +
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
            System.out.println("User Existed ! Please retype your information");
            return false;
        }
    }

    //*Appointment
    public void makeAppointment(Scanner input) throws Exception{
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
            if(matricNum.equals(appointmentInfo[5])) { 
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
    
    public Staff(String name, String email, String contact, String password) throws Exception{
        super(name, email, contact, password, true);
        appointment = new ArrayList<>();
        calculateStaffID();
    }
    
    public int getStaffID() {
        return staffID;
    }

    public void calculateStaffID() throws Exception{
        Scanner inputFile = new Scanner(new File("userData.txt"));
        int highest = 0;

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            // find the highest staff id among current user 
            // use Integer.valueOf to convert String to integer
            if(Integer.parseInt(userInfo[0]) > highest) { 
                highest = Integer.parseInt(userInfo[0]);
            }
        }
        staffID = highest + 1; //assign the incremented highest id to staffId

        inputFile.close();
    }

    public void findStaf() throws Exception{ // function to complete the private data initialization
        Scanner inputFile = new Scanner(new File("userData.txt"));

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] userInfo = line.split(","); // saperate this line into few string by using "," as break point

            if(userInfo[1].equals(super.getName())) { // check the name is same with user key in
                staffID = Integer.valueOf(userInfo[0]);
                inputFile.close();
                break;
            }
        }

        return;
    }

    @Override
    public boolean login() throws Exception{
        if(super.login()) {
            System.out.println("Welcome, " + super.getName());
            findStaf();
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
            outputFile.write(staffID + "," +
                             getName() + "," +
                             getEmail() + "," +
                             getContact() + "," +
                             getPassword() + "," +
                             getAdminPrivileges() + "," + "-\n");

            outputFile.close();

            System.out.println("Congrats, your registration successful !");
            return true;
        }
        else {
            System.out.println("User Existed ! Please retype your information");
            return false;
        }
    }

    //*Apointment
    public void readAppointment() throws Exception{
        Scanner inputFile  = new Scanner(new File("appointmentData.txt"));
        int i = 0; // use i to locate the appointment array list

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] appointmentInfo = line.split(", "); // saperate this line into few string by using "," as break point

            //read all the data from apointmentData file and assign those to an array list
            appointment.add(new Appointment(1, appointmentInfo[2], appointmentInfo[3], appointmentInfo[5]));
            appointment.get(i).setId(Integer.parseInt(appointmentInfo[0]));
            appointment.get(i).setType(appointmentInfo[1]);
            appointment.get(i).setStatus(appointmentInfo[4]);
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

    public void approveAppointment(Scanner input) throws Exception{
        int appointNum, decision; // decision decide the status of appointment

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
    }

    public void modifyAppointmentFile() throws Exception{
        FileWriter outputFile = new FileWriter("appointmentData.txt");

        for(Appointment app : appointment) {
            outputFile.write(app.toString() + ", " + app.getOwnerMatric() + "\n"); // save all the change into appointmentData file
        }

        outputFile.close();
    }
}