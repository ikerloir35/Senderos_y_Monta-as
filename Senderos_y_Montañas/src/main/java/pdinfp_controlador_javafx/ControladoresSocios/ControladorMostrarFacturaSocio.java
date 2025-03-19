package pdinfp_controlador_javafx.ControladoresSocios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.*;
import pdinfp_controlador_javafx.ControladorUtilGenerico;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ControladorMostrarFacturaSocio {

    private SocioEntity socioModificar = null;
    @FXML
    private TextField campoIdCambio;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoNumSocio;
    @FXML
    private TextField campoNif;
    @FXML
    private TextField campoTipo;
    @FXML
    private TextField campoImporte;


    public void onBuscarInfoSocioButtonClick(ActionEvent event) throws SQLException, DAOException {
        limpiarCampos();
        String codigoSocio = campoIdCambio.getText();
        Integer idSocio = 0;
        if ((Objects.equals(codigoSocio, ""))||(codigoSocio.length()<4)) {
            ControladorUtilGenerico.mostrarError("Error en codigo socio","Introduzca el codigo socio tipo SOCxxxx");
            return;
        }

        idSocio = ControladorUtilGenerico.limpiarIdParaDB(codigoSocio);


        if (idSocio <= 0) {
            ControladorUtilGenerico.mostrarError("Error en codigo socio","Introduzca el codigo socio tipo SOCxxxx");
            return;
        }
        boolean socioExiste = false;

        ControladorUtilGenerico.verificarCampoNoVacio(String.valueOf(idSocio));
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocio = hibernateDAOManager.getSocioDAO().obtenerTodos();

        for (SocioEntity socio : listaSocio){
            if (idSocio.equals(socio.getId())){
                campoNombre.setText(socio.getNombreSocio());
                campoNumSocio.setText(String.valueOf(socio.getId()));
                campoNif.setText(socio.getNif());
                campoTipo.setText(socio.getTipo());
                socioExiste = true;
                socioModificar = socio;
                break;
            }
        }
        if (!socioExiste){
            ControladorUtilGenerico.mostrarError("Socio no encontrado","No se ha encontrado ningún socio con ese id ");
        }
    }

    public void onMostrarFacturaButtonClick(ActionEvent event) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();

        Integer idSocio = 0;
        LocalDate fechaExcursionImportada;
        boolean existe = false;
        boolean activo = false;
        Double cuota = 10.00;
        Double totalExcursiones = 0.00;
        Double total = 0.00;
        Double descuentoCuotaFederado = 0.05;
        Double descuentoExcurFederado = 0.10;
        Double descuentoCuotaInfantil = 0.50;
        Double contadorExcursiones = 0.00;
        int mesActual = LocalDate.now().getMonthValue();
        int mesExcursion;

        if (socioModificar == null){
            ControladorUtilGenerico.mostrarError("Error socio","Introduzca un id socio");
        }
        idSocio = socioModificar.getId();


        for (SocioEntity socioEntity: listaSocios) {
            if (Objects.equals(idSocio, socioEntity.getId())) {
                existe = true;
                if (socioEntity.getActivo() == 1) {
                    activo = true;
                    for (ExcursionEntity excursionEntity : listaExcursiones) {
                        fechaExcursionImportada = excursionEntity.getFechaInicio();
                        mesExcursion = fechaExcursionImportada.getMonthValue();

                        if (mesActual == mesExcursion) {
                            for (InscripcionEntity inscripcionEntity : listaInscripciones) {
                                if ((Objects.equals(idSocio, inscripcionEntity.getSocios_Idsocio()))) {
                                    totalExcursiones += excursionEntity.getPrecioInscripcion();
                                    contadorExcursiones++;
                                }
                            }
                        }
                    }
                    if (socioEntity.getTipo().equals("Estandar")) {
                        EstandarEntity estandarEntity = hibernateDAOManager.getEstandarDAO().obtener(Long.valueOf(idSocio));
                        SeguroEntity seguroEntity = hibernateDAOManager.getSeguroDAO().obtener(Long.valueOf(estandarEntity.getIdSeguro()));
                        total = cuota + totalExcursiones + (contadorExcursiones * seguroEntity.getPrecio());
                    } else if (socioEntity.getTipo().equals("Federado")) {
                        total = (cuota / (1 + descuentoCuotaFederado)) + (totalExcursiones / (1 + descuentoExcurFederado));
                    } else if (socioEntity.getTipo().equals("Infantil")) {
                        total = (cuota / (1 + descuentoCuotaInfantil)) + totalExcursiones;
                    }

                    campoImporte.setText(String.format("%.2f", total));
                    socioModificar = null;




                }

            }
            if(!activo){
            campoImporte.setText("Socio inactivo");
            }
        }

    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
    private void limpiarCampos(){
        campoNombre.setText("");
        campoNumSocio.setText("");
        campoNif.setText("");
        campoTipo.setText("");
        campoImporte.setText("");


    }

}
