import java.util.Scanner;

public class DoubleRoom extends HostelRegistration { //Inheritance
    private Payment rentalPrice; //Composition

    public DoubleRoom(){
        super();
        rentalPrice = new Payment();
    }

    //Mutator
    public void setBlock(String block){super.setBlock(block);}
    public void setRoomNum(int roomNumber){super.setRoomNum(roomNumber);}

    //Register room
    public void Register(Scanner input){
        String fileName = "DoubleRoom_" + super.getBlock() + ".txt" ;
        int [][] room = new int[30][3] ;

        //Store room data from an input file
        super.OpenInputFile(fileName, room);

        //Check if the user has previous register room or the room is not available
        boolean available = super.Register(fileName, room);

        if(available == true){ //Room available and user successfully register
            System.out.println("\nCongrats " + super.getName());
            System.out.println("You have successfully register the hostel !\n");
            calcPrice();
            payment(input);
        }
        else{ //Room not available or user has register a room before
            System.out.println("\nI'm sorry " + super.getName());
            System.out.println("You have fail to register the hostel...\n");
        }
    }

    public void saveRegister(){
        String type = "Double" ;

        super.saveRegister(type); //Save user's hostel data in a new file
    }

    public void CheckingAvailable_DoubleRoom(){
        String fileName = "DoubleRoom_M17.txt" ;

        super.CheckAvailableRoom(fileName);
    }

    public void calcPrice(){rentalPrice.setPrice(1000) ; }
    public void payment(Scanner input){rentalPrice.paymentMethod(input) ;}
    public double getPriceNum(){return rentalPrice.getPrice() ; }
}
