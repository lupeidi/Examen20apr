package Service;

import Domain.Car;
import Domain.Reservation;
import Repository.IRepository;
import Service.Exceptions.ReservationServiceException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReservationService {
    private IRepository<Reservation>  repository;
    private IRepository<Car> carRepository;

    /**
     * sets repositories
     * @param repository
     * @param carRepository
     */
    public ReservationService(IRepository<Reservation> repository, IRepository<Car>  carRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
    }


    /**
     * adds or updates a reservation
     * @param id
     * @param idCar
     * @param days
     * @param kilometers
     */
    public void addOrUpdate(String id, String idCar, int days, int kilometers) {

        Car carToReserve = carRepository.getById(idCar);
        Reservation reservation = new Reservation(id, idCar, days, kilometers);

        repository.upsert(reservation);
        carToReserve.setTotalDays(carToReserve.getTotalDays() + days);
        carToReserve.setKilometer(carToReserve.getKilometer() + kilometers);
        carRepository.upsert(carToReserve);
    }

    /**
     * removes a reservation
     * @param id of reservation to remove
     */
    public void remove(String id) {

        Reservation reservation = repository.getById(id);
        repository.remove(id);

        Car car = carRepository.getById((reservation.getIdCar()));
        carRepository.upsert(car);

    }

    /**
     *
     * @return list of all reservations
     */
    public List<Reservation> getAll() {
        return repository.getAll();
    }


}
