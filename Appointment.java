import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

class Appointment {
    private String type;
    private String time;
    private String date;
    private String status;
    private int id;
    private String ownerMatric; //matric number for who make this appointment

    public Appointment(int type, String date, String time, String ownerMatric) throws Exception{
        this.type = typeConversion(type); // convert integer type to String and assign to this.type
        this.id = calculateAppointmentID(); // use highest appointment id + 1 as new appointment id 
        this.time = time;
        this.date = date;
        this.ownerMatric = ownerMatric;
        status = "Proccessing"; // all the appointment made should initial the status to "proccessing" since the admin haven't do anything
    }

    public int getId() {
        return id;
    }

    public String getOwnerMatric() {
        return ownerMatric;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setOwnerMatric(String ownerMatric) {
        this.ownerMatric = ownerMatric;
    }

    public int calculateAppointmentID() throws Exception{ //use the current highest appointment id to generate new appointment
        Scanner inputFile = new Scanner(new File("appointmentData.txt"));
        int highest = 0;

        while(inputFile.hasNextLine()) {
            String line = inputFile.nextLine(); // read a whole line in file
            String[] appointmentInfo = line.split(", "); // saperate this line into few string by using ", " as break point

            // find the highest appointment id among current appointment
            // use Integer.valueOf to convert String to integer
            if(Integer.valueOf(appointmentInfo[0]) > highest) {
                highest = Integer.valueOf(appointmentInfo[0]);
            }
        }

        inputFile.close();
        return highest + 1; //return incremented highest as new appointment id
    }

    public String typeConversion(int type) { // convert integer type into string
        if(type == 1)
        {
            return "Administration Appointment";
        }
        else if(type == 2)
        {
            return "Faculty Appointment";
        }
        else
        {
            return "Staff Appointment";
        }
    }

    public void addAppointment() throws Exception{ //write the new appointment made into appointmentData file
        FileWriter outputFile = new FileWriter("appointmentData.txt", true);

        outputFile.write(toString() + ownerMatric + "\n");

        outputFile.close();
    }

    public void appointmentApproval(int status) { // convert integer status to string and assign it into this.status
        if(status == 1) {
            this.status = "Approved";
        } 
        else {
            this.status = "Declined";
        }
    }

    public String toString() { // in order to simpifly the appointment output display
        return (id + ", " + type + ", " + date + ", " + time + ", " + status);
    }
}