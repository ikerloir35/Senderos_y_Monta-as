package pdinfp_controlador_javafx.ControladoresInscripciones;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_Entitys.InscripcionEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ControladorNuevaInscripcion {
    @FXML
    public TextField campoSocio;
    @FXML
    public TextField campoExcursion;

    @FXML
    public void onGuardarInscripcionButtonClick(ActionEvent event) throws SQLException, DAOException {

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        String idSocio = campoSocio.getText().trim();
        String excursion = campoExcursion.getText().trim();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();

        if (idSocio.isEmpty() || excursion.isEmpty()) {
            ControladorUtilGenerico.mostrarError("Campos vacios", "Por favor ingrese todos los campos");
            return;
        }

        int idSociodb = ControladorUtilGenerico.limpiarIdParaDB(idSocio);
        int idExcursiondb = getIdExcursion(listaExcursiones, excursion);

        boolean socioExiste = ControladorUtilGenerico.verificarSocioExistePorId(listaSocios, idSocio);
        boolean excursionExiste = ControladorUtilGenerico.verificarExcursionExiste(listaExcursiones, excursion);

        boolean inscripcionDuplicada = verificarInscripcionDuplicada(listaInscripciones, idSociodb, idExcursiondb );

        if (!socioExiste) {
            ControladorUtilGenerico.mostrarError("Socio no existe", "El socio indicado no existe," +
                    " indica un nuevo socio");
        }

        if (!excursionExiste || idExcursiondb == 0) {
            ControladorUtilGenerico.mostrarError("Excursion no existe", "La excursion indicada no" +
                    " existe, indica una nueva excursion");
        }


        if (inscripcionDuplicada) {
            ControladorUtilGenerico.mostrarError("Inscripcion Duplicada", "El socio ya tiene" +
                    " una inscripcion en la excursion seleccionada");
        } else {

            if (socioExiste && excursionExiste) {

                InscripcionEntity inscripcion = new InscripcionEntity();
                inscripcion.setSocios_Idsocio(idSociodb);
                inscripcion.setExcursiones_idExcursion(idExcursiondb);

                hibernateDAOManager.getInscripcionDAO().insertar(inscripcion);

                ControladorUtilGenerico.mostrarExito("Conseguido", "Inscripcion guardada con exito");

                limpiarCampos();
            }
        }
    }


    private int getIdExcursion(List<ExcursionEntity> listaExcursiones, String excursiondb) {
        int idExcursiondb = 0;

        for (ExcursionEntity excursion: listaExcursiones) {
            if (excursiondb.equals(excursion.getNomExcursion()) && excursion.getFechaInicio().isAfter(LocalDate.now())){
                idExcursiondb = excursion.getId();
                break;
            }
        }
        return idExcursiondb;
    }


    private boolean verificarInscripcionDuplicada (List<InscripcionEntity> listaInscripciones, int idSocio, int idExcursion) {
        boolean inscripcionDuplicada = false;


        for (InscripcionEntity inscripcion: listaInscripciones) {

            if (idExcursion == inscripcion.getExcursiones_idExcursion() && idSocio == inscripcion.getSocios_Idsocio()){

                inscripcionDuplicada = true;
                break;
            }
        }
        return inscripcionDuplicada;

    }

    private void limpiarCampos() {
        campoSocio.setText("");
        campoExcursion.setText("");

    }

    @FXML
    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}
