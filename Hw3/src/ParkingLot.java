import javax.sound.midi.SysexMessage;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

interface IParkingLotMethods{
    boolean isAvailable();
    void AddToParkingLot(String Car, int AfterCarTime,float ThirdParam);
    void RemoveToParkingLot(int CarInt);
    void PrintParkingLot();
}


public class ParkingLot implements IParkingLotMethods{
private int capacity = 3;
private int ratePerHour = 5;
LinkedList<VehicleName> VehicleWaiting = new LinkedList<VehicleName>();
LinkedList<VehicleName> VehicleLicense = new LinkedList<VehicleName>();
private LocalTime ParkingLotTime = LocalTime.MIDNIGHT;
public boolean isAvailable(){
    if(VehicleLicense.size() < capacity){
        return true;
    }
    else{
        return false;
    }
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

    public void Finisher(){

    for(int i=0; i <= Idunno()+400000;i++){
        ParkingLotTime = ParkingLotTime.plusMinutes(1);
        for(int j=0; j < VehicleLicense.size();j++){
            // System.out.println(VehicleLicense.get(i).TimeToLeave());
            int value =ParkingLotTime.compareTo(VehicleLicense.get(j).TimeToLeave());

            if(value <= 0){
                //  System.out.println("ParkingLot Time is bigger");
            }else{

                System.out.println("Vehicle "+ VehicleLicense.get(j).NameoFVehicle+" Left on " +VehicleLicense.get(j).TimeToLeave());
                RemoveToParkingLot(j);
            }


        }

    }

    }

    public void AddToParkingLot(VehicleName obj){
    VehicleLicense.add(obj);
    }

    public void AddToParkingLot(String Car,int AfterCarTime,float ThirdParam){

        VehicleName carName = new VehicleName();
        carName.NameoFVehicle = Car;
        ParkingLotTime= ParkingLotTime.plusMinutes(AfterCarTime);
        carName.TimeEntered = ParkingLotTime;
        carName.howlongtostay = ThirdParam;

        if(isAvailable()){
            VehicleLicense.add(carName);
            System.out.println(carName + " Has Successfully Entered The Parking Lot");
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
       //System.out.println(VehicleLicense.get(CarInt).NameoFVehicle+ "Has Left"); System.out.println(VehicleLicense.get(CarInt).NameoFVehicle+ "Has Left");
        VehicleLicense.remove(CarInt);
        System.out.println(VehicleLicense.get(CarInt).NameoFVehicle+ " Has Left at " + VehicleLicense.get(CarInt).TimeToLeave() +"  $"+ VehicleLicense.get(CarInt).Price());
        if (!VehicleWaiting.isEmpty()) {
            AddToParkingLot(temp);


        }
    }else{
        System.out.println(VehicleLicense.get(CarInt).NameoFVehicle+ " Has Left at " + VehicleLicense.get(CarInt).TimeToLeave() +"  $"+ VehicleLicense.get(CarInt).Price());
        VehicleLicense.remove(CarInt);

    }



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
        System.out.println();
    ParkingLot parking = new ParkingLot();

        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();

    System.out.println("Please Enter the TextFile that you want to use including the .txt at the end ");
       File file = new File("src\\" + inputString);
       Scanner sc = new Scanner(file);
       sc.useDelimiter("\\n");

        String Holder;

        String[] Methods = new String[2];


        while(sc.hasNext()){
           // System.out.println(sc.next());
           Holder = sc.next();
           Methods = Holder.split(" ");
           int FirstValue = Integer.parseInt(Methods[0]);
           float ThirdValues = Float.parseFloat(Methods[2]);
           parking.AddToParkingLot(Methods[1],FirstValue,ThirdValues);
           parking.Checker();


        }



       // parking.Idunno();
        parking.Finisher();
   //     for(int i =0; i< Methods.length;i++)
     //   {
       //     System.out.print("Hello "+Methods[i]+ " ");
        //}
        //parking.PrintWaiting();
        //parking.PrintParkingLot();

       //System.out.println(LocalTime.now().getHour());
        //System.out.println(LocalTime.MIDNIGHT);// returns 00:00



    }


}


//what i need to do in this assignment,
//i should have
//time after next car , carname, timestay
//time after next car will be by minutes;


