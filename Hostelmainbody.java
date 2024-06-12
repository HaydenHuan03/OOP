import java.util.Scanner;

public class Hostelmainbody {
    public static void main(String[] args){
        boolean HRegister = true ;
        Scanner input = new Scanner(System.in);

        while(HRegister == true){
            char chooseH ;
            System.out.println("Hostel Registration");
            System.out.println("===================");
            System.out.println("1       : Register");
            System.out.println("2       : Check Available Room");
            System.out.println("Any key : Back to Menu\n");
            System.out.print("> ");
            chooseH = input.next().charAt(0);

            int SingleDouble;

            switch(chooseH){
                case '1':{
                    String block, name ;
                    int roomNum ;

                    do{
                        System.out.println("[0] Single Room");
                        System.out.println("[1] Double Room\n");
                        System.out.print("> ");

                        SingleDouble = input.nextInt();
                    }while((0 < SingleDouble) || (SingleDouble > 1));

                    input.nextLine();

                    System.out.print("Name : ");
                    name = input.nextLine();
                    System.out.print("Block : ");
                    block = input.nextLine();
                    System.out.println("Room Number : ");
                    roomNum = input.nextInt();
                    input.nextLine();

                    if(SingleDouble == 0){
                        SingleRoom room = new SingleRoom();

                        room.setName(name);
                        room.setBlock(block);
                        room.setRoomNum(roomNum);

                        room.Register();
                        room.saveRegister();
                    }
                    else{
                        DoubleRoom room = new DoubleRoom();

                        room.setName(name);
                        room.setBlock(block);
                        room.setRoomNum(roomNum);

                        room.Register();
                        room.saveRegister();
                    }

                    break;
                }

                case '2':{
                    do{
                        System.out.println("[0] Single Room");
                        System.out.println("[1] Double Room\n");
                        System.out.print("> ");

                        SingleDouble = input.nextInt();
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
