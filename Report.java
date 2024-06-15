import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.lang.reflect.*;

public class Report {
    private Student student;
    private Hostel hostelRegistred;
    private ArrayList<Appointment> appointmentsMade = new ArrayList<>();
    private ArrayList<Vehicle> vehiclesRegistered = new ArrayList<>();

    public Report(Student std) throws Exception{
        student = std;
        readHostelReport();
        readAppointmentReport();
        readVehicleReport();
    }

    public boolean isEmpty(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        
        // Check if there are no fields, no methods, and only the default constructor
        return fields.length == 0 && methods.length == 0 && constructors.length == 1 && constructors[0].getParameterCount() == 0;
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

            if(matric_single[0].equals(student.getMatricNum())) {
                hostelRegistred = new Single_Room(appointmentInfo_single[0], 
                                                  Integer.parseInt(appointmentInfo_single[1]), 
                                                  matric_single, 
                                                  Integer.parseInt(appointmentInfo_single[2]));
            }
            else if(matric_double[0].equals(student.getMatricNum()) || matric_double[1].equals(student.getMatricNum())) {
                int[] availableStatus = {Integer.parseInt(appointmentInfo_double[2]), Integer.parseInt(appointmentInfo_double[3])};
                hostelRegistred = new Double_Room(appointmentInfo_double[0], 
                                                  Integer.parseInt(appointmentInfo_double[1]), 
                                                  matric_double, 
                                                  availableStatus);
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

            //read all the data from apointmentData file and assign those to an array list
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

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] vehicleInfo = line.split(", "); // saperate this line into few string by using "," as break point
            int i = 0;

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

    public void generateReport() throws Exception{

        System.out.println("Name : " + student.getName());
        System.out.println("Student ID : " + student.getStudentID());
        System.out.println("Matric No : " + student.getMatricNum());
        System.out.println("Contact Number : " + student.getContact());
        System.out.println("E-mail : " + student.getEmail());

        if(hostelRegistred.getMatric(0) != null || hostelRegistred.getMatric(1) != null) {
            System.out.println("\nHostel Registered : ");
            System.out.println("\tBlock : " + hostelRegistred.getBlock());
            System.out.println("\tRoomNum : " + hostelRegistred.getRoomNum());
        }

        if(!appointmentsMade.isEmpty()) {
            System.out.println("\nAppointment Made : ");
            for(int i = 0; i < appointmentsMade.size(); i++) {
                System.out.println("\t" + appointmentsMade.get(i).toString());
            }
        }

        if(!vehiclesRegistered.isEmpty()) {
            System.out.println("\nVehicle Registered : ");
            for(int i = 0; i < vehiclesRegistered.size(); i++) {
                System.out.println("\t" + vehiclesRegistered.get(i).toString());
            }
        }
        
    }
}
