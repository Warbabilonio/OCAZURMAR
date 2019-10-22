package application.utils;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.daos.EmbarcacionDao;

public class StaticUtils {

	private StaticUtils() {
		// TODO Auto-generated constructor stub
	}

	public static void iniciar() {
	}

	public static EmbarcacionDao getEmbarcacionDAO() {
		return EmbarcacionDao.getInstance();
	}
}
