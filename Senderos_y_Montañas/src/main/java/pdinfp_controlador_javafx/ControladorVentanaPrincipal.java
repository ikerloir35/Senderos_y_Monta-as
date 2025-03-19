package pdinfp_controlador_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void onSociosButtonClick(ActionEvent event) {

        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/menus_vista/menu_Socio.fxml");
    }

    @FXML
    protected void onExcursionesButtonClick(ActionEvent event) {

        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/menus_vista/menu_Excursion.fxml");
    }

    @FXML
    protected void onInscripcionesButtonClick(ActionEvent event) {

        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/menus_vista/menu_Inscripcion.fxml");
    }
}