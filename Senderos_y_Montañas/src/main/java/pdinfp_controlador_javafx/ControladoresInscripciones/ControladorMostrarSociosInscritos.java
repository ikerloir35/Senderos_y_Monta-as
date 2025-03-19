package pdinfp_controlador_javafx.ControladoresInscripciones;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;


import java.util.List;

public class ControladorMostrarSociosInscritos {

    private List<?> listaEntidades;
    private String datoFacilitadoUsuario;

    @FXML
    private Label campoExcursionBuscada;
    @FXML
    private TableView<ExcursionEntity> vistaGridSociosApuntados;
    @FXML
    private TableColumn<SocioEntity, String> colNombre;
    @FXML
    private TableColumn<SocioEntity, String> colnif;
    @FXML
    private TableColumn<SocioEntity, String> colTipo;
    @FXML
    private void initialize(){
        colNombre.setCellValueFactory(new PropertyValueFactory<SocioEntity, String>("nombreSocio"));
        colnif.setCellValueFactory(new PropertyValueFactory<SocioEntity, String>("nif"));
        colTipo.setCellValueFactory(new PropertyValueFactory<SocioEntity, String>("tipo"));
    }

    public void initData(List<ExcursionEntity> listaEntidades, String usuarioFacilitadoUsuario) {
        this.listaEntidades = listaEntidades;
        this.datoFacilitadoUsuario = usuarioFacilitadoUsuario;

        campoExcursionBuscada.setText(usuarioFacilitadoUsuario);
        vistaGridSociosApuntados.setItems(FXCollections.observableArrayList(listaEntidades));
    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);

    }
}
