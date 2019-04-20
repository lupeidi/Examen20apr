package Domain;

public class Car extends Entity {

    private String model;
    private int kilometer;
    private double price;
    private int totalDays;

    public Car(String id, String model, int kilometer, double price) {
        super(id);
        this.model = model;
        this.kilometer = kilometer;
        this.price = price;
        this.totalDays = 0;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", kilometer=" + kilometer +
                ", price=" + price +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
}