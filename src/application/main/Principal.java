package application.main;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.sun.javafx.application.LauncherImpl;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.facades.impl.EmbarcacionFacade;
import application.utils.Utilidades;
import javafx.application.Application;
import javafx.application.Platform;
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

	private Stage applicationStage;

	public Principal() {
		log.info("Entrando al constructor");
	}

	@Override
	public void init() {
		try {
			log.info("init - Estoy en el hilo: " + Thread.currentThread().toString());
			Platform.runLater(() -> {
				log.info("init.Platform - Estoy en el hilo: " + Thread.currentThread().toString());
				Utilidades.conexionTest();
			});
			log.info("init - Mandando a dormir");
			Thread.sleep(5000);
			log.info("init - Me he despertado : " + Thread.currentThread().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void start(Stage primaryStage) {
		applicationStage = primaryStage;
		Label title = new Label("This is your application!");
		title.setTextAlignment(TextAlignment.CENTER);
		VBox root = new VBox(title);
		root.setAlignment(Pos.CENTER);
		// Create scene and show application stage.
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		applicationStage.setScene(scene);
		applicationStage.show();
		log.info("Estoy en el hilo: " + Thread.currentThread().toString());
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
