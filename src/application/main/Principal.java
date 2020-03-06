package application.main;

import org.apache.log4j.Logger;

import com.sun.javafx.application.LauncherImpl;

import application.utils.Utilidades;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Principal extends Application {

	private static final Logger log = Logger.getLogger(Principal.class);

	private Stage principalStage = null;

	private Scene principalScene = null;

	public Principal() {
		log.info("Entrando al constructor");
	}

	@Override
	public void init() {
		try {
			Platform.runLater(() -> {
				Utilidades.conexionTest();
			});
			Thread.sleep(5000);
		} catch (Exception e) {
			log.error("Error al iniciar la Aplicacion", e);
		}
	}

	@Override
	public void start(Stage stage) {
		this.principalStage = stage;
		crearStage();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

	private void crearStage() {
		crearScene();
		principalStage.setScene(principalScene);
		principalStage.initStyle(StageStyle.UNDECORATED);
		principalStage.show();
	}

	private void crearScene() {
		final VBox root = new VBox();
		MenuItem opcion1 = new MenuItem("Opcion 1");
		Menu menu = new Menu("Menu");
		menu.getItems().add(opcion1);
		MenuBar menuBar = new MenuBar(menu);
		// add scene to stage
		this.principalScene = new Scene(root);
	}
}
