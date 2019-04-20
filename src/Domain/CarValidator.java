package Domain;
import Domain.Exceptions.CarValidatorException;

import java.util.Calendar;

public class CarValidator implements IValidator<Car> {

    public void validate(Car car) {

        if (car.getKilometer() < 0) {
            throw new CarValidatorException("Number of kilometers must be positive.");
        }
    }
}
