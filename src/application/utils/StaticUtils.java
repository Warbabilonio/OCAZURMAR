package application.utils;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.daos.EmbarcacionDAO;

public class StaticUtils {

	private StaticUtils() {
		// TODO Auto-generated constructor stub
	}

	public static void iniciar() {
		Conexion.iniciar();
	}

	public static EmbarcacionDAO getEmbarcacionDAO() {
		return EmbarcacionDAO.getInstance();
	}
}
