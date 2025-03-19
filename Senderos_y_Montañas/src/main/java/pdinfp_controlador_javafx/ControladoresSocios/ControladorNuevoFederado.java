package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.*;
import pdinfp_controlador_javafx.ControladorUtilGenerico;


import java.sql.SQLException;
import java.util.List;

import static pdinfp_controlador_javafx.ControladorUtilGenerico.comprobarSocioExiste;

public class ControladorNuevoFederado {

    //atributos fxml relacionados con los fx:id designados en nuevoFederado.fxml
    @FXML
    public TextField campoNif;

    @FXML
    public TextField campoCodigoFederacion;

    @FXML
    public TextField campoNombreNuevoFederado;

    @FXML
    protected void onGuardarButtonClick(ActionEvent event) throws SQLException, DAOException {

        String nombre = campoNombreNuevoFederado.getText().trim();
        String nif = campoNif.getText().trim();
        String codigoFederacion = campoCodigoFederacion.getText().trim();

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SeguroEntity> listaSeguros = hibernateDAOManager.getSeguroDAO().obtenerTodos();

        if (nombre.isEmpty() || nif.isEmpty() || codigoFederacion.isEmpty() ) {
            ControladorUtilGenerico.mostrarError("Valores vacios", "Por favor, rellena todos los campos");
            return;
        }

        if (comprobarSocioExiste(nif, hibernateDAOManager.getSocioDAO().obtenerTodos())) {
            ControladorUtilGenerico.mostrarError("El socio ya existe", "Este nif ya esta registrado");
            return;
        }

        Long idFederacion = Long.parseLong(codigoFederacion.substring(codigoFederacion.length() - 4));
        FederacionEntity federacion = hibernateDAOManager.getFederacionDAO().obtener(idFederacion);
        if (federacion == null) {
            ControladorUtilGenerico.mostrarError("Error en la federación","El código de federación no existe");
            return;
        }
        SocioEntity nuevoSocio = new SocioEntity();

        nuevoSocio.setNombreSocio(nombre);
        nuevoSocio.setNif(nif);
        nuevoSocio.setTipo("Federado");
        nuevoSocio.setActivo((byte) 1);


        nuevoSocio = hibernateDAOManager.getSocioDAO().insertar(nuevoSocio);

        FederadoEntity federado = new FederadoEntity();
        federado.setId(nuevoSocio.getId());
        federado.setIdFederacion(federacion.getId());
        federado = hibernateDAOManager.getFederadoDAO().insertar(federado);

        ControladorUtilGenerico.mostrarExito("Conseguido", "Socio Federado guardado con exito");

        limpiarCampos();
    }
    private void limpiarCampos(){
        campoNombreNuevoFederado.setText("");
        campoNif.setText("");
        campoCodigoFederacion.setText("");

    }

    @FXML
    protected void onVolverMenuPrincipalButtonClick(ActionEvent event) {

        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
