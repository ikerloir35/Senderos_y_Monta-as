package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;

import java.sql.SQLException;
import java.util.List;

public class ControladorMostrarXNif {

    @FXML
    private TextField campoNif;
    @FXML
    private TableView<SocioEntity> vistaGridSocios;
    @FXML
    private TableColumn<SocioEntity, Integer> colNumSocio;
    @FXML
    private TableColumn<SocioEntity, String> colNombreSocio;
    @FXML
    private TableColumn<SocioEntity, String> colNif;
    @FXML
    private TableColumn<SocioEntity, String> colTipo;

    @FXML
    private void initialize(){
        colNumSocio.setCellValueFactory(new PropertyValueFactory<SocioEntity, Integer>("id"));
        colNombreSocio.setCellValueFactory(new PropertyValueFactory<SocioEntity, String>("nombreSocio"));
        colNif.setCellValueFactory(new PropertyValueFactory<SocioEntity, String>("nif"));
        colTipo.setCellValueFactory(new PropertyValueFactory<SocioEntity, String>("tipo"));
    }

    public void onBuscarSocioButtonClick(ActionEvent event) throws SQLException, DAOException {

        vistaGridSocios.getItems().clear();
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        String nif = campoNif.getText();
        for (SocioEntity socio : listaSocios){
            if (nif.equals(socio.getNif())){
                vistaGridSocios.getItems().add(socio);
            }
        }
    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
