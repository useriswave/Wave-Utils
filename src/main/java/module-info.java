module com.wave.waveutils {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
//    requires com.wave.waveutils;
//    requires com.wave.waveutils.;


    opens com.wave.waveutils to javafx.fxml;
    exports com.wave.waveutils.main;
}