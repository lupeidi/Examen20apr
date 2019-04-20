package Domain;

import Domain.Exceptions.ReservationValidatorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReservationValidator implements IValidator<Reservation> {

    public void validate(Reservation reservation) {
        if (reservation.getKilometers() < 0) {
            throw new ReservationValidatorException("Number of kilometers must be positive.");
        }
        if (reservation.getDays() < 0) {
            throw new ReservationValidatorException("Number of days must be positive.");
        }
    }
}
