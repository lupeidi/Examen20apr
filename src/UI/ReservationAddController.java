package UI;

import Service.ReservationService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationAddController {

    public TextField txtId;
    public TextField txtIdCar;
    public TextField txtDays;
    public TextField txtKilometers;
    public Button btnAdd;
    public Button btnCancel;

    private ReservationService reservationService;

    /**
     * sets services
     * @param reservationService
     */
    public void setService(ReservationService reservationService) {
        this.reservationService = reservationService;
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
     * method for adding new reservation
     * @param actionEvent click
     */
    public void btnReservationInsertClick(ActionEvent actionEvent) {

        try {
            String id = txtId.getText();
            String idCar = txtIdCar.getText();
            int days = Integer.parseInt(txtDays.getText());
            int kilometers = Integer.parseInt(txtKilometers.getText());

            reservationService.addOrUpdate(id, idCar, days, kilometers);
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }
}
