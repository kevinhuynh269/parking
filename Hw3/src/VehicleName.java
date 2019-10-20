import java.math.BigDecimal;
import java.time.LocalTime;

public class VehicleName extends Time {
    String NameoFVehicle;
    BigDecimal k = new BigDecimal(.20*minutes);
    BigDecimal price = k;
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
