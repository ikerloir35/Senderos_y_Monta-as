package pdinfp_controlador_javafx.ControladoresInscripciones;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;

import java.time.LocalDate;
import java.util.List;

public class ControladorMostrarInscripcionSocio {

    private List<?> listaEntidades;
    private String datoFacilitadoUsuario;

    @FXML
    private Label campoSocioBuscado;
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

    public void initData(List<ExcursionEntity> listaEntidades, String usuarioFacilitadoUsuario) {
        this.listaEntidades = listaEntidades;
        this.datoFacilitadoUsuario = usuarioFacilitadoUsuario;

        campoSocioBuscado.setText(usuarioFacilitadoUsuario);
        vistaGridExcursiones.setItems(FXCollections.observableArrayList(listaEntidades));
    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
