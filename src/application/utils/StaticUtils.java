package application.utils;

import application.daos.impl.EmbarcacionDao;

public class StaticUtils {

	private static StaticUtils instance = null;

	private StaticUtils() {}

	public static StaticUtils getInstance() {
		if (instance == null)
			instance = new StaticUtils();
		return instance;
	}

	public static void iniciar() {}

	public static EmbarcacionDao getEmbarcacionDAO() {
		return EmbarcacionDao.getInstance();
	}
}
