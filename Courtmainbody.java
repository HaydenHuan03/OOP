import java.util.Scanner;

public class Courtmainbody {
    public static void main(String[] args){
        String time_slot, courtNo;
        boolean available, validBook = true;
        char bookOption;
        Scanner input = new Scanner(System.in);

        while(validBook == true){
            System.out.println("Booking Court");
            System.out.println("-------------");
            System.out.println("1       : Booking Court");
            System.out.println("Any key : Back to Menu");
            System.out.print("> ");
            bookOption = input.next().charAt(0);
            input.nextLine();

            switch(bookOption){
                case '1':{
                    do{
                        CourtBooking court = new CourtBooking(); //Later put user inside
                        court.printAvailableTimeSlot();

                        //ask user to choose what time slot they want and also the court number
                        System.out.print("Please enter time slot (example: 0800-1000): ");
                        time_slot = input.nextLine();
                        System.out.println("Please enter court number that you selected (accept number only): ");
                        courtNo = input.nextLine();

                        court.setTime_slot(time_slot);
                        court.setCourt(courtNo);

                        //variable named available used as a verification to see whether the user succeed to book court or not
                        available = court.bookingCourt();

                    } while (available == false);

                    break;
                }

                default:{
                    validBook = false;
                    break;
                }
            }

            input.close();
        }

    }
}
