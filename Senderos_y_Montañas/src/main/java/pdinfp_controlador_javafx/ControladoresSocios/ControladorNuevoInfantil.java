package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.EstandarEntity;
import pdinfp_Entitys.InfantilEntity;
import pdinfp_Entitys.SeguroEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;
import pdinfp_vista.UtilidadesVista;

import java.sql.SQLException;
import java.util.List;

import static pdinfp_controlador_javafx.ControladorUtilGenerico.comprobarSocioExiste;

public class ControladorNuevoInfantil {

    //atributos fxml relacionados con los fx:id designados en nuevoEstandar.fxml
    @FXML
    public TextField campoNifTutor;

    @FXML
    private TextField campoNombreNuevoInfantil;

    @FXML
    protected void onGuardarButtonClick(ActionEvent event) throws SQLException, DAOException {

        String nombre = campoNombreNuevoInfantil.getText().trim();
        String nifTutor = campoNifTutor.getText().trim();
        SocioEntity tutor = null;

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();


        if (nombre.isEmpty() || nifTutor.isEmpty() ) {

            ControladorUtilGenerico.mostrarError("Valores vacios", "Por favor, rellena todos los campos");
            return;
        }

        for (SocioEntity socio : listaSocios) {
            if (socio.getNif().equals(nifTutor)) {
                tutor = socio;
            }
        }

        if (tutor == null) {
            ControladorUtilGenerico.mostrarError("Tutor no encontrado", "El nif del tutor no existe");
            return;
        }
        SocioEntity nuevoSocio = new SocioEntity();
        nuevoSocio.setNombreSocio(nombre);
        nuevoSocio.setTipo("Infantil");
        nuevoSocio.setNif("");
        nuevoSocio.setActivo((byte) 1);


        nuevoSocio = hibernateDAOManager.getSocioDAO().insertar(nuevoSocio);

        InfantilEntity infantil = new InfantilEntity();
        infantil.setId(nuevoSocio.getId());
        infantil.setNifTutor(nifTutor);
        infantil = hibernateDAOManager.getInfantilDAO().insertar(infantil);

        ControladorUtilGenerico.mostrarExito("Conseguido", "Socio infantil guardado con exito");

        limpiarCampos();
    }
    private void limpiarCampos(){
        campoNombreNuevoInfantil.setText("");
        campoNifTutor.setText("");


    }

    @FXML
    protected void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
