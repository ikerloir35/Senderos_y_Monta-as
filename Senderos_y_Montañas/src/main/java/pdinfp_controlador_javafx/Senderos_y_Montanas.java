package pdinfp_controlador_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import pdinfp_util.ConectorHibernate;


import java.io.IOException;

public class Senderos_y_Montanas extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pdinfp_vista_javafx/menus_vista/menu_Principal.fxml"));
        Parent root = fxmlLoader.load();

        //Guardamos el controlador del menua principal
        ControladorVentanaPrincipal controlador = fxmlLoader.getController();

        //Pasamos la referencia del Stage al controlador
        controlador.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root, 640, 400);
        primaryStage.setTitle("Senderos y Montañas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        //Creamos la conexión con Hibernate.
        Session conexionBD = ConectorHibernate.getSessionFactory().openSession();

        try{
            //Inicio de la aplicación JavaFX
            launch();
        } catch (Exception e) {
            ControladorUtilGenerico.mostrarError("Error de inicialización",
                    "Ocurrio un error al iniciar la aplicación");
        } finally {
            //Cerramos la conexión con la BBDD
            if (conexionBD != null){
                conexionBD.close();
            }
        }
    }
}
