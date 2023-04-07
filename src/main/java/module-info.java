module ru.shihov.forsunki.fors {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.shihov.forsunki.fors to javafx.fxml;
    exports ru.shihov.forsunki.fors;
}