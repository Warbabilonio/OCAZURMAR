package application.main;

import org.apache.log4j.Logger;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		log.info("Iniciando la app...");
		LauncherImpl.launchApplication(Principal.class, Carga.class, args);
	}
}
