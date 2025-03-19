package pdinfp_util;

//import pdinfp_vista.BBDDVista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorJDBC {


    private static final String url = "jdbc:mysql://localhost:3306/prueba";
    private static final String usuario= "root";
    private static final String password = "prueba";


    public static Connection conexionBD() {
        Connection conexionBD = null;
        try {
            conexionBD = DriverManager.getConnection(url,usuario,password);
            //BBDDVista.conexionSi();
        } catch (SQLException sqle){
            //BBDDVista.conexionNo();
            sqle.printStackTrace();
        }

        return conexionBD;
    }

}
