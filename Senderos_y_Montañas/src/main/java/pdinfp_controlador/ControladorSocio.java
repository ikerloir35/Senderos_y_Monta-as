//controladorDAO de la sección socio, interactua con el MenuSocio y UtilidadesVista.

package pdinfp_controlador;


import pdinfp_DAO.HibernateDAO.HibernateDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_Entitys.*;
import pdinfp_vista.ImprimirJavaGenerics;
import pdinfp_vista.MenuSocio;
import pdinfp_vista.UtilidadesVista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class ControladorSocio {

    //Gestión del menusocio. Llama a los distintos métodos según la selección del usuario.
    public static void gestorMenuSocio() throws DAOException, SQLException {
        boolean salirMenu = false;

        do {
            int seleccion = MenuSocio.menuGestionSocio();

                switch (seleccion) {
                    case 1 -> agregarSocio();
                    case 2 -> buscarSocios();
                    case 3 -> modificarSocio();
                    case 4 -> eliminarSocio();
                    case 5 -> mostrarFacturaSocio();
                    case 0 -> salirMenu = true;
                    default -> UtilidadesVista.seleccionOpciones();
                }
        } while (!salirMenu);
    }

    //Método agregar Socio, permite inscribir un nuevo socio en el programa.
    public static void agregarSocio() throws DAOException, SQLException {

        //Vista menu opciones agregar Socio
        boolean salirMenu = false;
        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        List<SeguroEntity> listaSeguros = hibernateDAOManager.getSeguroDAO().obtenerTodos();


        do {
            int tipoSocio = MenuSocio.anadirSocio();
            switch (tipoSocio) {
                case 1 -> {
                    String nombre = UtilidadesVista.preguntaNombreSocio();
                    String nif = UtilidadesVista.preguntaNif();
                    if (comprobarSocioExiste(nif, hibernateDAOManager.getSocioDAO().obtenerTodos())) {
                        UtilidadesVista.registroNif();
                        return;
                    }
                    String nombreSeguro = MenuSocio.preguntaSeguro();

                    SeguroEntity seguroOK = null;
                    for (SeguroEntity seguro : listaSeguros) {
                        if (seguro.getNombreSeguro().equals(nombreSeguro)) {
                            seguroOK = seguro;
                        }


                    }
                    if (seguroOK == null) {
                        MenuSocio.errorSeguro();
                        return;
                    }
                    SocioEntity nuevoSocio = new SocioEntity();
                    nuevoSocio.setNombreSocio(nombre);
                    nuevoSocio.setNif(nif);
                    nuevoSocio.setTipo("Estandar");
                    nuevoSocio.setActivo((byte) 1);

                    nuevoSocio = hibernateDAOManager.getSocioDAO().insertar(nuevoSocio);

                    EstandarEntity estandar = new EstandarEntity();
                    estandar.setId(nuevoSocio.getId());
                    estandar.setIdSeguro(seguroOK.getId());
                    estandar = hibernateDAOManager.getEstandarDAO().insertar(estandar);
                    UtilidadesVista.informarExito();
                }
                case 2 -> {
                    String nombre = UtilidadesVista.preguntaNombreSocio();
                    String nif = UtilidadesVista.preguntaNif();

                    if (comprobarSocioExiste(nif, hibernateDAOManager.getSocioDAO().obtenerTodos())) {
                        UtilidadesVista.registroNif();
                        return;
                    }
                    String codigoFederacion = MenuSocio.preguntaCodigoFederacion();
                    Long idFederacion = Long.parseLong(codigoFederacion.substring(codigoFederacion.length() - 4));
                    FederacionEntity federacion = hibernateDAOManager.getFederacionDAO().obtener(idFederacion);
                    if (federacion == null) {
                        MenuSocio.errorFederacion();
                        return;
                    }
                    SocioEntity nuevoSocio = new SocioEntity();

                    nuevoSocio.setNombreSocio(nombre);
                    nuevoSocio.setNif(nif);
                    nuevoSocio.setTipo("Federado");
                    nuevoSocio.setActivo((byte) 1);


                    nuevoSocio = hibernateDAOManager.getSocioDAO().insertar(nuevoSocio);

                    FederadoEntity federado = new FederadoEntity();
                    federado.setId(nuevoSocio.getId());
                    federado.setIdFederacion(federacion.getId());
                    federado = hibernateDAOManager.getFederadoDAO().insertar(federado);
                    UtilidadesVista.informarExito();
                }

                case 3 -> {
                    SocioEntity tutor = null;

                    String nombre = UtilidadesVista.preguntaNombreSocio();
                    String nifTutor = MenuSocio.preguntaNifTutor();

                    for (SocioEntity socio : listaSocios) {
                        if (socio.getNif().equals(nifTutor)) {
                            tutor = socio;
                        }
                    }

                    if (tutor == null) {
                        UtilidadesVista.errorTutor();
                        return;
                    }
                    SocioEntity nuevoSocio = new SocioEntity();
                    nuevoSocio.setNombreSocio(nombre);
                    nuevoSocio.setTipo("Infantil");
                    nuevoSocio.setNif("");
                    nuevoSocio.setActivo((byte) 1);


                    nuevoSocio = hibernateDAOManager.getSocioDAO().insertar(nuevoSocio);

                    InfantilEntity infantil = new InfantilEntity();
                    infantil.setId(nuevoSocio.getId());
                    infantil.setNifTutor(nifTutor);
                    infantil = hibernateDAOManager.getInfantilDAO().insertar(infantil);
                    UtilidadesVista.informarExito();
                }
                case 0 -> salirMenu = true;
                default -> UtilidadesVista.seleccionOpciones();
            }

        } while (!salirMenu);
    }



    //Metodo para comprobar si el socio ya existe por nif. Para socios Estandar y Federados.
    public static boolean comprobarSocioExiste(String nif, List<SocioEntity> listaSocios) {
        for (SocioEntity socioEntity : listaSocios) {
            if (nif.equals(String.valueOf(socioEntity.getNif()))) {
                return true;
            }
        }
        return false;
    }

    //Metodo para imprimir los socios Activos x Tipo
    private static void printSociosXTipo(HibernateDAOManager hibernateDAOManager, String tipo) throws DAOException {
        List<SocioEntity> socios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        for (SocioEntity socio : socios) {
            if (tipo.equals(socio.getTipo()) && (socio.getActivo() == 1)) {
                ImprimirJavaGenerics imprimirJavaGenerics = new ImprimirJavaGenerics<>(socio);
                imprimirJavaGenerics.imprimir();
            }
        }
    }
    //Metodo para imprimir socio Activos x nif
    private static void printSocioXNif(HibernateDAOManager hibernateDAOManager, String nif) throws DAOException {
        List<SocioEntity> socios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        for (SocioEntity socioEntity : socios) {
            if (nif.equals(socioEntity.getNif()) && socioEntity.getActivo() == 1) {
                ImprimirJavaGenerics imprimirJavaGenerics = new ImprimirJavaGenerics<>(socioEntity);
                imprimirJavaGenerics.imprimir();
                break;
            }
        }
    }


    //Metodo para buscar socio en la lista de socios.
    public static void buscarSocios () throws SQLException, DAOException {

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();

        boolean salirMenu = false;

        do {
            int opcion = MenuSocio.mostrarSocio();

            switch (opcion) {
                case 1 -> printSociosXTipo(hibernateDAOManager, "Estandar");
                case 2 -> printSociosXTipo(hibernateDAOManager, "Federado");
                case 3 -> printSociosXTipo(hibernateDAOManager, "Infantil");
                case 4 -> {
                    printSociosXTipo(hibernateDAOManager, "Estandar");
                    printSociosXTipo(hibernateDAOManager, "Federado");
                    printSociosXTipo(hibernateDAOManager, "Infantil");
                }
                case 5 -> {
                    String nif = UtilidadesVista.preguntaNif();
                    printSocioXNif(hibernateDAOManager, nif);
                }
                case 0 -> salirMenu = true;
                default -> UtilidadesVista.seleccionOpciones();
            }
        } while (!salirMenu);
    }

    //Método para modificar Socios.
    public static void modificarSocio() throws DAOException, SQLException {

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        boolean salirMenu = false;
        List<SeguroEntity> listaSeguros = hibernateDAOManager.getSeguroDAO().obtenerTodos();

        do {
            int opcion = MenuSocio.cambiarSocio();

            switch (opcion) {
                case 1 -> {
                    SeguroEntity cambioSeguro = null;

                    String nif = UtilidadesVista.preguntaNif();
                    String nuevoSeguro = MenuSocio.preguntaSeguro();

                    for (SeguroEntity seguro : listaSeguros) {
                        if (seguro.getNombreSeguro().equals(nuevoSeguro)) {
                            cambioSeguro = seguro;
                        }
                    }

                    if (cambioSeguro == null) {
                        MenuSocio.errorSeguro();
                        return;

                    }
                    boolean existe = false;
                    List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
                        for (SocioEntity socioEntity : listaSocios) {
                            if ((socioEntity.getNif().equals(nif)) && (socioEntity.getTipo().equals("Estandar"))) {

                                EstandarEntity estandarEntity = hibernateDAOManager.getEstandarDAO().obtener(Long.valueOf(socioEntity.getId()));
                                estandarEntity.setIdSeguro(cambioSeguro.getId());
                                hibernateDAOManager.getEstandarDAO().modificar(estandarEntity);
                                UtilidadesVista.informarExito();
                                existe = true;
                            }

                        }
                        if (!existe){
                            MenuSocio.nifNoCorrespondeAClase();
                        }
                }
                case 2 -> {
                    String nif = UtilidadesVista.preguntaNif();
                    String nuevaFederacion = MenuSocio.preguntaNombreFederacion();
                    Long idFederacion = null;
                    FederacionEntity federacion = null;
                    List<FederacionEntity> listaFederacion = hibernateDAOManager.getFederacionDAO().obtenerTodos();
                    for (FederacionEntity federacionEntity : listaFederacion) {
                        if (nuevaFederacion.equals(federacionEntity.getNombreFederacion())) {
                            idFederacion = Long.valueOf(federacionEntity.getId());
                        }
                    }
                    if (idFederacion != null) {
                        federacion = hibernateDAOManager.getFederacionDAO().obtener(idFederacion);
                    }

                    if (federacion == null) {
                        MenuSocio.errorFederacion();
                        return;
                    }
                    SocioEntity socio =null;
                    List<SocioEntity> socios = hibernateDAOManager.getSocioDAO().obtenerTodos();
                    for (SocioEntity socioEntity : socios) {
                        if ((nif.equals(socioEntity.getNif()))&&(socioEntity.getTipo().equals("Federado"))) {
                             socio = socioEntity;
                        }
                    }
                    if (socio != null) {
                        FederadoEntity federado = hibernateDAOManager.getFederadoDAO().obtener(Long.valueOf(socio.getId()));
                        federado.setIdFederacion(federacion.getId());
                        hibernateDAOManager.getFederadoDAO().modificar(federado);
                        UtilidadesVista.informarExito();
                    } else {
                        MenuSocio.nifNoCorrespondeAClase();
                    }
                }
                case 3 -> {
                    String numeroSocio = UtilidadesVista.preguntaNumeroSocioCambio();
                    String nifTutor = UtilidadesVista.preguntaNuevoNifTutor();
                    List<SocioEntity> listaSocios= hibernateDAOManager.getSocioDAO().obtenerTodos();
                    SocioEntity tutor = null;
                    for (SocioEntity socioEntity : listaSocios) {
                        if (socioEntity.getNif().equals(nifTutor)) {
                           tutor = hibernateDAOManager.getSocioDAO().obtener(Long.valueOf(socioEntity.getId()));

                        }
                    }

                    InfantilEntity infantil = null;
                    if (tutor != null) {
                        Long idSocio = Long.parseLong(numeroSocio.substring(numeroSocio.length() - 4));
                        for (SocioEntity socioEntity : listaSocios) {
                            if ((Long.valueOf(socioEntity.getId()).equals(idSocio))&&(socioEntity.getTipo().equals("Infantil"))) {
                                infantil = hibernateDAOManager.getInfantilDAO().obtener(idSocio);
                                infantil.setNifTutor(tutor.getNif());
                                hibernateDAOManager.getInfantilDAO().modificar(infantil);
                                UtilidadesVista.informarExito();
                            }
                        }

                    } else {
                        UtilidadesVista.errorTutor();
                    }
                }
                case 0 -> salirMenu = true;
                default -> UtilidadesVista.seleccionOpciones();
            }
        } while (!salirMenu);
    }

    //Este método no borrara al socio, sino que activara o desactivara su casilla de activo (booleano),
    //para mantener la integridad de la base de datos.
    public static void eliminarSocio() throws DAOException, SQLException {

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocio = hibernateDAOManager.getSocioDAO().obtenerTodos();
        String codigoSocio;
        boolean existe = false;

        codigoSocio = MenuSocio.eliminarSocio();
        String ultimasCuatro = codigoSocio.substring(codigoSocio.length()-4);
        Long idSocio = Long.parseLong(ultimasCuatro);

        for (SocioEntity socioEntity : listaSocio){
            if (idSocio.equals((Long.valueOf(socioEntity.getId())))){
                existe = true;
                hibernateDAOManager.getSocioDAO().eliminar(socioEntity);
                UtilidadesVista.informarExito();
            }
        }

        if (!existe) {
            UtilidadesVista.socioNoEncontrado();
        }
    }

    //Método para mostrar factura socio en el mes corriente.
    public static void mostrarFacturaSocio() throws DAOException, SQLException {

        HibernateDAOManager hibernateDAOManager = new HibernateDAOManager();
        List<SocioEntity> listaSocios = hibernateDAOManager.getSocioDAO().obtenerTodos();
        List<ExcursionEntity> listaExcursiones = hibernateDAOManager.getExcursionDAO().obtenerTodos();
        List<InscripcionEntity> listaInscripciones = hibernateDAOManager.getInscripcionDAO().obtenerTodos();

        String codigoSocio;
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

        codigoSocio = UtilidadesVista.codigoFactura();
        String ultimasCuatro = codigoSocio.substring(codigoSocio.length()-4);
        Long idSocio = Long.parseLong(ultimasCuatro);

        for (SocioEntity socioEntity: listaSocios){
            if (idSocio.equals((Long.valueOf(socioEntity.getId())))) {
                existe = true;
                if (socioEntity.getActivo()==1) {
                    activo = true;
                    for (ExcursionEntity excursionEntity : listaExcursiones) {
                        fechaExcursionImportada = excursionEntity.getFechaInicio();
                        mesExcursion = fechaExcursionImportada.getMonthValue();

                        if (mesActual == mesExcursion) {
                            for (InscripcionEntity inscripcionEntity : listaInscripciones) {
                                if ((idSocio.equals(Long.valueOf(inscripcionEntity.getId())))) {
                                    totalExcursiones += excursionEntity.getPrecioInscripcion();
                                    contadorExcursiones++;
                                }
                            }
                        }
                    }
                    if (socioEntity.getTipo().equals("Estandar")) {
                        EstandarEntity estandarEntity = hibernateDAOManager.getEstandarDAO().obtener(idSocio);
                        SeguroEntity seguroEntity = hibernateDAOManager.getSeguroDAO().obtener(Long.valueOf(estandarEntity.getIdSeguro()));
                        total = cuota + totalExcursiones + (contadorExcursiones * seguroEntity.getPrecio());
                    } else if (socioEntity.getTipo().equals("Federado")) {
                        total = (cuota / (1 + descuentoCuotaFederado)) + (totalExcursiones / (1 + descuentoExcurFederado));
                    } else if (socioEntity.getTipo().equals("Infantil")) {
                        total = (cuota / (1 + descuentoCuotaInfantil)) + totalExcursiones;
                    }
                    UtilidadesVista.mensajeImporte(total);


                }
            }


        }

        if (!activo){

            if (!existe){
                UtilidadesVista.socioNoEncontrado();
            }else {
                UtilidadesVista.socioDesactivado();
            }
        }
    }
}

