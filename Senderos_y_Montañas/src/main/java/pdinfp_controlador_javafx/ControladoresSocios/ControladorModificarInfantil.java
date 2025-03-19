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

import java.sql.SQLException;
import java.util.List;

public class ControladorModificarInfantil {

    private InfantilEntity socioModificar = null;
    @FXML
    private TextField campoNumCambio;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoNumSocio;
    @FXML
    private TextField campoNifTutor;
    @FXML
    private TextField campoNuevoTutor;


    public void onBuscarInfoSocioButtonClick(ActionEvent event) throws SQLException, DAOException {
        limpiarCampos();

        Integer numACambiar = Integer.valueOf(campoNumCambio.getText().trim());
        boolean socioExiste = false;

        ControladorUtilGenerico.verificarCampoNoVacio(String.valueOf(numACambiar));
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocio = hibernateDAOManager.getSocioDAO().obtenerTodos();

        for (SocioEntity socio : listaSocio){
            if (numACambiar.equals(socio.getId())&& (socio.getTipo().equals("Infantil"))){
                campoNombre.setText(socio.getNombreSocio());
                campoNumSocio.setText(String.valueOf(socio.getId()));
                socioModificar = hibernateDAOManager.getInfantilDAO().obtener(Long.valueOf(socio.getId()));
                campoNifTutor.setText(socioModificar.getNifTutor());
                socioExiste = true;

                break;
            }
        }
        if (!socioExiste){
            ControladorUtilGenerico.mostrarError("Socio no encontrado","No se ha encontrado ningún socio con ese numero ");
        }
    }

    public void onGuardarButtonClick(ActionEvent event) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        String nifEntrado = campoNuevoTutor.getText().trim();
        SocioEntity socioNuevo = null;

        if (nifEntrado.isEmpty()){
            ControladorUtilGenerico.mostrarError("Introduzca el nif del tutor","Escriba el nombre del nif del nuevo tutor");
            return;
        }
        for (SocioEntity socio : listaSocios){
            if (socio.getNif().equals(nifEntrado)){
                socioNuevo = socio;
                break;
            }
        }
        if (socioNuevo == null){
            ControladorUtilGenerico.mostrarError("Error en el socio","el nif del tutor introducido no existe");
            return;
        }
        if (socioModificar==null){
            ControladorUtilGenerico.mostrarError("Error en socio", "haga la busqueda correcta antes de grabar");
            return;
        }else{
            socioModificar.setNifTutor(socioNuevo.getNif());
            hibernateDAOManager.getInfantilDAO().modificar(socioModificar);
            ControladorUtilGenerico.mostrarExito("Socio Infantil", "el tutor del socio ha sido modificado");
        }
        limpiarCampos();

    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
    private void limpiarCampos(){
        campoNombre.setText("");
        campoNumSocio.setText("");
        campoNifTutor.setText("");
        campoNuevoTutor.setText("");
        socioModificar=null;
    }

}
