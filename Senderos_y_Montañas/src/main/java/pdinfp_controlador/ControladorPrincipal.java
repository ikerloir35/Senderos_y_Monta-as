//controladorDAO principal que carga datos iniciales y ejecuta los diferentes controladores de cada sección solicitada.

package pdinfp_controlador;


import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_vista.MenuPrincipal;
import pdinfp_vista.UtilidadesVista;

import java.sql.SQLException;


public class ControladorPrincipal {



    public ControladorPrincipal (){

    }

    public void iniciarMenu() throws DAOException, SQLException {
        //Precargamos datos

       /* CargaDatos cargaDatos = new CargaDatos();
        cargaDatos.asignaciones();*/

        //inicializamos variables y objeto scanner

        boolean salirMenu = false;


        //Presentamos menu principal y redireccionamos a cada sección del programa
        do {


                int opcion = MenuPrincipal.menuPrincipal();

                switch (opcion) {
                    case 1 -> {
                        ControladorExcursion.gestorMenuExcursion();
                    }
                    case 2 -> {
                        ControladorSocio.gestorMenuSocio();
                    }
                    case 3 -> {
                       ControladorInscripcion.gestorMenuInscripcion();
                    }
                    case 0 -> {
                        UtilidadesVista.mensajeDespedida();
                        salirMenu = true;
                    }
                    default -> UtilidadesVista.seleccionOpciones();
                }

        } while (!salirMenu);

    }
}
