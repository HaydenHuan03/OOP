import java.io.*;
import java.util.Scanner;

public class HostelRegistration {
    private String name ;
    private String block ;
    private int roomNumber ;

    //Mutator
    public void setName(String name){this.name = name;}
    public void setBlock(String block){this.block = block;}
    public void setRoomNum(int roomNumber){this.roomNumber = roomNumber;}

    //Accessor
    public String getName(){return name;}
    public String getBlock(){return block;}

    //Store room data from an input file
    public void OpenInputFile(String fileName, int[][] Room) {
        try (Scanner inp = new Scanner(new File(fileName))) {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 3; j++) {
                    if (inp.hasNextInt()) {
                        Room[i][j] = inp.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error\n");
            System.exit(0);
        }
    }

    //User has successfully register a room and set the room to unavailable
    public void OpenOutputFile(String fileName, int[][] Room) {
        try (PrintWriter op = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < 30; i++) {
                if (roomNumber == Room[i][0]) {
                    if (Room[i][1] == 1) {
                        op.println(Room[i][0] + "\t" + 0 + "\t" + Room[i][2]);
                    } else {
                        op.println(Room[i][0] + "\t" + Room[i][1] + "\t" + 0);
                    }
                } else {
                    op.println(Room[i][0] + "\t" + Room[i][1] + "\t" + Room[i][2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Check if the user has previous register room or the room is not available
    public boolean Register(String fileName, int[][] Room) {
        String userRoomFile = name + "_Room.txt";
        String isEmpty = "";

        //Check if the user has previous register room
        try (Scanner inp = new Scanner(new File(userRoomFile))) {
            if (inp.hasNext()) {
                isEmpty = inp.next();
            }
        } catch (FileNotFoundException e) {
            // File does not exist; treat as empty
        }


        for (int i = 0; i < 30; i++) {
            if (Room[i][0] == roomNumber) {
                if (Room[i][1] == 1 || Room[i][2] == 1) {
                    if (!isEmpty.isEmpty()) {
                        //User has register a room before
                        System.out.println("Already register room !");
                        return false;
                    } else {
                        //User not register a room before and room available
                        OpenOutputFile(fileName, Room);
                        return true;
                    }
                }
            }
        }

        //Room not available, not have the room number
        return false;
    }

    //Save user's hostel data in a new file
    public void saveRegister(String type) {
        String fileName = name + "_Room.txt";
        try (PrintWriter UserOp = new PrintWriter(new FileWriter(fileName, true))) {
            UserOp.println(type + " " + block + " " + roomNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheckAvailableRoom(String fileName) {
        int[][] Room = new int[30][3];
        OpenInputFile(fileName, Room);

        System.out.println("Available Room of " + getBlock());
        System.out.println("=====================");
        for (int i = 0; i < 30; i++) {
            if (Room[i][1] == 1 || Room[i][2] == 1) {
                System.out.println(Room[i][0]);
            }
        }
        System.out.println();
    }
}