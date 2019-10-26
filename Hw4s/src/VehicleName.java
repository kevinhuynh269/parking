import java.math.BigDecimal;
import java.time.LocalTime;

 public class VehicleName extends Time {
    String NameoFVehicle;
    //double price = minutes * 20;

    public double Price(){
        double price;
        price = minutes * 20;
        return price;
    }

    public double Discount(double price){
        double DiscountedPrice = Price()/2;
        return DiscountedPrice;
    }


    public float howlongtostay;

    public LocalTime TimeToLeave(){
        LocalTime TimeToLeave = TimeEntered.plusMinutes((int)howlongtostay);
        return TimeToLeave;
    }




    @Override
    public String toString(){
        return String.valueOf(NameoFVehicle +" " +TimeEntered);
    }

    public String TimeReturn() {return String.valueOf(TimeEntered);}
}
