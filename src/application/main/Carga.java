package application.main;

import org.apache.log4j.Logger;

import application.utils.Constantes;
import application.utils.Utilidades;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Carga extends Preloader implements EventHandler<Event> {

	private static final Logger log = Logger.getLogger(Carga.class);

	private final double WIDTH = 300;

	private final double HEIGHT = 250;

	private double xOffset = 0;

	private double yOffset = 0;

	private Stage preloaderStage;

	private Scene scene;

	private Label titulo;

	public Carga() {}

	@Override
	public void init() throws Exception {
		Platform.runLater(() -> {
			titulo = new Label(Constantes.CARGA_TITULO);
			titulo.setId("progreso-titulo");
			VBox root = new VBox();
			root.getChildren().addAll(titulo);
			root.setId("progreso-panel");
			scene = new Scene(root, WIDTH, HEIGHT);
			scene.setOnMousePressed(this);
			scene.setOnMouseDragged(this);
			scene.setCursor(Cursor.WAIT);
			Utilidades.addCss(scene);
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.preloaderStage = primaryStage;
		preloaderStage.setScene(scene);
		preloaderStage.initStyle(StageStyle.UNDECORATED);
		preloaderStage.setResizable(false);
		preloaderStage.setAlwaysOnTop(true);
		preloaderStage.setOnCloseRequest(Utilidades.noCerrarVentana());
		Utilidades.centrarPantalla(preloaderStage, WIDTH, HEIGHT);
		preloaderStage.show();
	}

	@Override
	public void handleApplicationNotification(PreloaderNotification info) {}

	@Override
	public void handleStateChangeNotification(StateChangeNotification info) {
		// Handle state change notifications.
		StateChangeNotification.Type type = info.getType();
		switch (type) {
			case BEFORE_LOAD:
				// Called after MyPreloader#start is called.
				break;
			case BEFORE_INIT:
				// Called before Principal#init is called.
				break;
			case BEFORE_START:
				// Called after Principal#init and before Principal#start is called.
				preloaderStage.hide();
				break;
		}
	}

	@Override
	public void handleProgressNotification(ProgressNotification info) {
		log.info("Progreso: " + info.getProgress());
	}

	@Override
	public void handle(Event event) {
		if (event instanceof MouseEvent) {
			MouseEvent evento = (MouseEvent) event;;
			switch (event.getEventType().getName()) {
				case "MOUSE_PRESSED":
					xOffset = evento.getSceneX();
					yOffset = evento.getSceneY();
					break;
				case "MOUSE_DRAGGED":
					preloaderStage.setX(evento.getScreenX() - xOffset);
					preloaderStage.setY(evento.getScreenY() - yOffset);
				default:
					break;
			}
		}
	}
}
