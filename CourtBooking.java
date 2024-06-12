import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CourtBooking {
    private String courtNumber;
    private String timeSlot;
    private User Booker;

    //Constructor
    public CourtBooking(User Booker){this.Booker = Booker;}

    //Mutator
    public void setCourt(String courtNumber){this.courtNumber = courtNumber;}
    public void setTime_slot(String timeSlot){this.timeSlot = timeSlot;}

    //Normal function
    public boolean bookingCourt(){
        String[][] court_Info = new String[20][3] ; //store court info

        try (BufferedReader reader = new BufferedReader(new FileReader("courtInfo.txt"))) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 3; j++) {
                    court_Info[i][j] = reader.readLine().split(" ")[j];
                }
            }
        } catch (IOException e) {
            System.out.println("File is not created or cannot be opened.\n");
            return false;
        }

        // Compare the status with the user input to check whether the court is available or not
        for (int i = 0; i < 20; i++) {
            if (timeSlot.equals(court_Info[i][0]) && courtNumber.equals(court_Info[i][1]) && "y".equals(court_Info[i][2])) {
                System.out.println("Hi, Mr/Ms " + Booker.getName() + "\n");//"\nSuccessfully booked.\n");
                System.out.println("You have successfully booked the court");

                // Update the status in the courtInfo array and the file
                court_Info[i][2] = "n";
                UpdateAvailableTime("courtInfo.txt", court_Info);
                return true;
            }
        }

        System.out.println("I'm sorry Mr/Ms " + Booker.getName() + "\n");
        System.out.println("Court not available. Please select again...\n");
        return false;
    }

    public void UpdateAvailableTime(String fileName, String[][] courtInfo) {
        // Write the updated information back to the file
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < 20; i++) {
                if (courtInfo[i][0].equals(timeSlot) && courtInfo[i][1].equals(courtNumber)) {
                    // Update the court info based on the user input
                    // if booked, the court status will change to not available (n)
                    writer.write(courtInfo[i][0] + " " + courtInfo[i][1] + " n\n");
                } else {
                    // if not booked, just remain all the data and write into the same file
                    writer.write(courtInfo[i][0] + " " + courtInfo[i][1] + " " + courtInfo[i][2] + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating the file...\n");
        }
    }

    public void printAvailableTimeSlot(){
        String[][] display = new String[20][3];

        //read file into an array name display
        try (BufferedReader reader = new BufferedReader(new FileReader("courtInfo.txt"))) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 3; j++) {
                    display[i][j] = reader.readLine().split(" ")[j];
                }
            }
        } catch (IOException e) {
            System.out.println("File is not created or cannot be opened.\n");
            return ;
        }

        // Display available timeslot
        System.out.println("Time Slot" + "\t" +"Court Number");
        System.out.println("---------------------------");

        for (int i = 0; i < 20; i++) {
            // Compare the status of the court (booked or not)
            // it will display the timeslot and court number if they are not booked yet
            if ("y".equals(display[i][2])) {
                System.out.println(display[i][0] + "\t" + display[i][1]);
            }
        }

        System.out.println("---------------------------\n");
    }
}

