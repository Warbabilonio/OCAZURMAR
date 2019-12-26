package application.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.errores.ErrorFacade;
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

	private Utilidades() {
	}

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

	public static void addCss(Scene scene) {
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/application.css").toExternalForm());
	}

	public static void fullScream(Stage stage) {
		stage.setMaximized(Boolean.TRUE);
	}

	public static void conexionTest() {
		try {
			Connection conn = Conexion.getConnection();
			final Embarcacion emb = new Embarcacion(000000, 0.0, 0.0, 0.0, 0.0, 0.0, 9999, "BARCOTEST");
			testAlta(emb);
			testLista();
			testConsulta(emb);
			testBaja(emb);
			testLista();
			conn.close();
		} catch (SQLException | ErrorFacade e) {
			log.error("Error testeando la conexión - " + e.getMessage());
		}
	}

	private static void testAlta(Embarcacion emb) throws ErrorFacade {
		if (StaticUtils.getEmbarcacionFacade().alta(emb))
			log.debug("Test Alta - OK");
		else
			log.debug("Test Alta - FAILURE");
	}

	private static void testBaja(Embarcacion emb) throws ErrorFacade {
		if (StaticUtils.getEmbarcacionFacade().baja(emb))
			log.debug("Test Baja - OK");
		else
			log.debug("Test Baja - FAILURE");
	}

	private static void testConsulta(Embarcacion emb) throws ErrorFacade {
		final List<Object> embs = StaticUtils.getEmbarcacionFacade().consulta(emb);
		if (embs != null)
			log.debug("Test Consulta - OK: ");
		else
			log.debug("Test Consulta - FAILURE");
	}

	private static void testLista() throws ErrorFacade {
		List<Object> embarcaciones = StaticUtils.getEmbarcacionFacade().lista();
		if (embarcaciones != null)
			log.debug("Test Lista - OK ");
		else
			log.debug("Test Lista - FAILURE");
	}
}