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
import pdinfp_Entitys.*;
import pdinfp_controlador_javafx.ControladorUtilGenerico;
import pdinfp_vista.ImprimirJavaGenerics;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorMostrarEstandar {

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
        String tipo = "Estandar";
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
        EstandarEntity estandar = hibernateDAOManager.getEstandarDAO().obtener(Long.valueOf(socio.getId()));
        SeguroEntity seguro = hibernateDAOManager.getSeguroDAO().obtener(Long.valueOf(estandar.getIdSeguro()));
        Label idLabel = new Label(String.valueOf(socio.getId()));
        Label nombreLabel = new Label(socio.getNombreSocio());
        Label nifLabel = new Label(socio.getNif());
        Label seguroLabel = new Label(seguro.getNombreSeguro());

        vistaGridSocios.add(idLabel, 0, rowIndex);
        vistaGridSocios.add(nombreLabel, 1, rowIndex);
        vistaGridSocios.add(nifLabel, 2, rowIndex);
        vistaGridSocios.add(seguroLabel, 3, rowIndex);
    }
    public void limpiarVistaGridSocios() {
        vistaGridSocios.getChildren().clear();
        // Agregar encabezados nuevamente si es necesario
        vistaGridSocios.add(new Label("Id Socio"), 0, 0);
        vistaGridSocios.add(new Label("Nombre"), 1, 0);
        vistaGridSocios.add(new Label("NIF"), 2, 0);
        vistaGridSocios.add(new Label("Seguro"), 3, 0);
    }

}
