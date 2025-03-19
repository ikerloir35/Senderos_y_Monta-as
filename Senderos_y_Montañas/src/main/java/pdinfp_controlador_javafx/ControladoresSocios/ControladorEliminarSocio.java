package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;

import java.sql.SQLException;
import java.util.List;

public class ControladorEliminarSocio {

    private SocioEntity socioModificar = null;
    @FXML
    private TextField campoNifCambio;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoNumSocio;
    @FXML
    private TextField campoNif;


    public void onBuscarInfoSocioButtonClick(ActionEvent event) throws SQLException, DAOException {


        String nifACambiarOrigen = campoNifCambio.getText().trim();

        if ((nifACambiarOrigen.equals(""))||(nifACambiarOrigen.length()<4)){

            ControladorUtilGenerico.mostrarError("Busqueda incorrecta","Introduzca un id correcto tipo SOCxxxx");
        }
        Integer nifACambiar = ControladorUtilGenerico.limpiarIdParaDB(nifACambiarOrigen);

        boolean socioExiste = false;

        ControladorUtilGenerico.verificarCampoNoVacio(String.valueOf(nifACambiar));

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocio = hibernateDAOManager.getSocioDAO().obtenerTodos();

        for (SocioEntity socio : listaSocio){

            if ((nifACambiar.equals(socio.getId()))){

                campoNombre.setText(socio.getNombreSocio());
                campoNumSocio.setText(String.valueOf(socio.getId()));
                campoNif.setText(socio.getNif());
                socioModificar = socio;
                socioExiste = true;
                break;
            }
        }
        if (!socioExiste){

            ControladorUtilGenerico.mostrarError("Socio no encontrado","No se ha encontrado ningún socio con ese id ");

        }
    }

    public void onEliminarButtonClick(ActionEvent event) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();


        if (socioModificar==null){
            ControladorUtilGenerico.mostrarError("Error en socio", "haga la busqueda correcta antes de grabar");
            return;
        }else{

            if (socioModificar.getActivo()==1){
            hibernateDAOManager.getSocioDAO().eliminar(socioModificar);
            ControladorUtilGenerico.mostrarExito("Socio Eliminado", "el socio ha sido desactivado");
            }else{
                ControladorUtilGenerico.mostrarError("Socio desactivado", "Este socio ya esta desactivado");
            }

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
        campoNifCambio.setText("");
        socioModificar = null;


    }

}
