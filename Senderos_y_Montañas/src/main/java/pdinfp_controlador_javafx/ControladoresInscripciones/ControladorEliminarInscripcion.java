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

public class ControladorEliminarInscripcion {

    @FXML
    private TextField campoSeleccionarExcursion;
    @FXML
    private TextField campoSeleccionarSocio;

    @FXML
    protected void onEliminarButtonClick(ActionEvent event) throws SQLException, DAOException {

        String excursionSeleccionada = campoSeleccionarExcursion.getText().trim();
        String socioSeleccionado = campoSeleccionarSocio.getText().trim();

        if (excursionSeleccionada.isEmpty() || socioSeleccionado.isEmpty()) {
            ControladorUtilGenerico.mostrarError("Valores vacios", "Por favor, rellena todos los campos");
            return;
        }

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();


        int idExcursionSeleccionada = getIdExcursion(listaExcursiones, excursionSeleccionada);
        int idSocioSeleccionado = ControladorUtilGenerico.limpiarIdParaDB(socioSeleccionado);

        if (idSocioSeleccionado <= 0) {
            ControladorUtilGenerico.mostrarError("Socio no existe", "El socio indicado no existe," +
                    " indica un nuevo socio");
        }

        boolean excursionExiste = verificarValidezExcursion(listaExcursiones, idExcursionSeleccionada);
        boolean socioExiste = ControladorUtilGenerico.verificarSocioExistePorId(listaSocios, socioSeleccionado);

        if (!excursionExiste || idExcursionSeleccionada == 0) {
            ControladorUtilGenerico.mostrarError("Excursion no existe", "La excursion indicada no" +
                    " existe o es antigua, indica una nueva excursion");
        }


        if (socioExiste && excursionExiste) {
            for (InscripcionEntity inscripcion : listaInscripciones) {
                if (idExcursionSeleccionada == (inscripcion.getExcursiones_idExcursion()) &&
                        idSocioSeleccionado == inscripcion.getSocios_Idsocio()) {
                    hibernateDAOManager.getInscripcionDAO().eliminar(inscripcion);
                    ControladorUtilGenerico.mostrarExito("Conseguido", "Inscrición eliminada con exito");
                }
            }
        }

        limpiarCampos();
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

    private boolean verificarValidezExcursion (List<ExcursionEntity> listaExcursionesEntera, int excursionBuscada) {
        LocalDate fechaInicioExcursion = null;
        boolean excursionValida = false;

        if (excursionBuscada > 0) {
            for (ExcursionEntity excursion : listaExcursionesEntera) {
                if (excursionBuscada == (excursion.getId())) {
                    fechaInicioExcursion = excursion.getFechaInicio();

                }
            }
            if (fechaInicioExcursion.isAfter(LocalDate.now())) {
                excursionValida = true;
            }
        }
        return excursionValida;
    }

    private void limpiarCampos(){
        campoSeleccionarExcursion.setText("");
        campoSeleccionarSocio.setText("");
    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}



