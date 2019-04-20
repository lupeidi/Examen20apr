package Service;

import Domain.Car;
import Repository.IRepository;
import Service.Exceptions.CarServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class CarService {

    private IRepository<Car> repository;

    public CarService(IRepository<Car> repository) {
        this.repository = repository;
    }

    /**
     * adds or updates a car
     * @param id
     * @param model
     * @param kilometer
     * @param price
     */
    public void addOrUpdate (String id, String model, int kilometer, double price) {

        Car car = new Car(id, model, kilometer, price);
        List<Car> cars = new ArrayList<>(repository.getAll());

        for (Car i : cars) {
            if (id.equals(i.getId())) {
                throw new CarServiceException("Id already exists");
            }
        }
        repository.upsert(car);
    }

    /**
     * removes a car
     * @param id of the car to remove
     */
    public void remove(String id) {
        Car car = repository.getById(id);

    }

    /**
     *
     * @return list of all cars
     */
    public List<Car> getAll() {
        return repository.getAll();
    }


    /**
     *
     * @return list of all cars, sorted by number of days rented
     */
    public List<Car> sortCarsByDays() {
        List<Car> orderedCars = repository.getAll();
        orderedCars.sort(Comparator.comparing(Car::getTotalDays).reversed());
        return orderedCars;
    }

    /**
     * searching car id to show total profit
     * @param text
     * @return
     */
    public double profitSearchCars(String text) {

        double profit = 0;
        Car c = repository.getById(text);
        profit = c.getPrice() * c.getTotalDays();
        return profit;
    }

    /**
     * searching car id to show total kilometers
     * @param text
     * @return
     */
    public int kilometerSearchCars(String text) {

        int kilometers = 0;
        Car c = repository.getById(text);
        kilometers = c.getKilometer();
        return kilometers;
    }

    /**
     *
     * @return car repository
     */
    public IRepository<Car> getCarRepository() {
        return repository;
    }
}
