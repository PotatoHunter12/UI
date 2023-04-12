module com.example.naloga2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.scripting;

    opens com.example.naloga2 to javafx.fxml;
    exports com.example.naloga2;
}