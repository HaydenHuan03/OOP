import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Report {
    private Student student;
    private Hostel hostelRegistred;
    private ArrayList<Appointment> appointmentsMade = new ArrayList<>();
    private ArrayList<Vehicle> vehiclesRegistered = new ArrayList<>();

    public Report(Student std) throws Exception{
        student = std;

        //read the data related to this student form file
        readHostelReport(); 
        readAppointmentReport();
        readVehicleReport();
    }

    public void readHostelReport() throws Exception{
        Scanner inputFile_single = new Scanner(new File("singleRoom.txt"));
        Scanner inputFile_double = new Scanner(new File("doubleRoom.txt"));

        while(inputFile_single.hasNextLine() || inputFile_double.hasNextLine()) {
            String[] line = {inputFile_single.nextLine(), inputFile_double.nextLine()};
            String[] appointmentInfo_single = line[0].split(", ");
            String[] appointmentInfo_double = line[1].split(", ");

            String[] matric_single = {appointmentInfo_single[3]};
            String[] matric_double = {appointmentInfo_double[4], appointmentInfo_double[5]};

            //check the matric number to make sure that the student has registered a room or not
            if(matric_single[0].equals(student.getMatricNum())) { //check the single room file first
                //if there is room registered by this student, set the whole related data to the hostelRegistered
                hostelRegistred = new Single_Room(appointmentInfo_single[0], 
                                                  Integer.parseInt(appointmentInfo_single[1]), 
                                                  matric_single, 
                                                  Integer.parseInt(appointmentInfo_single[2]));
                inputFile_single.close();
                inputFile_double.close();
                return;
            }
            else if(matric_double[0].equals(student.getMatricNum()) || matric_double[1].equals(student.getMatricNum())) {//check the double room file
                int[] availableStatus = {Integer.parseInt(appointmentInfo_double[2]), Integer.parseInt(appointmentInfo_double[3])};
                hostelRegistred = new Double_Room(appointmentInfo_double[0], 
                                                  Integer.parseInt(appointmentInfo_double[1]), 
                                                  matric_double, 
                                                  availableStatus);
                inputFile_single.close();
                inputFile_double.close();
                return;
            }
            else {
                String[] emptyArray = {"", ""};
                hostelRegistred = new Single_Room("", 0, emptyArray, 0);
            }
        }
        inputFile_single.close();
        inputFile_double.close();
    }

    public void readAppointmentReport() throws Exception{
        Scanner inputFile  = new Scanner(new File("appointmentData.txt"));
        int i = 0; // use i to locate the appointment array list

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] appointmentInfo = line.split(", "); // saperate this line into few string by using "," as break point

            //read the data related to this student from apointmentData file and assign those to an array list
            //check the matric number to make sure which appointment is made by this student
            if(appointmentInfo[5].equals(student.getMatricNum())) {
                appointmentsMade.add(new Appointment(1, appointmentInfo[2], appointmentInfo[3], appointmentInfo[5]));
                appointmentsMade.get(i).setId(Integer.parseInt(appointmentInfo[0]));
                appointmentsMade.get(i).setType(appointmentInfo[1]);
                appointmentsMade.get(i).setStatus(appointmentInfo[4]);
                i++;
            }
        }

        inputFile.close();
    }

    public void readVehicleReport() throws Exception{
        Scanner inputFile = new Scanner(new File("vehicleInfo.txt"));
        int i = 0;

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] vehicleInfo = line.split(", "); // saperate this line into few string by using "," as break point

            //read the data related to this student from vehicleInfo file and assign those to an array list
            //check the matric number to make sure which vehicle is reistred by this student
            if(vehicleInfo[0].equals(student.getMatricNum())) {
                if(vehicleInfo[2].equals("Car")) {
                    vehiclesRegistered.add(new Car(vehicleInfo[0], vehicleInfo[1]));
                }
                else {
                    vehiclesRegistered.add(new Motorbike(vehicleInfo[0], vehicleInfo[1]));
                }   
    
                vehiclesRegistered.get(i).setModel(vehicleInfo[3]);
                vehiclesRegistered.get(i).setBrand(vehicleInfo[4]);
                vehiclesRegistered.get(i).setPlateNumber(vehicleInfo[5]);
                vehiclesRegistered.get(i).setLicense(vehicleInfo[6]);
                i++;
            }
        }
        inputFile.close();
    }

    public void generateReport() throws Exception{ // print all the related data to user

        //student data
        if(!student.getName().isEmpty()) { //check student is exist or not
            System.out.println("Name : " + student.getName());
            System.out.println("Student ID : " + student.getStudentID());
            System.out.println("Matric No : " + student.getMatricNum());
            System.out.println("Contact Number : " + student.getContact());
            System.out.println("E-mail : " + student.getEmail());
        }
        else { //if no exist
            System.out.println("No such student !");
            return;
        }

        //student hostel data
        if(!hostelRegistred.getMatric(0).equals("") || !hostelRegistred.getMatric(1).isEmpty()) { //check the variable is empty or not
            System.out.println("\nHostel Registered : ");
            System.out.println("\tBlock : " + hostelRegistred.getBlock());
            System.out.println("\tRoomNum : " + hostelRegistred.getRoomNum());
        }

        //student appointment data
        if(!appointmentsMade.isEmpty()) { // check the arraylist is empty or not
            System.out.println("\nAppointment Made : ");
            for(int i = 0; i < appointmentsMade.size(); i++) {
                System.out.println("\t" + appointmentsMade.get(i).toString());
            }
        }

        //student vehicle data
        if(!vehiclesRegistered.isEmpty()) { // check the arraylist is empty or not
            System.out.println("\nVehicle Registered : ");
            for(int i = 0; i < vehiclesRegistered.size(); i++) {
                System.out.println("\t" + vehiclesRegistered.get(i).toString());
            }
        }
        
    }
}
