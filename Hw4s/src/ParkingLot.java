import javax.sound.midi.SysexMessage;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

interface IParkingLotMethods{
    boolean isAvailable();
    void AddToParkingLot(String Car, int AfterCarTime,float ThirdParam, String name);
    void RemoveToParkingLot(int CarInt);
    void PrintParkingLot();
    int VehicleLicenseSize();
}


public class ParkingLot extends VehicleName implements IParkingLotMethods{

private int capacity = 3;
private int ratePerHour = 5;
  LinkedList<VehicleName> VehicleWaiting = new LinkedList<VehicleName>();
  LinkedList<VehicleName> VehicleLicense = new LinkedList<VehicleName>();
public static LocalTime ParkingLotTime = LocalTime.MIDNIGHT;
public boolean isAvailable(){
    if(VehicleLicense.size() < capacity){
        return true;
    }
    else{
        return false;
    }
}

   public int VehicleLicenseSize(){
    return VehicleLicense.size();

    }
    public void Checker(){
 for(int i=0; i < VehicleLicense.size();i++){
   // System.out.println(VehicleLicense.get(i).TimeToLeave());
     Collections.sort(VehicleLicense,Comparator.comparing(VehicleName::TimeToLeave));
    int value =ParkingLotTime.compareTo(VehicleLicense.get(i).TimeToLeave());

    if(value <= 0){
      //  System.out.println("ParkingLot Time is bigger");
    }else{
        RemoveToParkingLot(i);
      //  System.out.println("Vehicle "+ VehicleLicense.get(i).NameoFVehicle+" Left on " +VehicleLicense.get(i).TimeToLeave());
    }


 }
    }




    public LocalTime LargestTime() {
        LocalTime Largest = LocalTime.MIDNIGHT;
        for (int i = 0; i < VehicleLicense.size(); i++) {
            VehicleLicense.get(i).TimeToLeave();
            if (i == 0) {
                Largest = VehicleLicense.get(i).TimeToLeave();
            }
            int values = Largest.compareTo(VehicleLicense.get(i).TimeToLeave());
            if (values < 0) {
                Largest = VehicleLicense.get(i).TimeToLeave();
            }


        }
        return Largest;
    }

    public int Idunno(){
    int Hours = ParkingLotTime.getHour() * 60;
    int Minut = ParkingLotTime.getMinute();
    int finals = Hours + Minut;

    int LargHour = LargestTime().getHour() * 60;
    int LargMin = LargestTime().getMinute();
    int LargFinal = LargHour + LargMin;

    int Done = LargFinal-finals;
   // System.out.println(Done);
    return Done;

    }
    public LinkedList<VehicleName> Returner(){

    return VehicleLicense;
    }


    public void Finisher(LinkedList<VehicleName> k1){

    }

    public void Finisher(){

    for(int i=0; i <= Idunno()+400000;i++){
        ParkingLotTime = ParkingLotTime.plusMinutes(1);
        for(int j=0; j < VehicleLicense.size();j++){
            // System.out.println(VehicleLicense.get(i).TimeToLeave());
            int value =ParkingLotTime.compareTo(VehicleLicense.get(j).TimeToLeave());

            if(value <= 0){
                //  System.out.println("ParkingLot Time is bigger");
            }else{

              //  System.out.println("Vehicle "+ VehicleLicense.get(j).NameoFVehicle+" Left on " +VehicleLicense.get(j).TimeToLeave());
                RemoveToParkingLot(j);
            }


        }

    }

    }

    public void AddToParkingLot(VehicleName obj){
    VehicleLicense.add(obj);
    }

    public void AddToParkingLot(String Car,int AfterCarTime,float ThirdParam, String name){

        VehicleName carName = new VehicleName();
        carName.NameoFVehicle = Car;
        ParkingLotTime= ParkingLotTime.plusMinutes(AfterCarTime);
        carName.TimeEntered = ParkingLotTime;
        carName.howlongtostay = ThirdParam;

        if(isAvailable()){
            VehicleLicense.add(carName);
            System.out.println(carName + " Has Successfully Entered The Parking Lot" + "("+ name+ ")");
           // System.out.println("When The Car should Leave " + carName.TimeToLeave());
        }else{
            VehicleWaiting.add(carName);

            int carPosition =  VehicleWaiting.indexOf(carName)+1;
            System.out.println("No more Space " + carName.NameoFVehicle + " will wait in position " + carPosition);
        }

    }

    public void RemoveToParkingLot(int CarInt) {

    int hourss =VehicleLicense.get(CarInt).TimeEntered.getHour() * 60;
    int Minutess = VehicleLicense.get(CarInt).TimeEntered.getMinute();
    int finallss = hourss + Minutess;

    int mours = VehicleLicense.get(CarInt).TimeToLeave().getHour() * 60;
        int Sours = VehicleLicense.get(CarInt).TimeToLeave().getMinute();
    int minal = mours + Sours;
    int diff = minal - finallss;
    VehicleLicense.get(CarInt).minutes = diff;
       // System.out.println(VehicleLicense.get(CarInt).Price());

    if(!VehicleWaiting.isEmpty()){
        VehicleName temp = VehicleWaiting.get(0);
        temp.TimeEntered = ParkingLotTime;
        VehicleLicense.remove(CarInt);

        try{
            System.out.println(VehicleLicense.get(CarInt).NameoFVehicle+ " Has Left at " + VehicleLicense.get(CarInt).TimeToLeave() +"  $"+ VehicleLicense.get(CarInt).Price());
        }catch(Exception ex){

        }
            AddToParkingLot(temp);
        VehicleWaiting.remove(VehicleWaiting.get(0));



    }else{
       VehicleName hello= VehicleLicense.get(CarInt);
       LocalTime yellow = VehicleLicense.get(CarInt).TimeToLeave();
       double sello = VehicleLicense.get(CarInt).Price();

        VehicleLicense.remove(CarInt);
        System.out.println(hello+ " Has Left at " + yellow +"  $"+ sello);
    }



    }

    public boolean Containss(String k){
    return VehicleLicense.toString().contains(k);
    }


    public void PrintParkingLot() {
            System.out.println(VehicleLicense.toString());
    }
    public void PrintWaiting() {
        System.out.println(VehicleWaiting.toString());
    }


    public static void main(String args[]) throws FileNotFoundException {
   // System.out.println("");
        System.out.println("This is Kevin's Parking Lot. The Rate is 20 Bucks an Minute :)");
        System.out.println("Please Enter the TextFile that you want to use including the .txt at the end ");
        System.out.println();
    ParkingLot parking = new ParkingLot();
    ParkingLot parking1 = new ParkingLot();
        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();


       File file = new File("src\\" + inputString);
       Scanner sc = new Scanner(file);
       sc.useDelimiter("\\n");

        String Holder;

        String[] Methods = new String[2];


        while(sc.hasNext()){

            if(parking.VehicleLicenseSize() <= parking1.VehicleLicenseSize()){


                Holder = sc.next();
                Methods = Holder.split(" ");
                int FirstValue = Integer.parseInt(Methods[0]);
                float ThirdValues = Float.parseFloat(Methods[2]);
                parking.Checker();
                if(parking.Containss(Methods[1])){
                    System.out.println("Cannot since its already in a parking lot");
                }else{
                    parking.AddToParkingLot(Methods[1],FirstValue,ThirdValues,"parking");
                }



            }else{
                Holder = sc.next();
                Methods = Holder.split(" ");
                int FirstValue = Integer.parseInt(Methods[0]);
                float ThirdValues = Float.parseFloat(Methods[2]);
                parking1.Checker();
                if(parking.Containss(Methods[1])){
                    System.out.println("Cannot enter since its already in a parking lot"+ "("+Methods[1]+")");
                }else{
                    parking1.AddToParkingLot(Methods[1],FirstValue,ThirdValues,"parking1");
                }



            }

           // System.out.println(sc.next());

        }


        parking.Finisher();
        parking1.Finisher();





    }


}


//what i need to do in this assignment,
//i should have
//time after next car , carname, timestay
//time after next car will be by minutes;


