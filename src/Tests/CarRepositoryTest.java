package Tests;

import Domain.Car;
import Domain.CarValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

//    @org.junit.jupiter.api.Test
//    void getById() {
//        IRepository<Car> repository = new InMemoryRepository<>(new CarValidator());
//        Car car = new Car("151", "model", 100,  15);
//        repository.upsert(car);
//        Car found = repository.getById("151");
//        assertNotNull(found, "Returned null for existing id!");
//        assertEquals("151", found.getId(), String.format("Returned id another id", found.getId()));
//        assertEquals("model", found.getModel(), String.format("Returned model=%s instead of %s", found.getModel()));
//        assertEquals(100, found.getModel(), String.format("Returned kilometer=%s instead of %s", found.getKilometer()));
//        assertEquals(15, found.getModel(), String.format("Returned price=%s instead of %s", found.getPrice()));
//    }

}