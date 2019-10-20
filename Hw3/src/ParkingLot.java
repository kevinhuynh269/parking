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
    int value =ParkingLotTime.compareTo(VehicleLicense.get(i).TimeToLeave());

    if(value <= 0){
      //  System.out.println("ParkingLot Time is bigger");
    }else{
        RemoveToParkingLot(i);
        System.out.println("Vehicle "+ VehicleLicense.get(i).NameoFVehicle+" Left on " +VehicleLicense.get(i).TimeToLeave());
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



    if(!VehicleWaiting.isEmpty()){
        VehicleName temp = VehicleWaiting.get(0);
        temp.TimeEntered = ParkingLotTime;
        VehicleLicense.remove(CarInt);

        if (!VehicleWaiting.isEmpty()) {
            AddToParkingLot(temp);


        }
    }else{
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
    ParkingLot parking = new ParkingLot();

   // parking.AddToParkingLot("kevinHuynh");
   // parking.AddToParkingLot("kevinHuynhs");


    //    System.out.print("Parking Lot: ");
   // parking.PrintParkingLot();
      //  System.out.print("Waiting: ");
    //parking.PrintWaiting();
    //parking.RemoveToParkingLot(1);
    //System.out.print("Parking Lot: ");
    //parking.PrintParkingLot();

       File file = new File("src\\Hello.txt");
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




        for(int i =0; i< Methods.length;i++)
        {
            System.out.print("Hello "+Methods[i]+ " ");
        }

        parking.PrintParkingLot();

       //System.out.println(LocalTime.now().getHour());
        //System.out.println(LocalTime.MIDNIGHT);// returns 00:00



    }


}


//what i need to do in this assignment,
//i should have
//time after next car , carname, timestay
//time after next car will be by minutes;


