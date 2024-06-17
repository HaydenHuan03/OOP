import java.util.Scanner;

public class mainbody {
    private static Scanner inp = new Scanner(System.in);
    private static int choice;

    public static void main(String[] args) throws Exception{
        User user;
        String name, password;
        int choice, character;
        boolean authenticate = false, run = true;

        while (run) {
            //main menu
            clearScreen(); //initial the screen
            choice = displayMenu(0);
            clearScreen();

            //login & register
            if(choice == 1) { // login
                character = displayMenu(1);
                clearScreen();

                if(character > 0 && character < 3) {
                    //user enter login information to authenticate himself
                    System.out.println("Please enter your name (Username)");
                    System.out.print(  "> ");
                    name = inp.nextLine();
                    System.out.println();

                    System.out.println("Please enter your password");
                    System.out.print(  "> ");
                    password = inp.nextLine();
                    System.out.println();
                    clearScreen();

                    if(character == 1) {
                        user = new Student(name, "", "", password, "");
                    }
                    else {
                        user = new Staff(name, "", "", password);
                    }

                    authenticate = user.login(); //check the login successful or not
                    waitScreen();
                    clearScreen();

                    if(authenticate) { //successful
                        if(user.getAdminPrivileges() == 0) { //check the user is staff or student
                            displayStudent(user); //display student's site
                            clearScreen();
                        }
                        else {
                            displayStaff(user); //display staff's site
                            clearScreen();
                        }

                        System.out.println("Thank you, " + user.getName() + " !");
                        System.out.println("Logout successfully");
                        waitScreen();
                        authenticate = false; // drop the authenticate if user logout
                    }
                }
            }
            else if(choice == 2) { // register
                boolean registerStatus = false;
                character = displayMenu(2);
                clearScreen();

                while(!registerStatus && (character > 0 && character < 3)) {
                    String[] registerInfo = userRegister();

                    if(character == 1) { // student
                        // student need to enter his/her matric number
                        System.out.println("Enter your matric number");
                        System.out.print(  "> ");
                        registerInfo[4] = inp.nextLine();

                        user = new Student( registerInfo[0],
                                            registerInfo[1],
                                            registerInfo[2],
                                            registerInfo[3],
                                            registerInfo[4]);

                    }
                    else { // staff
                        user = new Staff(   registerInfo[0],
                                            registerInfo[1],
                                            registerInfo[2],
                                            registerInfo[3]);
                    }

                    clearScreen();
                    registerStatus = user.register(); //check register is success or not
                    waitScreen();
                }
            }
            else {run = false; break;} // stop the program
        }
    }

    public static void clearScreen() { //use to clear the screen
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void waitScreen() { //use to stop the screen before user enter anykey
        System.out.println("Press enter to continue");
        inp.nextLine();
    }

    public static String[] userRegister() { // use for user to enter the detail information to register an account
        String[] registerInfo = new String[5];

        System.out.println("Please enter user name : ");
        System.out.print(  "> ");
        registerInfo[0] = inp.nextLine();

        System.out.println("\nPlease enter your email (abc123@graduate.utm.my): ");
        System.out.print(  "> ");
        registerInfo[1] = inp.nextLine();

        System.out.println("\nPlease enter your contact number (0127378800): ");
        System.out.print(  "> ");
        registerInfo[2] = inp.nextLine();

        System.out.println("\nPlease enter your password : ");
        System.out.print(  "> ");
        registerInfo[3] = inp.nextLine();

        return registerInfo;
    }

    public static int displayMenu(int menuNum) { // decide which menu to display and what decision / action user did
        switch (menuNum) {
            case 0:
                return displayAuthenticateMenu();

            case 1:
                return displayLoginMenu();

            case 2:
                return displayRegisterMenu();

            case 3:
                return displayMainMenu();

            case 4:
                return displayStudentHostel();

            case 5:
                return displayStaffHostel();

            case 6:
                return displayStudentAppointment();

            case 7:
                return displayStaffAppointment();

            case 8:
                return displayCourtBooking();

            case 9:
                return displayStudentVehicle();

            case 10:
                return displayStaffVehicle();

            case 11:
                return displayStudentReport();

            case 12:
                return displayStaffVehicle();

            default:
                return -1;
        }
    }

    public static int displayAuthenticateMenu() {
        System.out.println("Welcome to KTDI Management System");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayLoginMenu() {
        System.out.println("Login as :");
        System.out.println("1. Student");
        System.out.println("2. Staff");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayRegisterMenu() {
        System.out.println("Register as :");
        System.out.println("1. Student");
        System.out.println("2. Staff");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayMainMenu() {
        System.out.println("Main Menu :");
        System.out.println("1. Hostel Registration");
        System.out.println("2. Appointment");
        System.out.println("3. Court Booking");
        System.out.println("4. Vehicle");
        System.out.println("5. Report");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStudentHostel() {
        System.out.println("Hostel Menu :");
        System.out.println("1. Check Available Room");
        System.out.println("2. Register a Room");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStaffHostel() {
        System.out.println("Hostel Menu :");
        System.out.println("1. Check Rooms Detail");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStudentAppointment() {
        System.out.println("Appointment Menu");
        System.out.println("1. Make Appointment");
        System.out.println("2. Display Appointment");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        int choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStaffAppointment() {
        System.out.println("Appointment Menu");
        System.out.println("1. Check Appointment");
        System.out.println("2. Approve Appointment");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayCourtBooking() {
        System.out.println("Court Booking Menu");
        System.out.println("1. Check Available Timeslot");
        System.out.println("2. Booking Court");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStudentVehicle() {
        System.out.println("Vehicle Menu");
        System.out.println("1. Vehicle Register");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStaffVehicle() {
        System.out.println("Vehicle Menu");
        System.out.println("1. Check Registered Vehicle");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStudentReport() {
        System.out.println("Report Menu");
        System.out.println("1. Check Report");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static int displayStaffReport() {
        System.out.println("Report Menu");
        System.out.println("1. Check Student's Report");
        System.out.println("Other to exit");
        System.out.print(  "> ");
        choice = inp.nextInt();
        inp.nextLine();

        return choice;
    }

    public static void displayStudent(User user) throws Exception {
        //set the user to student
        Student student = new Student(user.getName(), user.getEmail(), user.getContact(), user.getPassword(), "");
        student.findStud();

        boolean run = true;

        while(run) {
            choice = displayMenu(3); //display main menu
            int index = choice;
            clearScreen();

            if(index > 0 && index < 6){
                switch (index) {
                    case 1: // hostel registration
                        do {
                            choice = displayMenu(4);
                            clearScreen();
                            if(choice == 1) { //check available room
                                student.checkAvailable(inp);
                                waitScreen();
                                clearScreen();
                            }
                            else if(choice == 2) { // register a room
                                student.registerHostel(inp);
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1 || choice == 2);
                        break;

                    case 2: //appointment
                        do {
                            choice = displayMenu(6);
                            clearScreen();
                            if(choice == 1) { // make appointment
                                student.makeAppointment(inp);
                                clearScreen();
                            }
                            else if(choice == 2) { // display appointment
                                student.displayAppointment();
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1 || choice == 2);
                        break;

                    case 3: // court booking
                        do {
                            choice = displayMenu(8);
                            clearScreen();
                            if(choice == 1) { // check available timeslot
                                user.prntAvailableCourt();
                                waitScreen();
                                clearScreen();
                            }
                            else if(choice == 2) { // booking court
                                user.bookingCourt(inp);
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1 || choice == 2);
                        break;

                    case 4: // vehicle
                        do {
                            choice = displayMenu(9);
                            clearScreen();
                            if(choice == 1) { // vehicle register
                                student.vehicleRegister(inp);
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1);
                        break;

                    case 5: // report
                        do {
                            choice = displayMenu(11);
                            clearScreen();
                            if(choice == 1) { // check report
                                Report report = new Report(student);
                                report.generateReport();
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1);
                        break;

                    default:
                        index = 0;
                        run = false;
                        break;
                }
            }
            else {run = false; break;}
        }
    }

    public static void displayStaff(User user) throws Exception {
        //set the user to staff
        Staff staff = new Staff(user.getName(), user.getEmail(), user.getContact(), user.getPassword());
        staff.findStaf();
        //read the file the initialize the data
        staff.readAppointment();
        staff.readVehicle();

        boolean run = true;

        while(run) {
            choice = displayMenu(3); //display main menu
            int index = choice;
            clearScreen();

            if(index > 0 && index < 6){
                switch (index) {
                    case 1: // hostel registration
                        do {
                            choice = displayMenu(5);
                            clearScreen();
                            if(choice == 1) { // check rooms details
                                staff.checkAllRoom(inp);
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1);
                        break;

                    case 2: // appointment
                        do {
                            choice = displayMenu(7);
                            clearScreen();
                            if(choice == 1) { //check appointment
                                staff.checkAppointment();
                                waitScreen();
                                clearScreen();
                            }
                            else if(choice == 2) { //approve appointment
                                staff.approveAppointment(inp);
                                clearScreen();
                            }
                            else break;

                        } while(choice == 1 || choice == 2);
                        break;

                    case 3: // court booking
                        do {
                            choice = displayMenu(8);
                            clearScreen();
                            if(choice == 1) { //check available timeslot
                                user.prntAvailableCourt();
                                waitScreen();
                                clearScreen();
                            }
                            else if(choice == 2) { //booking court
                                user.bookingCourt(inp);
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1 || choice == 2);
                        break;

                    case 4: //vehicle
                        do {
                            choice = displayMenu(10);
                            clearScreen();
                            if(choice == 1) { //check registered vehicle
                                staff.printRegisteredVehicle();
                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1);
                        break;

                    case 5: // report
                        do {
                            choice = displayMenu(11);
                            clearScreen();
                            if(choice == 1) { //check student's report
                                System.out.println("Enter student matric you want to check");
                                System.out.print(  "> ");
                                String stdMatric = inp.nextLine();

                                //set the student
                                Student checkedStudent = new Student("", "", "", "", stdMatric);
                                checkedStudent.findStud();

                                //print student's report
                                Report report = new Report(checkedStudent);
                                report.generateReport();

                                waitScreen();
                                clearScreen();
                            }
                            else break;

                        }while(choice == 1);
                        break;

                    default: // stop the method (logout)
                        index = 0;
                        run = false;
                        break;
                }
            }
            else {run = false; break;} // stop the method (logout)
        }
    }
}