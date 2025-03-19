package pdinfp_controlador_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorMenuExcursion {

    @FXML
    protected void onNuevaExcursionButtonClick(ActionEvent event) {


        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/excursiones_vista/nueva_Excursion.fxml");

    }

    @FXML
    protected void onMostrarExcrusionButtonClick(ActionEvent event) {


        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/excursiones_vista/mostrar_Excursion.fxml");

    }

    @FXML
    protected void onModificarExcursionButtonClick(ActionEvent event) {


        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/excursiones_vista/modificar_Excursion.fxml");

    }

    @FXML
    protected void onEliminarExcursionButtonClick(ActionEvent event) {

        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/excursiones_vista/eliminar_Excursion.fxml");

    }

    @FXML
    protected void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
