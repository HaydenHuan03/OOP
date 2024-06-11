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
            choice = displayMenu(0);

            //login & register
            if(choice == 1) {
                character = displayMenu(1);
                
                if(character > 0 && character < 3) {
                    System.out.println("Please enter your name");
                    System.out.print(  "> ");
                    name = inp.nextLine();
                    System.out.println();
                
                    System.out.println("Please enter your password");
                    System.out.print(  "> ");
                    password = inp.nextLine();
                    System.out.println();

                    if(character == 1) {
                        user = new Student(name, "", "", password, "");
                    }
                    else {
                        user = new Staff(name, "", "", password);
                    }

                    authenticate = user.login();

                    if(authenticate) {
                        if(user.getAdminPrivileges() == 0) {
                            displayStudent(user);
                        }
                        else {
                            displayStaff(user);
                        }

                        System.out.println("Thank you, " + user.getName() + " !");
                        System.out.println("Logout successfully");
                        authenticate = false;
                    }
                }
            }
            else if(choice == 2) {
                boolean registerStatus = false;
                character = displayMenu(2);

                while(!registerStatus && (character > 0 && character < 3)) {
                    String[] registerInfo = userRegister();

                    if(character == 1) {
                        System.out.println("Enter your matric number");
                        System.out.print(  "> ");
                        registerInfo[4] = inp.nextLine();

                        user = new Student( registerInfo[0],
                                            registerInfo[1],
                                            registerInfo[2],
                                            registerInfo[3],
                                            registerInfo[4]);

                    }
                    else {
                        user = new Staff(   registerInfo[0],
                                            registerInfo[1],
                                            registerInfo[2],
                                            registerInfo[3]);
                    }

                    registerStatus = user.register();
                }
            }
            else {run = false; break;}
        }
    }

    public static String[] userRegister() {
        String[] registerInfo = new String[5];
 
        System.out.println("Please enter user name : ");
        System.out.print(  "> ");
        registerInfo[0] = inp.nextLine();

        System.out.println("Please enter your email : ");
        System.out.print(  "> ");
        registerInfo[1] = inp.nextLine();

        System.out.println("Please enter your contact number : ");
        System.out.print(  "> ");
        registerInfo[2] = inp.nextLine();

        System.out.println("Please enter your password : ");
        System.out.print(  "> ");
        registerInfo[3] = inp.nextLine();

        return registerInfo;
    }

    public static int displayMenu(int menuNum) {
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
                return displayStudentHostelRegistration();

            case 5:
                return displayStaffHostelRegistration();

            case 6:
                return displayStudentAppointment();

            case 7:
                return displayStaffAppointment();
        
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

    public static int displayStudentHostelRegistration() {
        return choice;
    }

    public static int displayStaffHostelRegistration() {
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

    public static void displayStudent(User user) throws Exception {
        Student student = new Student(user.getName(), user.getEmail(), user.getContact(), user.getPassword(), "");
        student.findStud();

        boolean run = true;

        while(run) {
            choice = displayMenu(3);
            int index = choice;

            if(index > 0 && index < 6){
                switch (index) {
                    case 1:
                        break;

                    case 2:
                        do {
                            choice = displayMenu(6);
                            if(choice == 1) {
                                student.makeAppointment(inp);
                            }
                            else if(choice == 2) {
                                student.displayAppointment();
                            }
                            else break;

                        }while(choice == 1 || choice == 2);
                        break;

                    case 3:
                        break;

                    case 4:
                        break;
                
                    default:
                        break;
                }
            }
        }
    }

    public static void displayStaff(User user) throws Exception {
        Staff staff = new Staff(user.getName(), user.getEmail(), user.getContact(), user.getPassword());
        staff.findStaf();
        staff.readAppointment();

        boolean run = true;

        while(run) {
            choice = displayMenu(3);
            int index = choice;

            if(index > 0 && index < 6){
                switch (index) {
                    case 1:
                        break;

                    case 2:
                        do {
                            choice = displayMenu(7);
                            if(choice == 1) {
                                staff.checkAppointment();
                            }
                            else if(choice == 2) {
                                staff.approveAppointment(inp);
                            }
                            else break;

                        } while(choice == 1 || choice == 2);
                        break;

                    case 3:
                        break;

                    case 4:
                        break;
                
                    default:
                        run = false;
                        break;
                }
            }
        }
    }
}
