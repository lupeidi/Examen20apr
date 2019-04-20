package Service.Exceptions;

    public class CarServiceException extends RuntimeException {

        public CarServiceException(String s){
            super("Car service exception: " + s);
        }
}
