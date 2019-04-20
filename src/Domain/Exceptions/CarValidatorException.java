package Domain.Exceptions;

public class CarValidatorException extends RuntimeException {

    public CarValidatorException(String s) {
        super("Car validator exception: " + s);
    }
}
