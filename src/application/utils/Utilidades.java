package application.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.errores.ErrorFacade;
import application.facades.impl.EmbarcacionFacade;
import application.main.Principal;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Utilidades {

	private static final Logger log = Logger.getLogger(Utilidades.class);

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

	public static void conexionTest() {
		try {
			Connection conn = Conexion.getConnection();
			List<Object> embarcaciones = EmbarcacionFacade.getInistance().lista();
			for (Object ob : embarcaciones) {
				final Embarcacion embarcacion = (Embarcacion) ob;
				log.info(embarcacion.toString());
			}
			conn.close();
		} catch (SQLException | ErrorFacade e) {
			log.error("Error testeando la conexión - " + e.getMessage());
		}
	}

	public static void addCss(Scene scene) {
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/application.css").toExternalForm());
	}
}