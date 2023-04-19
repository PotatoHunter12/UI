module com.example.naloga4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.naloga4 to javafx.fxml;
    exports com.example.naloga4;
}