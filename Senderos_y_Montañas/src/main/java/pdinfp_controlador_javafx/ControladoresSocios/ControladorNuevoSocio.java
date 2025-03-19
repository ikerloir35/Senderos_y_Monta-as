package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorNuevoSocio {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    public void onNuevoEstandarButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pdinfp_vista_javafx/socios_vista/nuevoEstandar.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cerrar la ventana anterior
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            //Abrir la nueva ventana
            Stage newStage = new Stage();
            newStage.setTitle("Senderos y Montañas");
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onNuevoFederadoButtonClick(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pdinfp_vista_javafx/socios_vista/nuevoFederado.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cerrar la ventana anterior
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            //Abrir la nueva ventana
            Stage newStage = new Stage();
            newStage.setTitle("Senderos y Montañas");
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onNuevoInfantilButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pdinfp_vista_javafx/socios_vista/nuevoInfantil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Cerrar la ventana anterior
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            //Abrir la nueva ventana
            Stage newStage = new Stage();
            newStage.setTitle("Senderos y Montañas");
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
