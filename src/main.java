import Domain.*;

import Repository.*;
import Service.CarService;
import Service.ReservationService;
import UI.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Resources/mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();

        IValidator<Car> clientValidator = new CarValidator();
        IValidator<Reservation> reservationValidator = new ReservationValidator();

        IRepository<Car> cardsRepository = new JsonFileRepository<>(clientValidator, "cars.json", Car.class);
        IRepository<Reservation> bookingsRepository = new JsonFileRepository<>(reservationValidator, "reservations.json", Reservation.class);


        CarService carService = new CarService(cardsRepository);
        ReservationService reservationService = new ReservationService(bookingsRepository, cardsRepository);


        controller.setServices(carService, reservationService);

        primaryStage.setTitle("Car manager");
        primaryStage.setScene(new Scene(root, 1500, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
