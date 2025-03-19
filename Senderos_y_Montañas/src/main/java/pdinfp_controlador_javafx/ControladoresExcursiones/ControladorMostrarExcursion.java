package pdinfp_controlador_javafx.ControladoresExcursiones;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;
import pdinfp_vista.ImprimirJavaGenerics;
import pdinfp_vista.UtilidadesVista;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ControladorMostrarExcursion {

    @FXML
    private TextField campoFechaInicial;
    @FXML
    private TextField campoFechaFinal;
    @FXML
    private TableView<ExcursionEntity> vistaGridExcursiones;
    @FXML
    private TableColumn<ExcursionEntity, String> colNombre;
    @FXML
    private TableColumn<ExcursionEntity, LocalDate> colFechaInicio;
    @FXML
    private TableColumn<ExcursionEntity, Integer> colNumeroDias;
    @FXML
    private TableColumn<ExcursionEntity, Double> colPrecioInscripcion;
    @FXML
    private TableColumn<ExcursionEntity, String> colDescripcion;
    @FXML
    private void initialize(){
        colNombre.setCellValueFactory(new PropertyValueFactory<ExcursionEntity, String>("nomExcursion"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<ExcursionEntity, LocalDate>("fechaInicio"));
        colNumeroDias.setCellValueFactory(new PropertyValueFactory<ExcursionEntity, Integer>("numeroDias"));
        colPrecioInscripcion.setCellValueFactory(new PropertyValueFactory<ExcursionEntity, Double>("precioInscripcion"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<ExcursionEntity, String>("descripcion"));
    }


    public void onBuscarExcursionButtonClick(ActionEvent event) throws SQLException, DAOException {
        String fechaInicialStr = campoFechaInicial.getText();
        String fechaFinalStr = campoFechaFinal.getText();
        LocalDate fechaInicial = null;
        LocalDate fechaFinal = null;

        if (fechaInicialStr.isEmpty() || fechaFinalStr.isEmpty()) {
            ControladorUtilGenerico.mostrarError("Valores Vacios", "Rellena las casillas por favor");
            return;
        }
        try {
            fechaInicial = ControladorUtilGenerico.parsearFechaFormato(fechaInicialStr);
            fechaFinal = ControladorUtilGenerico.parsearFechaFormato(fechaFinalStr);
        } catch (DateTimeParseException e) {
            ControladorUtilGenerico.mostrarError("Datos no Validos",
                    "Verifica el formato de la fecha DD-MM-YYYY");
            return;
        }
        List<ExcursionEntity> excursionesEnRango = obtenerExcursionesEnRango(fechaInicial, fechaFinal);

        vistaGridExcursiones.getItems().setAll(excursionesEnRango);
    }

    public List<ExcursionEntity> obtenerExcursionesEnRango(LocalDate fechaInicial, LocalDate fechaFinal) throws SQLException,
            DAOException
    {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        List<ExcursionEntity> excursionesDentroRango = new ArrayList<>();

        for (ExcursionEntity excursion : listaExcursiones) {
            LocalDate fechaExcursion = excursion.getFechaInicio();

            if ((fechaExcursion.isBefore(fechaFinal)||(fechaExcursion.isEqual(fechaFinal)))&&((fechaExcursion.isAfter(fechaInicial)||(fechaExcursion.isEqual(fechaInicial))))){
                excursionesDentroRango.add(excursion);
            }
        }
        return excursionesDentroRango;
    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
