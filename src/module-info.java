module EjerciciosJFX_ActividadQ {
	requires transitive javafx.base;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.web;
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.graphics;
	opens widget to javafx.graphics, javafx.fxml, javafx.base;
	exports widget;
}
