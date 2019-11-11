package application.main;

import org.apache.log4j.Logger;

import application.utils.Utilidades;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Principal extends Application {

	private static final Logger log = Logger.getLogger(Principal.class);

	private static final double WIDTH = 800;

	private static final double HEIGHT = 600;

	private Stage applicationStage;

	public Principal() {
		log.info("Entrando al constructor");
	}

	@Override
	public void init() {
		try {
			Platform.runLater(() -> {
				log.info("init.Platform - Estoy en el hilo: " + Thread.currentThread().toString());
				Utilidades.conexionTest();
			});
			log.info("init - Mandando a dormir");
			Thread.sleep(5000);
			log.info("init - Me he despertado : " + Thread.currentThread().toString());
		} catch (Exception e) {
			log.error("Error en el Init", e);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		crearStage(primaryStage);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

	private void crearStage(Stage primaryStage) {
		this.applicationStage = primaryStage;
		VBox root = new VBox();
		Scene scene = new Scene(root);
		applicationStage.setScene(scene);
		Utilidades.fullScream(primaryStage);
		applicationStage.show();
		log.info("Estoy en el hilo: " + Thread.currentThread().toString());
	}
}
