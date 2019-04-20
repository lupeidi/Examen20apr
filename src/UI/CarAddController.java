package UI;

import Service.CarService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CarAddController {

    public TextField txtId;
    public TextField txtModel;
    public TextField txtKilometer;
    public TextField txtPrice;
    public Button btnAdd;
    public Button btnCancel;

    private CarService carService;

    /**
     * sets services
     * @param carService
     */
    public void setService(CarService carService) {
        this.carService = carService;
    }

    /**
     * method for cancel
     * @param actionEvent click
     */
    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * method for adding a new car
     * @param actionEvent click
     */
    public void btnCarInsertClick(ActionEvent actionEvent) {

        try {
            String id = txtId.getText();
            String model = txtModel.getText();
            int kilometer = Integer.parseInt(txtKilometer.getText());
            double price = Double.parseDouble( txtPrice.getText());
            carService.addOrUpdate(id, model, kilometer, price);
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }
}
