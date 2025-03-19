package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.SeguroDAO;
import pdinfp_Entitys.EstandarEntity;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_Entitys.SeguroEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;

import java.sql.SQLException;
import java.util.List;

public class ControladorModificarEstandar {

    private EstandarEntity socioModificar = null;
    @FXML
    private TextField campoNifCambio;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoNumSocio;
    @FXML
    private TextField campoNif;
    @FXML
    private TextField campoSeguro;


    public void onBuscarInfoSocioButtonClick(ActionEvent event) throws SQLException, DAOException {
        limpiarCampos();
        String nifACambiar = campoNifCambio.getText().trim();
        boolean socioExiste = false;

        ControladorUtilGenerico.verificarCampoNoVacio(nifACambiar);
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocio = hibernateDAOManager.getSocioDAO().obtenerTodos();

        for (SocioEntity socio : listaSocio){
            if (nifACambiar.equals(socio.getNif())&& (socio.getTipo().equals("Estandar"))){
                campoNombre.setText(socio.getNombreSocio());
                campoNumSocio.setText(String.valueOf(socio.getId()));
                campoNif.setText(socio.getNif());
                socioExiste = true;
                socioModificar = hibernateDAOManager.getEstandarDAO().obtener(Long.valueOf(socio.getId()));
                break;
            }
        }
        if (!socioExiste){
            ControladorUtilGenerico.mostrarError("Socio no encontrado","No se ha encontrado ningún socio con ese nif ");
        }
    }

    public void onGuardarButtonClick(ActionEvent event) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SeguroEntity> listaSeguros = hibernateDAOManager.getSeguroDAO().obtenerTodos();
        String seguroEntrado = campoSeguro.getText().trim();
        SeguroEntity seguroNuevo = null;

        if (seguroEntrado.isEmpty()){
            ControladorUtilGenerico.mostrarError("Introduzca el seguro","Escriba el nombre del seguro");
            return;
        }
        for (SeguroEntity seguro : listaSeguros){
            if (seguro.getNombreSeguro().equals(seguroEntrado)){
                seguroNuevo = seguro;
                break;
            }
        }
        if (seguroNuevo == null){
            ControladorUtilGenerico.mostrarError("Error en el seguro","el seguro introducido no existe");
            return;
        }
        if (socioModificar==null){
            ControladorUtilGenerico.mostrarError("Error en socio", "haga la busqueda correcta antes de grabar");
            return;
        }else{
            socioModificar.setIdSeguro(seguroNuevo.getId());
            hibernateDAOManager.getEstandarDAO().modificar(socioModificar);
            ControladorUtilGenerico.mostrarExito("Socio Estandar", "el seguro del socio ha sido modificado");
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
        campoSeguro.setText("");
        socioModificar=null;
    }

}
