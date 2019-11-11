package application.utils;

import application.daos.impl.EmbarcacionDao;
import application.facades.impl.EmbarcacionFacade;

public class StaticUtils {

	private static StaticUtils instance = null;

	private StaticUtils() {
	}

	public static StaticUtils getInstance() {
		if (instance == null)
			instance = new StaticUtils();
		return instance;
	}

	public static void iniciar() {
	}

	public static EmbarcacionDao getEmbarcacionDao(){
		return EmbarcacionDao.getInstance();
	}

	public static EmbarcacionFacade getEmbarcacionFacade(){
		return EmbarcacionFacade.getInistance();
	}
}
