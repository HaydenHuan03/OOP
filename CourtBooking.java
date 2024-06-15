import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class CourtBooking {
    private String courtNumber;
    private String timeSlot;
    private String[][] court_info;

    //Normal function
    public void readCourt_info() {
        court_info = new String[20][3] ; //store court info

        try (BufferedReader reader = new BufferedReader(new FileReader("courtInfo.txt"))) {
            for (int i = 0; i < court_info.length; i++) {
                court_info[i] = reader.readLine().split(" "); // store file info into court_info
            }
        } catch (IOException e) {
            System.out.println("File is not created or cannot be opened.\n");
            return;
        }
    }

    public boolean bookingCourt(Scanner input) throws Exception{
        readCourt_info();// Read the info to court_info variable

        System.out.print("Please enter time slot (example: 0800-1000): ");
        timeSlot = input.nextLine();
        System.out.print("Please enter court number that you selected (accept number only): ");
        courtNumber = input.nextLine();

        // Compare the status with the user input to check whether the court is available or not
        for (int i = 0; i < 20; i++) {
            if (timeSlot.equals(court_info[i][0]) && courtNumber.equals(court_info[i][1]) && "y".equals(court_info[i][2])) {
                // Update the status in the courtInfo array and the file
                court_info[i][2] = "n";
                UpdateAvailableTime();
                return true;
            }
        }

        return false;
    }

    public void UpdateAvailableTime() {
        // Write the updated information back to the file
        try (FileWriter writer = new FileWriter("courtInfo.txt")) {
            for (int i = 0; i < court_info.length; i++) {
                // Update the court info based on the user input
                writer.write(court_info[i][0] + " " + court_info[i][1] + " " + court_info[i][2] + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating the file...\n");
        }
    }

    public void printAvailableTimeSlot(){
        readCourt_info(); // Read the info to court_info variable
        // Display available timeslot
        System.out.println("Time Slot" + "\t" +"Court Number");
        System.out.println("---------------------------");

        for (int i = 0; i < 20; i++) {
            // Compare the status of the court (booked or not)
            // it will display the timeslot and court number if they are not booked yet
            if ("y".equals(court_info[i][2])) {
                System.out.println(court_info[i][0] + "\t" + court_info[i][1]);
            }
        }

        System.out.println("---------------------------\n");
    }
}