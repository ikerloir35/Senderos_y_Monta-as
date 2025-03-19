package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.EstandarEntity;
import pdinfp_Entitys.SeguroEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;


import java.sql.SQLException;
import java.util.List;

import static pdinfp_controlador_javafx.ControladorUtilGenerico.comprobarSocioExiste;

public class ControladorNuevoEstandar {

    //atributos fxml relacionados con los fx:id designados en nuevoEstandar.fxml
    @FXML
    public TextField campoNif;

    @FXML
    public TextField campoNombreSeguro;

    @FXML
    private TextField campoNombreNuevoEstandar;

    @FXML
    protected void onGuardarButtonClick(ActionEvent event) throws SQLException, DAOException {

        String nombre = campoNombreNuevoEstandar.getText().trim();
        String nif = campoNif.getText().trim();
        String nombreSeguro = campoNombreSeguro.getText().trim();

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SeguroEntity> listaSeguros = hibernateDAOManager.getSeguroDAO().obtenerTodos();

        if (nombre.isEmpty() || nif.isEmpty() || nombreSeguro.isEmpty() ) {
            ControladorUtilGenerico.mostrarError("Valores vacios", "Por favor, rellena todos los campos");
            return;
        }

        if (comprobarSocioExiste(nif, hibernateDAOManager.getSocioDAO().obtenerTodos())) {
            ControladorUtilGenerico.mostrarError("El socio ya existe", "Este nif ya esta registrado");
            return;
        }

        SeguroEntity seguroOK = null;
        for (SeguroEntity seguro : listaSeguros) {
            if (seguro.getNombreSeguro().equals(nombreSeguro)) {
                seguroOK = seguro;
            }

        }
        if (seguroOK == null) {
            ControladorUtilGenerico.mostrarError("Error en el seguro","El nombre del seguro no existe");
            return;
        }
        SocioEntity nuevoSocio = new SocioEntity();
        nuevoSocio.setNombreSocio(nombre);
        nuevoSocio.setNif(nif);
        nuevoSocio.setTipo("Estandar");
        nuevoSocio.setActivo((byte) 1);

        nuevoSocio = hibernateDAOManager.getSocioDAO().insertar(nuevoSocio);

        EstandarEntity estandar = new EstandarEntity();
        estandar.setId(nuevoSocio.getId());
        estandar.setIdSeguro(seguroOK.getId());
        estandar = hibernateDAOManager.getEstandarDAO().insertar(estandar);

        ControladorUtilGenerico.mostrarExito("Conseguido", "Socio estandar guardado con exito");

        limpiarCampos();
    }
    private void limpiarCampos(){
        campoNombreNuevoEstandar.setText("");
        campoNif.setText("");
        campoNombreSeguro.setText("");

    }

    @FXML
    protected void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
