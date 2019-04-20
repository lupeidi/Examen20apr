package UI;

import Domain.Car;
import Domain.Reservation;
import Service.CarService;
import Service.ReservationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {



    public TableView tableViewCars;
    public TableColumn tableColumnIdCar;
    public TableColumn tableColumnModel;
    public TableColumn tableColumnKilometer;
    public TableColumn tableColumnPrice;
    public TableColumn tableColumnTotalDays;
    public TableColumn tableColumnTotalProfit;
    public Button btnCarAdd;
    public Button btnCarDelete;


    public TableView tableViewReservations;
    public TableColumn tableColumnIdReservation;
    public TableColumn tableColumnIdReservationCar;
    public TableColumn tableColumnDays;
    public TableColumn tableColumnCarKilometers;
    public Button btnReservationAdd;
    public Button btnReservationDelete;

    public TextField txtProfitInput;
    public TextField txtKilometerInput;
    public Label profitOutput;
    public Label kilometerOutput;

    private CarService carService;
    private ReservationService reservationService;

    private ObservableList<Car> cars = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    /**
     * sets services
     * @param carService
     * @param reservationService
     */
    public void setServices( CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            cars.addAll(carService.getAll());
            tableViewCars.setItems(cars);
            reservations.addAll(reservationService.getAll());
            tableViewReservations.setItems(reservations);
        });
    }


    public void btnCarAddClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../Resources/carAdd.fxml"));
        upsertCar(fxmlLoader, "Add/Update Car");
    }

    public void btnCarDeleteClick(ActionEvent actionEvent) {
        Car selected = (Car)tableViewCars.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                carService.remove(selected.getId());
                cars.clear();
                cars.addAll(carService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    /**
     * method for creating new window in which to add car
     * @param fxmlLoader
     * @param text
     */
    public void upsertCar(FXMLLoader fxmlLoader, String text) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            stage.setTitle(text);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CarAddController controller = fxmlLoader.getController();
            controller.setService(carService);
            stage.showAndWait();
            cars.clear();
            cars.addAll(carService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window", e);
        }
    }

    public void btnReservationAddClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../Resources/reservationAdd.fxml"));
        upsertReservation(fxmlLoader, "Add/Update Reservation");
    }

    public void btnReservationDeleteClick(ActionEvent actionEvent) {
        Reservation selected = (Reservation) tableViewReservations.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                reservationService.remove(selected.getId());
                reservations.clear();
                reservations.addAll(reservationService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    /**
     * method for creating new window in which to add reservation
     * @param fxmlLoader
     * @param text
     */
    public void upsertReservation(FXMLLoader fxmlLoader, String text) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage stage = new Stage();
            stage.setTitle(text);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ReservationAddController controller = fxmlLoader.getController();
            controller.setService(reservationService);
            stage.showAndWait();
            reservations.clear();
            reservations.addAll(reservationService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window", e);
        }
    }


    /**
     * shows total kilometers of given car id
     * @param actionEvent
     */
    public void kilometersReport(ActionEvent actionEvent) {
        String text = txtKilometerInput.getText();

        int result = carService.kilometerSearchCars(text);
        kilometerOutput.setText(Integer.toString(result));
    }

    /**
     * shows total profit of given car id
     * @param actionEvent
     */
    public void profitReport(ActionEvent actionEvent) {

        String text = txtProfitInput.getText();
        double result = carService.profitSearchCars(text);
        profitOutput.setText(Double.toString(result));
    }


    /**
     * opens new window to show cars ordered by days rented
     * @param actionEvent
     */
    public void carsByDaysReport(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Resources/carsByDays.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("Cars ordered by days");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CarsByDaysController controller = fxmlLoader.getController();
            controller.setService(carService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: ", e);
        }
    }
}
