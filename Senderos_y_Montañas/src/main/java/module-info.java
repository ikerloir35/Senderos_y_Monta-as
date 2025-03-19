module com.example.pdin_JavaFX {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.naming;
    requires jakarta.persistence;
    //requires hibernate.core;
    requires org.hibernate.orm.core;
    requires java.datatransfer;
    requires java.desktop;

    opens pdinfp_controlador_javafx to javafx.fxml;
    opens pdinfp_Entitys to org.hibernate.orm.core, javafx.base;
    exports pdinfp_controlador_javafx;

    exports pdinfp_controlador_javafx.ControladoresExcursiones;
    opens pdinfp_controlador_javafx.ControladoresExcursiones to javafx.fxml;

    exports pdinfp_controlador_javafx.ControladoresSocios;
    opens pdinfp_controlador_javafx.ControladoresSocios to javafx.fxml;


    exports pdinfp_controlador_javafx.ControladoresInscripciones;
    opens pdinfp_controlador_javafx.ControladoresInscripciones to javafx.fxml;

}