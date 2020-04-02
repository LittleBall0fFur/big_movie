module bmdb {
    requires java.sql;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.nhlstenden.bmdb to javafx.fxml;
    exports com.nhlstenden.bmdb;
}