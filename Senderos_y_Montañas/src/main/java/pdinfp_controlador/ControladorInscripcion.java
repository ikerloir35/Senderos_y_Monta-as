package pdinfp_controlador;

import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_Entitys.InscripcionEntity;
import pdinfp_Entitys.SocioEntity;
import pdinfp_vista.ImprimirJavaGenerics;
import pdinfp_vista.MenuInscripcion;
import pdinfp_vista.UtilidadesVista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ControladorInscripcion {


    public static void gestorMenuInscripcion() throws DAOException, SQLException {

        boolean salirMenu = false;

        do {
            int seleccion = MenuInscripcion.gestionInscripcion();

                switch (seleccion) {
                    case 1 -> agregarInscripcion();
                    case 2 -> mostrarInscripcion();
                    case 3 -> eliminarInscripcion();
                    case 0 -> salirMenu = true;
                    default -> UtilidadesVista.seleccionOpciones();
                }

        } while (!salirMenu);

    }
    public static void agregarInscripcion() throws SQLException, DAOException {
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        String codigoExcursion, codigoSocio;
        boolean existeExcursion = false;
        boolean existeSocio = false;

        codigoExcursion = MenuInscripcion.agregarInscripcion();
        String ultimasCuatro = codigoExcursion.substring(codigoExcursion.length()-4);
        Long idExcursion = Long.parseLong(ultimasCuatro);

        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();

        for (ExcursionEntity excursion : listaExcursiones) {
            if (idExcursion.equals(Long.valueOf(excursion.getId()))) {
                codigoSocio = UtilidadesVista.preguntaNumeroSocio();
                Long idSocio = Long.parseLong(codigoSocio.substring(codigoSocio.length() - 4));
                List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();

                existeExcursion = true;

                List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();

                for (InscripcionEntity inscripcion : listaInscripciones) {
                    if (idSocio.equals(Long.valueOf((inscripcion.getSocios_Idsocio())))) {
                        MenuInscripcion.mensajeInscripcionSocio(codigoSocio);
                        return;
                    }
                }

                for (SocioEntity socio : listaSocios) {
                    if (idSocio.equals(Long.valueOf(socio.getId()))) {

                        InscripcionEntity inscripcionNueva = new InscripcionEntity();
                        inscripcionNueva.setSocios_Idsocio(socio.getId());
                        inscripcionNueva.setExcursiones_idExcursion(excursion.getId());
                        hibernateDAOManager.getInscripcionDAO().insertar(inscripcionNueva);
                        UtilidadesVista.informarExito();
                        return;
                    }
                }

                if (!existeSocio) {
                    UtilidadesVista.socioNoEncontrado();
                    UtilidadesVista.errorCancelacion();
                }
            }
        }

        if (!existeExcursion) {
            MenuInscripcion.codExcursionNotFound();
        }


    }
    public static void mostrarInscripcion() throws SQLException, DAOException {

        String codigoSocio;
        int opcionSocio = 0;
        int opcionFecha = 0;
        int contador = 0;

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        LocalDate fechaInicialDate = LocalDate.now();
        LocalDate fechaFinalDate = LocalDate.now();
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        do {
            opcionSocio = MenuInscripcion.mostrarInscripcionesXSocio();

        }while((opcionSocio < 1) || (opcionSocio > 2));
        do {
            opcionFecha = MenuInscripcion.mostrarInscripcionesXFecha();

        }while((opcionFecha < 1) || (opcionFecha > 2));

        if (opcionFecha == 1) {

            do {

                fechaInicialDate = UtilidadesVista.fechaInicialConsulta();

            }while (fechaInicialDate.equals(LocalDate.now()));

            do {
                fechaFinalDate = UtilidadesVista.fechaFinalConsulta();

            }while (fechaFinalDate.equals(LocalDate.now()));

        }

        if ((opcionFecha == 2) && (opcionSocio == 2)) {
            MenuInscripcion.mensajeFiltro();


            for (ExcursionEntity excursion : listaExcursiones) {

                ImprimirJavaGenerics print = new ImprimirJavaGenerics(excursion);
                print.imprimir();

                for (InscripcionEntity inscripcion : listaInscripciones) {
                    if ((inscripcion.getExcursiones_idExcursion().equals(excursion.getId()))){

                        contador = contador + 1;
                        MenuInscripcion.printInscripcion(inscripcion,contador);
                    }
                }
            }
            return;
        }
        if ((opcionSocio == 1) && (opcionFecha == 2)) {
            codigoSocio = UtilidadesVista.preguntaNumeroSocio();
            String ultimasCuatro = codigoSocio.substring(codigoSocio.length()-4);
            Long idSocio = Long.parseLong(ultimasCuatro);


            for (InscripcionEntity inscripcion : listaInscripciones) {

                if (Long.valueOf(inscripcion.getSocios_Idsocio()).equals(idSocio)){

                    contador = contador +1;
                    MenuInscripcion.printInscripcion(inscripcion,contador);
                }
            }

            return;
        }
        if ((opcionSocio == 2) && (opcionFecha == 1)) {
            for (ExcursionEntity excursion : listaExcursiones){
                LocalDate fechaExcursion = excursion.getFechaInicio();
                if (((fechaInicialDate.isBefore(fechaExcursion)) || (fechaInicialDate.isEqual(fechaExcursion))) && ((fechaFinalDate.isAfter(fechaExcursion)) || (fechaFinalDate.isEqual(fechaExcursion)))) {
                    ImprimirJavaGenerics print = new ImprimirJavaGenerics(excursion);
                    print.imprimir();
                    for (InscripcionEntity inscripcion : listaInscripciones) {
                        if (inscripcion.getExcursiones_idExcursion().equals(excursion.getId())){

                            contador = contador +1;
                            MenuInscripcion.printInscripcion(inscripcion,contador);
                        }
                    }
                }
            }
        }
    }

    public static void eliminarInscripcion() throws SQLException, DAOException {

        String codigoExcursion, codigoInscripcion;

        boolean exitoExcursion = false;
        boolean exitoInscripcion =false;
        codigoExcursion = MenuInscripcion.borrarInscripcionExcursion();
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        Long idExcursion = Long.parseLong(codigoExcursion.substring(codigoExcursion.length()-4));
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();

        for (ExcursionEntity excursion : listaExcursiones) {
            if (idExcursion.equals(Long.valueOf(excursion.getId()))) {

                exitoExcursion = true;
                codigoInscripcion = MenuInscripcion.borrarInscripcion();
                List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();
                Long idInscripcion = Long.parseLong(codigoInscripcion.substring(codigoInscripcion.length() - 4));

                for (InscripcionEntity inscripcion : listaInscripciones) {

                    if (idInscripcion.equals(Long.valueOf(inscripcion.getId())) && !exitoInscripcion) {
                        hibernateDAOManager.getInscripcionDAO().eliminar(inscripcion);
                        UtilidadesVista.informarExito();
                        exitoInscripcion = true;
                        break;
                    }
                }
            }
        }
        if (!exitoExcursion){
            MenuInscripcion.codExcursionNotFound();
        }else {
            if (!exitoInscripcion){
                MenuInscripcion.codInscripcionNotFound();
            }

        }

    }
}

