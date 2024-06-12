public class Vehiclemainbody {
    public static void main(String[] args)
    {
        Vehicle transportation = new Vehicle();
        Vehicle vehicle = transportation.setDetail() ;

        int type = vehicle.vehicleType(); //Get the vehicle type

        //Type 1: Motorbike, Type 2(default): Car
        switch (type) {
            case 1: {
                //Get motorbike details
                Motorbike Motorcycle = new Motorbike() ;
                Motorbike userMotor = Motorcycle.setDetail(vehicle);

                userMotor.calcPrice(); //Motorbike price - RM 5
                userMotor.getDetail(); //Inform user the details that he insert
                userMotor.payment(); //for payment purpose
                userMotor.storeDetail(); //Store the details into a file

                break;
            }

            default: {
                //Get car details
                Car car = new Car() ;
                Car userCar = car.setDetail(vehicle);

                userCar.calcPrice(); //Car price - RM 10
                userCar.getDetail(); //Inform user the details that he insert
                userCar.payment(); //for payment purpose
                userCar.storeDetail();//Store the details into a file

                break;
            }
        }
    }
}