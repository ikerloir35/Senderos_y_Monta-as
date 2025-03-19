package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.EstandarEntity;
import pdinfp_Entitys.FederacionEntity;
import pdinfp_Entitys.FederadoEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorMostrarFederado {

    @FXML
    private GridPane vistaGridSocios;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void initialize() {
        limpiarVistaGridSocios(); // Agrega los encabezados
    }

    public void onBuscarSocioButtonClick(ActionEvent event) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        String tipo = "Federado";
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        int i = 1;
        for (SocioEntity socio : listaSocios) {
            if (tipo.equals(socio.getTipo()) && (socio.getActivo() == 1)) {

               agregarFilaAGrid(socio,i);
                i++;
            }
        }

    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }

    private void agregarFilaAGrid(SocioEntity socio, int rowIndex) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        FederadoEntity federado = hibernateDAOManager.getFederadoDAO().obtener(Long.valueOf(socio.getId()));
        FederacionEntity federacion = hibernateDAOManager.getFederacionDAO().obtener(Long.valueOf(federado.getIdFederacion()));
        Label idLabel = new Label(String.valueOf(socio.getId()));
        Label nombreLabel = new Label(socio.getNombreSocio());
        Label nifLabel = new Label(socio.getNif());
        Label federacionLabel = new Label(federacion.getNombreFederacion());

        vistaGridSocios.add(idLabel, 0, rowIndex);
        vistaGridSocios.add(nombreLabel, 1, rowIndex);
        vistaGridSocios.add(nifLabel, 2, rowIndex);
        vistaGridSocios.add(federacionLabel, 3, rowIndex);
    }
    public void limpiarVistaGridSocios() {
        vistaGridSocios.getChildren().clear();
        // Agregar encabezados nuevamente si es necesario
        vistaGridSocios.add(new Label("Id Socio"), 0, 0);
        vistaGridSocios.add(new Label("Nombre"), 1, 0);
        vistaGridSocios.add(new Label("NIF"), 2, 0);
        vistaGridSocios.add(new Label("Federación"), 3, 0);
    }
}
