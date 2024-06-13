import java.util.Scanner;

public class Hostelmainbody {
    public static void main(String[] args){
        boolean HRegister = true ;
        Scanner input = new Scanner(System.in);

        while(HRegister == true){
            char chooseH ;

            //Choose either want to register a room, check available room or quit
            System.out.println("\n--Hostel Registration--");
            System.out.println("=======================");
            System.out.println("1       : Register");
            System.out.println("2       : Check Available Room");
            System.out.println("Any key : Back to Menu\n");
            System.out.print("> ");
            chooseH = input.next().charAt(0);
            input.nextLine(); //Consume new line

            int SingleDouble;

            switch(chooseH){
                case '1':{ //Register a room
                    String block, name ;
                    int roomNum ;

                    do{
                        System.out.println("\nRegister Room");
                        System.out.println("-------------");
                        System.out.println("Note: M16 - Single Room");
                        System.out.println("      M17 - Double Room\n");
                        System.out.println("[0] Single Room");
                        System.out.println("[1] Double Room\n");
                        System.out.print("> ");

                        SingleDouble = input.nextInt();

                        if((0 > SingleDouble) || (SingleDouble > 1)){
                            System.out.println("\nInvalid. Please choose again...");
                        }
                    }while((0 > SingleDouble) || (SingleDouble > 1));

                    input.nextLine(); //Consume new line

                    //Get user name, block and room number he want to register
                    System.out.print("Name : ");
                    name = input.nextLine();

                    if(SingleDouble == 0){
                        block = "M16" ;
                    }
                    else{
                        block = "M17" ;
                    }

                    System.out.println("Block : " + block);
                    System.out.print("Room Number : ");
                    roomNum = input.nextInt();
                    input.nextLine(); //Consume new line

                    if(SingleDouble == 0){
                        SingleRoom room = new SingleRoom();

                        room.setName(name);
                        room.setBlock(block);
                        room.setRoomNum(roomNum);

                        room.Register(input); //Register room
                        room.saveRegister(); //Save user's hostel data in a new file
                    }
                    else{
                        DoubleRoom room = new DoubleRoom();

                        room.setName(name);
                        room.setBlock(block);
                        room.setRoomNum(roomNum);

                        room.Register(input); //Register room
                        room.saveRegister(); //Save user's hostel data in a new file
                    }

                    break;
                }

                case '2':{
                    do{
                        System.out.println("\nCheck Availability of Room");
                        System.out.println("--------------------------");
                        System.out.println("Note: M16 - Single Room");
                        System.out.println("      M17 - Double Room\n");
                        System.out.println("[0] Single Room");
                        System.out.println("[1] Double Room\n");
                        System.out.print("> ");

                        SingleDouble = input.nextInt();

                        if((0 > SingleDouble) || (SingleDouble > 1)){
                            System.out.println("\nInvalid. Please choose again...");
                        }
                    }while((0 < SingleDouble) || (SingleDouble > 1));

                    input.nextLine();

                    if(SingleDouble == 0){
                        SingleRoom room = new SingleRoom();

                        room.CheckingAvailable_SingleRoom();
                    }
                    else{
                        DoubleRoom room = new DoubleRoom();

                        room.CheckingAvailable_DoubleRoom();
                    }

                    break;
                }

                default:{
                    HRegister = false;

                    break;
                }
            }
        }

        input.close();
    }
}
