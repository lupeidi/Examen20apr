package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Reservation extends Entity {

    private String idCar;
    private int days;
    private int kilometers;

    public Reservation(String id, String idCar, int days, int kilometers) {
        super(id);
        this.idCar = idCar;
        this.days = days;
        this.kilometers = kilometers;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idCar='" + idCar + '\'' +
                ", days=" + days +
                ", kilometers=" + kilometers +
                '}';
    }

    public String getIdCar() {
        return idCar;
    }

    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }
}


