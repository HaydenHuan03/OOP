class DoubleRoom extends HostelRegistration {
    private Payment rentalPrice;

    public DoubleRoom(){
        super();
        rentalPrice = new Payment();
    }

    public void setBlock(String block){super.setBlock(block);}

    public void setRoomNum(int roomNumber){super.setRoomNum(roomNumber);}

    public void Register(){
        String fileName = "DoubleRoom_" + super.getBlock() + ".txt" ;
        int [][] room = new int[30][3] ;

        super.OpenInputFile(fileName, room);
        boolean available = super.Register(fileName, room);

        if(available == true){
            System.out.println("Congrats " + super.getName());
            System.out.println("You have successfully register the hostel !\n");
            calcPrice();
            payment();
        }
        else{
            System.out.println("I'm sorry " + super.getName());
            System.out.println("You have fail to register the hostel...\n");
        }
    }

    public void saveRegister(){
        String type = "Double" ;

        super.saveRegister(type);
    }

    public void CheckingAvailable_DoubleRoom(){
        String fileName = "DoubleRoom_" + super.getBlock() + ".txt" ;

        super.CheckAvailableRoom(fileName);
    }

    public void calcPrice(){rentalPrice.setPrice(1500) ; }
    public void payment(){rentalPrice.paymentMethod() ;}
    public double getPriceNum(){return rentalPrice.getPrice() ; }
}
