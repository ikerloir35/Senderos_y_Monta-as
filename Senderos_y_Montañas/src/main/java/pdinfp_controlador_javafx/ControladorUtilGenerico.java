package pdinfp_controlador_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladoresInscripciones.ControladorMostrarInscripcion;
import pdinfp_controlador_javafx.ControladoresInscripciones.ControladorMostrarInscripcionSocio;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;


public class ControladorUtilGenerico {

    private ControladorUtilGenerico metodoGenerico;
    private Stage primaryStage;

    public void setMetodosGenericos(ControladorUtilGenerico metodosGenericos) {
        this.metodoGenerico = metodoGenerico;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    //Metodo Generico para volver al menú principal
    @FXML
    public static void volverMenuPrincipal(ActionEvent event) {

        //System.out.println("Button clicked: Volver al Menu Principal");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Crear una nueva instancia de la aplicación y llamar al método start
        Senderos_y_Montanas app = new Senderos_y_Montanas();
        Stage newStage = new Stage();
        try {
            app.start(newStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarError(String encabezado, String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(encabezado);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void mostrarExito(String encabezado, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Exito");
        alerta.setHeaderText(encabezado);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void verificarCampoNoVacio(String campoCheck){
        if (campoCheck.isEmpty()) {
            ControladorUtilGenerico.mostrarError("Valores vacios", "Por favor, rellena todos los campos");

            return;

        }
    }

    public static boolean verificarFechaInicioValida(LocalDate fechaInicio) {
        if (fechaInicio.isEqual(LocalDate.now()) || fechaInicio.isBefore(LocalDate.now())) {
            ControladorUtilGenerico.mostrarError("Fecha no valida", "Elige una fecha posterior a " +
                    "la fecha de hoy");
            return false;
        }
        return true;
    }

    public static boolean verificarEnteroValido(int enteroCheck){
        if (enteroCheck <= 0) {
            ControladorUtilGenerico.mostrarError("Numero no valido", "Introduce un número" +
                    "mayor que 0");
            return false;
        }
        return true;
    }

    public static boolean verificarDoubleValido(double doubleCheck){
        if (doubleCheck <= 0) {
            ControladorUtilGenerico.mostrarError("Numero no valido", "Introduce un número" +
                    "mayor que 0.00");
            return false;
        }
        return true;
    }

    public static LocalDate parsearFechaFormato(String fecha){
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaParseada = LocalDate.parse(fecha, formatoFecha);
        return fechaParseada;
    }


    public static boolean comprobarSocioExiste(String nif, List<SocioEntity> listaSocios) {
        for (SocioEntity socioEntity : listaSocios) {
            if (nif.equals(String.valueOf(socioEntity.getNif()))) {
                return true;
            }
        }
        return false;
    }


    public static boolean verificarSocioExistePorId(List<SocioEntity> listaSocios, String socioAVerificar) {

        boolean socioExiste = false;
        int idSociodb = limpiarIdParaDB(socioAVerificar);

        if (idSociodb > 0) {
            for (SocioEntity socio : listaSocios) {
                if (idSociodb == socio.getId()) {
                    socioExiste = true;
                    break;
                }
            }
            if (socioExiste) {
                return true;
            } else {
                ControladorUtilGenerico.mostrarError("Socio no Existe", "El id del socio introducido no exite");
                return false;
            }
        }
        return false;
    }

    public static boolean verificarExcursionExiste(List<ExcursionEntity> listaExcursiones, String excursionAVerificar) {
        boolean excursionExiste = false;

        for (ExcursionEntity excursion: listaExcursiones){
            if (excursionAVerificar.equals(excursion.getNomExcursion())) {
                excursionExiste = true;
            }
        }
        if (excursionExiste) {
            return true;
        } else {
            return false;
        }
    }

    public static int limpiarIdParaDB(String idLogico) {
        try {
            return Integer.parseInt(idLogico.substring(idLogico.length()-4));
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public static void iniciarVentana(ActionEvent event, String nombreVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorUtilGenerico.class.getResource(nombreVentana));
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
