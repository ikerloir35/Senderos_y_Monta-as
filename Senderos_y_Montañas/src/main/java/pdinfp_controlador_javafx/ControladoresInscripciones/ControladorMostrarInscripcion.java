package pdinfp_controlador_javafx.ControladoresInscripciones;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_Entitys.InscripcionEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_controlador_javafx.ControladorUtilGenerico;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorMostrarInscripcion {

    @FXML
    private TextField campoNumeroSocio;
    @FXML
    private TextField campoNombreExcursion;

    @FXML
    public void onBuscarInscripcionPorSocioButtonClick(ActionEvent event) throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        //check socio TextFile is not empty
        String numeroSocio = campoNumeroSocio.getText().trim();

        if (numeroSocio.isEmpty()) {
            ControladorUtilGenerico.mostrarError("Campo Vacio", "Debes indicar un numero de Socio");
            return;
        }

        //check if exist in socios
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();


        boolean numeroSocioExite = ControladorUtilGenerico.verificarSocioExistePorId(listaSocios, numeroSocio);

        //if exists
        if (numeroSocioExite){

            int numeroSociodb = ControladorUtilGenerico.limpiarIdParaDB(numeroSocio);
            List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();
            List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
            ArrayList<Integer> listaExcursionesSocio = new ArrayList<>();

            for (InscripcionEntity inscripcion : listaInscripciones) {
                for (ExcursionEntity excursion: listaExcursiones){
                    if (inscripcion.getExcursiones_idExcursion().equals(excursion.getId())) {
                        LocalDate fechaExcursion = excursion.getFechaInicio();
                        if (numeroSociodb == inscripcion.getSocios_Idsocio() && fechaExcursion.isAfter(LocalDate.now())){
                            listaExcursionesSocio.add(inscripcion.getExcursiones_idExcursion());
                        }
                    }
                }
            }

            if (listaExcursionesSocio.isEmpty()){
                ControladorUtilGenerico.mostrarError("Socio sin inscripciones", "El socio indicado" +
                        " no tiene inscripciones activas");
            } else {

                ArrayList<ExcursionEntity> listaExcursionesSocioDetalles = new ArrayList<>();
                for (Integer idExcrusion: listaExcursionesSocio) {
                    for (ExcursionEntity excursion: listaExcursiones){
                        if (idExcrusion.equals(excursion.getId())) {
                            listaExcursionesSocioDetalles.add(excursion);
                            break;
                        }
                    }
                }
                iniciarVentanaSecundariaBusquedaSocio(event, listaExcursionesSocioDetalles, numeroSocio,
                        "/pdinfp_vista_javafx/inscripciones_vista/mostrar_Inscripciones_Socio.fxml");
            }
        }
    }

    @FXML
    public void onBuscarInscripcionPorExcursionButtonClick(ActionEvent event) throws SQLException, DAOException {

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        //check campoNombreExcursion TextFile is not empty
        String nombreExcursion = campoNombreExcursion.getText().trim();
        if (nombreExcursion.isEmpty()) {
            ControladorUtilGenerico.mostrarError("Campo Vacio", "Debes indicar un nombre de excursion");
            return;
        }

        //check if exist in socios
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        boolean nombreExcursionExite = ControladorUtilGenerico.verificarExcursionExiste(listaExcursiones, nombreExcursion);
        ArrayList<Integer> listaSocioIdEnExcursion = new ArrayList<>();
        ArrayList<SocioEntity> listaSociosEnExcursion = new ArrayList<>();

        //if exists
        if (!nombreExcursionExite) {
            ControladorUtilGenerico.mostrarError("Error Excursion", "El nombre de la excursion no existe");
        } else {
            int idExcursionSolicitada = 0;
            for (ExcursionEntity excursion : listaExcursiones) {
                if (nombreExcursion.equals(excursion.getNomExcursion())) {
                    idExcursionSolicitada = excursion.getId();
                    break;
                }
            }
            for (InscripcionEntity inscripcion : listaInscripciones) {
                if (idExcursionSolicitada == inscripcion.getExcursiones_idExcursion()) {
                    listaSocioIdEnExcursion.add(inscripcion.getSocios_Idsocio());
                }
            }
            for (SocioEntity socio : listaSocios) {
                for (Integer idsocio : listaSocioIdEnExcursion) {
                    if (idsocio.equals(socio.getId())) {
                        listaSociosEnExcursion.add(socio);
                    }
                }
            }
            if (listaSociosEnExcursion.isEmpty()) {
                ControladorUtilGenerico.mostrarError("Excursion sin inscripciones", "La excursion" +
                        " solicitada no tiene inscripciones activas");
            } else {
                iniciarVentanaSecundariaBusquedaExcursion(event, listaSociosEnExcursion, nombreExcursion,
                        "/pdinfp_vista_javafx/excursiones_vista/mostrar_Socios_Inscritos_Excursion.fxml");
            }
        }
    }

    public static void iniciarVentanaSecundariaBusquedaSocio(ActionEvent event, ArrayList listaEntidades, String datoFacilitadoUsuario, String nombreVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorUtilGenerico.class.getResource(nombreVentana));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Inicializamos el controlador para pasarle los datos generados
            ControladorMostrarInscripcionSocio controller = loader.getController();
            controller.initData(listaEntidades, datoFacilitadoUsuario);

            //Abrir la nueva ventana
            Stage newStage = new Stage();
            newStage.setTitle("Senderos y Montañas");
            newStage.setScene(scene);
            newStage.show();

            //Cerrar la ventana anterior
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void iniciarVentanaSecundariaBusquedaExcursion(ActionEvent event, ArrayList listaEntidades, String datoFacilitadoUsuario, String nombreVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorUtilGenerico.class.getResource(nombreVentana));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Inicializamos el controlador para pasarle los datos generados
            ControladorMostrarSociosInscritos controller = loader.getController();
            controller.initData(listaEntidades, datoFacilitadoUsuario);

            //Abrir la nueva ventana
            Stage newStage = new Stage();
            newStage.setTitle("Senderos y Montañas");
            newStage.setScene(scene);
            newStage.show();

            //Cerrar la ventana anterior
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onVolverMenuPrincipalButtonClick(ActionEvent event) {
        ControladorUtilGenerico.volverMenuPrincipal(event);
    }
}

