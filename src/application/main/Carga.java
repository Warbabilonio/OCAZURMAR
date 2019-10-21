package application.main;

import org.apache.log4j.Logger;

import application.utils.Utilidades;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Carga extends Preloader implements EventHandler<Event> {

	private static final Logger log = Logger.getLogger(Carga.class);

	private static final double WIDTH = 300;

	private static final double HEIGHT = 250;

	private static final String tituloString = "Iniciando la aplicacion";

	private static int max = tituloString.length() + 3;

	private double xOffset = 0;

	private double yOffset = 0;

	private boolean controlProgreso = false;

	private boolean controlPorcentaje = false;

	private Stage preloaderStage;

	private Scene scene;

	private Label progress;

	private ProgressBar progressBar;

	private Label title;

	public Carga() {}

	@Override
	public void init() throws Exception {
		// If preloader has complex UI it's initialization can be done in MyPreloader#init
		Platform.runLater(() -> {
			title = new Label(tituloString);
			title.setId("progreso-titulo");
			progress = new Label("0");
			progress.setId("progreso-texto");
			progressBar = new ProgressBar(0);
			progressBar.setId("progreso-barra");
			final VBox root = new VBox(title, progressBar, progress);
			root.setAlignment(Pos.CENTER);
			root.setId("progreso-panel");
			scene = new Scene(root, WIDTH, HEIGHT);
			scene.setOnMousePressed(this);
			scene.setOnMouseDragged(this);
			scene.setCursor(Cursor.WAIT);
			scene.getStylesheets().add(this.getClass().getResource("../estatico/application.css").toExternalForm());
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
	public void handleApplicationNotification(PreloaderNotification info) {
		// Handle application notification in this point (see Principal#init).
		if (info instanceof ProgressNotification) {
			final ProgressNotification notificacion = (ProgressNotification) info;
			Double progreso = notificacion.getProgress() != 0.00 ? notificacion.getProgress() / 100 : 0.00;
			progressBar.setProgress(progreso);
			progress.setText((int) Math.round(notificacion.getProgress()) + " %");
			if ((!controlPorcentaje && notificacion.getProgress() % 5 != 0) && controlProgreso) {
				controlPorcentaje = false;
				controlProgreso = false;
			}
			if ((!controlPorcentaje && notificacion.getProgress() % 5 == 0) && !controlProgreso) {
				controlPorcentaje = true;
				if (controlPorcentaje && !controlProgreso)
					animarTexto(title);
			}
		}
	}

	private void animarTexto(Label title) {
		if (controlPorcentaje && !controlProgreso) {
			controlProgreso = true;
			controlPorcentaje = false;
			switch (max - title.getText().length()) {
				case 3:
					title.setText(tituloString + ".");
					break;
				case 2:
					title.setText(tituloString + "..");
					break;
				case 1:
					title.setText(tituloString + "...");
					break;
				case 0:
					title.setText(tituloString);
					break;
				default:
					break;
			}
		}
	}

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
	public void handle(Event event) {
		if (event instanceof MouseEvent) {
			MouseEvent evento = (MouseEvent) event;
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
