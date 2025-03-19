package pdinfp_controlador_javafx.ControladoresExcursiones;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class ControladorNuevaExcursion {

    //atributos fxml relacionados con los fx:id designados en nueva_Excursion.fxml
    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoDescripcion;

    @FXML
    private TextField campoFechaInicio;

    @FXML
    private TextField campoDias;

    @FXML
    private TextField campoPrecio;


    @FXML
    protected void onGuardarButtonClick(ActionEvent event) throws SQLException, DAOException {

        String nombre = campoNombre.getText().trim();
        String descripcion = campoDescripcion.getText().trim();
        String fechaInicioStr = campoFechaInicio.getText().trim();
        String numeroDiasStr = campoDias.getText().trim();
        String precioInscripcionStr = campoPrecio.getText().trim();
        LocalDate fechaInicio = null;
        int numeroDias = 0;
        double precioInscripcion = 0.00;


        if (nombre.isEmpty() || descripcion.isEmpty() || numeroDiasStr.isEmpty() || precioInscripcionStr.isEmpty()) {
            ControladorUtilGenerico.mostrarError("Valores vacios", "Por favor, rellena todos los campos");
            return;
        }

        try {
            fechaInicio = ControladorUtilGenerico.parsearFechaFormato(fechaInicioStr);
            numeroDias = Integer.parseInt(numeroDiasStr);
            precioInscripcion = Double.parseDouble(precioInscripcionStr);
        }catch (DateTimeParseException | NumberFormatException e){
            ControladorUtilGenerico.mostrarError("Datos no Validos",
                    "Verifica el formato de la fecha DD-MM-YYYY, días y precio");

            return;
        }

        if (!ControladorUtilGenerico.verificarFechaInicioValida(fechaInicio) ||
                !ControladorUtilGenerico.verificarEnteroValido(numeroDias) ||
                !ControladorUtilGenerico.verificarDoubleValido(precioInscripcion)) {
            return;
        }

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        ExcursionEntity excursion = new ExcursionEntity();
        excursion.setNomExcursion(nombre);
        excursion.setDescripcion(descripcion);
        excursion.setFechaInicio(fechaInicio);
        excursion.setNumeroDias(numeroDias);
        excursion.setPrecioInscripcion(precioInscripcion);

        hibernateDAOManager.getExcursionDAO().insertar(excursion);

        ControladorUtilGenerico.mostrarExito("Conseguido", "Excursion guardada con exito");

        limpiarCampos();
    }

    private void limpiarCampos(){
        campoNombre.setText("");
        campoDescripcion.setText("");
        campoFechaInicio.setText("");
        campoDias.setText("");
        campoPrecio.setText("");
    }

    @FXML
    protected void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
