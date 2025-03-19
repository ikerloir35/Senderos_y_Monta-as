package pdinfp_controlador;


import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_vista.ImprimirJavaGenerics;
import pdinfp_vista.MenuExcursion;
import pdinfp_vista.UtilidadesVista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ControladorExcursion {

    //Menú excursión.
    public static void gestorMenuExcursion() throws DAOException, SQLException {


        boolean salirMenu = false;

        do {
            int seleccion = MenuExcursion.menuExcursion();

            switch (seleccion) {
                case 1 -> agregarExcursion();
                case 2 -> mostrarExcursion();
                case 3 -> modificarExcursion();
                case 4 -> eliminarExcursion();
                case 0 -> salirMenu = true;
                default -> UtilidadesVista.seleccionOpciones();
            }

        } while (!salirMenu);
    }


    //Método que permite añadir excursiones al HashSet de excursiones.
    public static void agregarExcursion() throws SQLException, DAOException {
        String nombre, descripcion;
        LocalDate fechaInicio = LocalDate.now();
        int numeroDias = 0;
        Double precioInscripcion = 0.00;


        nombre = MenuExcursion.preguntaNombre();
        descripcion = MenuExcursion.preguntaDescripcion();

        do {
            fechaInicio = MenuExcursion.preguntaFechaInicio();

        } while (fechaInicio == LocalDate.now());
        do {
            numeroDias = MenuExcursion.preguntaNumeroDias();

        } while (numeroDias <= 0);
        do {
            precioInscripcion = MenuExcursion.preguntaPrecioInscripcion();

        } while (precioInscripcion <= 0.00);

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        ExcursionEntity excursion = new ExcursionEntity();
        excursion.setNomExcursion(nombre);
        excursion.setDescripcion(descripcion);
        excursion.setFechaInicio(fechaInicio);
        excursion.setNumeroDias(numeroDias);
        excursion.setPrecioInscripcion(precioInscripcion);

        hibernateDAOManager.getExcursionDAO().insertar(excursion);
        UtilidadesVista.informarExito();

    }

    //Método que muestra las excursiones por rango de fechas.
    public static void mostrarExcursion() throws SQLException, DAOException {

        LocalDate fechaFinal = LocalDate.now();
        LocalDate fechaInicial = LocalDate.now();
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        do {
            fechaInicial = UtilidadesVista.fechaInicialConsulta();
        } while (fechaInicial.isEqual(LocalDate.now()));

        do {
            fechaFinal = UtilidadesVista.fechaFinalConsulta();
        } while (fechaFinal.isEqual(LocalDate.now()));

        for (ExcursionEntity excursion : listaExcursiones) {
            LocalDate fechaExcursion = excursion.getFechaInicio();

            if ((fechaExcursion.isBefore(fechaFinal)||(fechaExcursion.isEqual(fechaFinal)))&&((fechaExcursion.isAfter(fechaInicial)||(fechaExcursion.isEqual(fechaInicial))))){
                ImprimirJavaGenerics print = new ImprimirJavaGenerics(excursion);
                print.imprimir();
            }
        }
    }

    //Método que permite modificar alguna excursión, siempre y cuando, no se haya realizado a fecha hoy.
    public static void modificarExcursion() throws SQLException, DAOException {

        String codigoExcursion, descripcion;
        LocalDate fechaInicio = LocalDate.now();
        int numeroDias = -1;
        double precioInscripcion = -1.00;
        boolean modificado = false;
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        codigoExcursion = MenuExcursion.modificarCodExcurion();
        Long idExcursion = Long.valueOf(codigoExcursion.substring(codigoExcursion.length()-4));
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();

        for (ExcursionEntity excursion : listaExcursiones) {
            if (idExcursion.equals(Long.valueOf(excursion.getId()))) {
                MenuExcursion.errorExcursion();
                descripcion = MenuExcursion.preguntaDescripcion();

                if (!"0".equals(descripcion)) {
                    excursion.setDescripcion(descripcion);
                    modificado = true;
                }

                do {
                    fechaInicio = MenuExcursion.preguntaFechaInicio();
                } while (fechaInicio.isEqual(LocalDate.now()));

                if (!fechaInicio.isBefore(LocalDate.now())) {
                    excursion.setFechaInicio(fechaInicio);
                    modificado = true;
                } else {
                    MenuExcursion.errorModificarFecha();
                }

                do {
                    numeroDias = MenuExcursion.preguntaNumeroDias();
                } while (numeroDias < -1);

                if (numeroDias != 0) {
                    excursion.setNumeroDias(numeroDias);
                    modificado = true;
                }

                do {
                    precioInscripcion = MenuExcursion.preguntaPrecioInscripcion();
                } while (precioInscripcion < -1);

                if (precioInscripcion != 0) {
                    excursion.setPrecioInscripcion(precioInscripcion);
                    modificado = true;
                }

                if (modificado) {
                    hibernateDAOManager.getExcursionDAO().modificar(excursion);
                    UtilidadesVista.informarExito();
                } else {
                    UtilidadesVista.errorModificacion();
                }
                break;
            }


        }
    }

    //Método que permite eliminar una excursión. Dada la dependencia de las inscripciones, borra todas las que esten
    //vinculadas a la excursion a eliminar.
    public static void eliminarExcursion() throws SQLException, DAOException {

        boolean excursionExiste = false;
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        String codigoExcursion = MenuExcursion.eliminarExcursion();
        Long idExcursion = Long.valueOf(codigoExcursion.substring(codigoExcursion.length()-4));

        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();

            for (ExcursionEntity excursion : listaExcursiones) {
                if (idExcursion.equals(Long.valueOf(excursion.getId()))) {
                    LocalDate fechaExcursion = excursion.getFechaInicio();

                    if (fechaExcursion.isBefore(LocalDate.now()) || fechaExcursion.isEqual(LocalDate.now())) {
                        MenuExcursion.errorEliminarExcursion();
                    } else {
                        hibernateDAOManager.getExcursionDAO().eliminar(excursion);
                        UtilidadesVista.informarExito();
                        excursionExiste = true;
                        break;
                    }
                }
            }


        if (!excursionExiste) {
            MenuExcursion.errorExcursionExiste();
        }
    }
}
