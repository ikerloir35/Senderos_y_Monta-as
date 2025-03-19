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

public class ControladorModificarFederado {

    private FederadoEntity socioModificar = null;
    @FXML
    private TextField campoNifCambio;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoNumSocio;
    @FXML
    private TextField campoNif;
    @FXML
    private TextField campoFederacion;


    public void onBuscarInfoSocioButtonClick(ActionEvent event) throws SQLException, DAOException {
        limpiarCampos();
        String nifACambiar = campoNifCambio.getText().trim();
        boolean socioExiste = false;

        ControladorUtilGenerico.verificarCampoNoVacio(nifACambiar);
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocio = hibernateDAOManager.getSocioDAO().obtenerTodos();

        for (SocioEntity socio : listaSocio){
            if (nifACambiar.equals(socio.getNif())&& (socio.getTipo().equals("Federado"))){
                campoNombre.setText(socio.getNombreSocio());
                campoNumSocio.setText(String.valueOf(socio.getId()));
                campoNif.setText(socio.getNif());
                socioExiste = true;
                socioModificar = hibernateDAOManager.getFederadoDAO().obtener(Long.valueOf(socio.getId()));
                break;
            }
        }
        if (!socioExiste){
            ControladorUtilGenerico.mostrarError("Socio no encontrado","No se ha encontrado ningún socio con ese nif ");
        }
    }

    public void onGuardarButtonClick(ActionEvent event) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<FederacionEntity> listaFederaciones = hibernateDAOManager.getFederacionDAO().obtenerTodos();

        String federacionCodigo =campoFederacion.getText().trim();
        if (federacionCodigo.isEmpty()){
            ControladorUtilGenerico.mostrarError("Introduzca la federacion","Escriba el código de la federación");
            return;
        }
        Integer federacionEntrado = ControladorUtilGenerico.limpiarIdParaDB(federacionCodigo);
        FederacionEntity federacionNuevo = null;

        if (federacionEntrado<=0){
            ControladorUtilGenerico.mostrarError("Introduzca la federacion","Escriba un código de federacion tipo FEDXXXX");
            return;
        }
        for (FederacionEntity federacion : listaFederaciones){
            if (federacion.getId().equals(federacionEntrado)){
                federacionNuevo = federacion;
                break;
            }
        }
        if (federacionNuevo == null){
            ControladorUtilGenerico.mostrarError("Error en la federación","la federación introducida no existe");
            return;
        }
        if (socioModificar==null){
            ControladorUtilGenerico.mostrarError("Error en socio", "haga la busqueda correcta antes de grabar");
            return;
        }else{
            socioModificar.setIdFederacion(federacionNuevo.getId());
            hibernateDAOManager.getFederadoDAO().modificar(socioModificar);
            ControladorUtilGenerico.mostrarExito("Socio Federado", "la federación del socio ha sido modificada");
        }
        limpiarCampos();

    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
    private void limpiarCampos(){
        campoNombre.setText("");
        campoNumSocio.setText("");
        campoNif.setText("");
        campoFederacion.setText("");
        socioModificar = null;
    }

}
