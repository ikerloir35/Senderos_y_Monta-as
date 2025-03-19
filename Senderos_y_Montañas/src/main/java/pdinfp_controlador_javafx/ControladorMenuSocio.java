package pdinfp_controlador_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import static pdinfp_controlador_javafx.ControladorUtilGenerico.iniciarVentana;

public class ControladorMenuSocio {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    protected void onAñadirSocioButtonClick(ActionEvent event) {

        iniciarVentana(event, "/pdinfp_vista_javafx/menus_vista/menu_NuevoSocio.fxml");

    }

    @FXML
    protected void onMostrarSocioButtonClick(ActionEvent event) {



        iniciarVentana(event, "/pdinfp_vista_javafx/menus_vista/menu_BusquedaSocio.fxml");


    }

    @FXML
    protected void onModificarSocioButtonClick(ActionEvent event) {
        iniciarVentana(event, "/pdinfp_vista_javafx/menus_vista/menu_ModificarSocio.fxml");

    }
    //Este método no borrara al socio, sino que activara o desactivara su casilla de activo (booleano),
    //para mantener la integridad de la base de datos.
    @FXML
    protected void onEliminarSocioButtonClick(ActionEvent event) {
        String nombreVentana= "/pdinfp_vista_javafx/socios_vista/eliminar_socio.fxml";
        iniciarVentana(event, nombreVentana);

    }
    @FXML
    protected void onMostrarFacturaButtonClick(ActionEvent event) {



        String nombreVentana= "/pdinfp_vista_javafx/socios_vista/mostrar_FacturaSocio.fxml";
        iniciarVentana(event, nombreVentana);



    }
    @FXML
    protected void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);

    }
}
