package application.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sun.javafx.application.LauncherImpl;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.facade.EmbarcacionFacade;
import application.utils.StaticUtils;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Principal extends Application {

	private static final Logger log = Logger.getLogger(Principal.class);

	private static final double WIDTH = 800;

	private static final double HEIGHT = 600;

	// Just a counter to create some delay while showing preloader.
	private static final int COUNT_LIMIT = 500000;

	private static int stepCount = 1;

	private Stage applicationStage;

	public Principal() {
		log.info("Entrando al constructor");
	}

	@Override
	public void init() throws Exception {
		for (int i = 0; i < COUNT_LIMIT; i++) {
			double progress = (100 * i) / COUNT_LIMIT;
			LauncherImpl.notifyPreloader(this, new Carga.ProgressNotification(progress));
		}
		testConexion();
	}

	private void testConexion() {
		try {
			Connection conn = Conexion.getConnection();
			log.info("Conexion Realizada correctamente - " + conn.toString());
			List<Object> embarcaciones = EmbarcacionFacade.getInistance().lista();
			for (Object ob : embarcaciones) {
				final Embarcacion embarcacion = (Embarcacion) ob;
				log.info(embarcacion.toString());
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationStage = primaryStage;
		Label title = new Label("This is your application!");
		title.setTextAlignment(TextAlignment.CENTER);
		VBox root = new VBox(title);
		root.setAlignment(Pos.CENTER);
		// Create scene and show application stage.
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		applicationStage.setScene(scene);
		applicationStage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
