package UI;

import Domain.Car;
import Service.CarService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarsByDaysController {
    public TableView tableViewCars;
    public TableColumn tableColumnId;
    public TableColumn tableColumnModel;
    public TableColumn tableColumnDays;
    private CarService service;

    private ObservableList<Car> cars = FXCollections.observableArrayList();

    /**
     * sets services
     * @param service
     */
    public void setService(CarService service) {
        this.service = service;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List<Car> carsOrdered = service.sortCarsByDays();

                cars.addAll(carsOrdered);
                tableViewCars.setItems(cars);
            } catch (RuntimeException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window: ", e);
            }
        });
    }

}
