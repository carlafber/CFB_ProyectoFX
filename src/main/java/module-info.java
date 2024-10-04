module com.example.carla_delafuentebernardino_proyectofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.carla_delafuentebernardino_proyectofx to javafx.fxml;
    exports com.example.carla_delafuentebernardino_proyectofx;
}