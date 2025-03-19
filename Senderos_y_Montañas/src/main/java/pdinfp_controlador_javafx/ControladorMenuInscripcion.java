package pdinfp_controlador_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorMenuInscripcion {

    @FXML
    protected void onAnadirInscripcionButtonClick(ActionEvent event) {

        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/inscripciones_vista/nueva_Inscripcion.fxml");
    }

    @FXML
    protected void onMostrarInscripcionButtonClick(ActionEvent event) {

        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/inscripciones_vista/mostrar_Inscripcion.fxml");
    }
    @FXML
    protected void onEliminarInscripcionButtonClick(ActionEvent event) {

        ControladorUtilGenerico.iniciarVentana(event, "/pdinfp_vista_javafx/inscripciones_vista/eliminar_Inscripcion.fxml");
    }
    @FXML
    protected void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
