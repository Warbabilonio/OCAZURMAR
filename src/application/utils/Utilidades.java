package application.utils;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Utilidades {

	private static Utilidades instancia = null;

	private static final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	private Utilidades() {}

	public static Utilidades getInstance() {
		if (instancia == null)
			instancia = new Utilidades();
		return instancia;
	}

	public static void centrarPantalla(Stage stage, double w, double h) {
		stage.setX((screenBounds.getWidth() - w) / 2);
		stage.setY((screenBounds.getHeight() - h) / 2);
	}

	public static EventHandler<WindowEvent> noCerrarVentana() {
		return new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				event.consume();
			}
		};
	}
}