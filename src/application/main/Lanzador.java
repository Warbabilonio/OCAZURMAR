package application.main;

import org.apache.log4j.Logger;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.stage.Stage;

public class Lanzador {

	private static final Logger log = Logger.getLogger(Lanzador.class);

	public static void main(String[] args) {
		log.info("Iniciando la app...");
		LauncherImpl.launchApplication(Principal.class, Carga.class, args);
		System.out.println("Pruiebita git");
		System.out.println("Pruiebita git2");
	}
}
