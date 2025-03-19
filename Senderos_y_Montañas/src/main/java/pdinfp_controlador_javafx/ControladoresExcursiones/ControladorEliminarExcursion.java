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
import java.util.List;

public class ControladorEliminarExcursion {

    @FXML
    private TextField campoExcursionCambiar;
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

    public void onBuscarInfoExcursionButtonClick(ActionEvent event) throws SQLException, DAOException {

        String excursionBuscada = campoExcursionCambiar.getText().trim();

        ControladorUtilGenerico.verificarCampoNoVacio(excursionBuscada);
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        ExcursionEntity excursionExiste = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (ExcursionEntity excursion : listaExcursiones) {
            if (excursionBuscada.equals(excursion.getNomExcursion())) {

                campoNombre.setText(excursion.getNomExcursion());
                campoDescripcion.setText(excursion.getDescripcion());
                String fechaFormateada = excursion.getFechaInicio().format(formatoFecha);
                campoFechaInicio.setText(fechaFormateada);
                campoDias.setText(excursion.getNumeroDias().toString());
                campoPrecio.setText(excursion.getPrecioInscripcion().toString());
                excursionExiste = excursion;
                break;
            }
        }
        if (excursionExiste == null) {
            ControladorUtilGenerico.mostrarError("Excursión no encontrada", "No se ha encontrado" +
                    " ninguna excursión con ese nombre");
        }
    }

    @FXML
    protected void onEliminarButtonClick(ActionEvent event) throws SQLException, DAOException {

        String excursionBuscada = campoExcursionCambiar.getText().trim();
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

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        ControladorUtilGenerico.verificarCampoNoVacio(excursionBuscada);
        ExcursionEntity excursionExiste = null;
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        for (ExcursionEntity excursion : listaExcursiones) {
            if (excursionBuscada.equals(excursion.getNomExcursion())) {
                excursionExiste = excursion;
                break;
            }
        }
        if (excursionExiste == null) {
            ControladorUtilGenerico.mostrarError("Excursión no encontrada", "No se ha encontrado" +
                    " ninguna excursión con ese nombre");
            return;
        }

        excursionExiste.setNomExcursion(nombre);
        excursionExiste.setDescripcion(descripcion);
        excursionExiste.setFechaInicio(fechaInicio);
        excursionExiste.setNumeroDias(numeroDias);
        excursionExiste.setPrecioInscripcion(precioInscripcion);

        hibernateDAOManager.getExcursionDAO().eliminar(excursionExiste);

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

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
