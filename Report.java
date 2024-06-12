public class Report {
    private User userInfo ;

    public Report(String id, String matric){
        //this.userInfo = new User(id, matric) ;
    }

    public void printUser(){
        System.out.println("User name : " +  userInfo.getName());
        System.out.println("User email: " + userInfo.getEmail());
        System.out.println("User contact number: " + userInfo.getContact());
    }

    public void printHostel_Registration(){

    }

    public void printAppointment(){

    }

    public void printReport(){

    }
}
