import java.util.Scanner;

public class Vehiclemainbody {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Vehicle transportation = new Vehicle();
        Vehicle vehicle = transportation.setDetail(input) ;

        int type = vehicle.vehicleType(input); //Get the vehicle type
        System.out.println("Vehicle type selected: " + type + "\n");

        //Type 1: Motorbike, Type 2(default): Car
        switch (type) {
            case 1: {
                //Get motorbike details
                Motorbike Motorcycle = new Motorbike() ;
                Motorbike userMotor = Motorcycle.setDetail(vehicle, input);

                userMotor.calcPrice(); //Motorbike price - RM 5
                userMotor.getDetail(); //Inform user the details that he insert
                userMotor.payment(input); //for payment purpose
                userMotor.storeDetail(); //Store the details into a file

                break;
            }

            default: {
                //Get car details
                Car car = new Car() ;
                Car userCar = car.setDetail(vehicle, input);

                userCar.calcPrice(); //Car price - RM 10
                userCar.getDetail(); //Inform user the details that he insert
                userCar.payment(input); //for payment purpose
                userCar.storeDetail();//Store the details into a file

                break;
            }
        }

        input.close();
    }
}