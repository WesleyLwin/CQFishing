module fishing.cqfishing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens fishing.cqfishing to javafx.fxml;
    exports fishing.cqfishing;
}
