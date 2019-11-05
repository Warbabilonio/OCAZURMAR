package application.utils;

public class Constantes {

	private Constantes() {}

	public static String JDBC = "jdbc:sqlite:";

	public static int MAX_CONEXIONES = 20;

	public static String BBDD_NOMBRE = "ZURMAR.db";

	public static String BBDD_RUTA = "C:\\BaseDatos\\";

	public static String BBDD = BBDD_RUTA + BBDD_NOMBRE;

	public static String CARGA_TITULO = "Iniciando";

	// TABLAS BASE DATOS
	/** EMBARCACION */
	public static String EMBARCACION_TABLE = "EMBARCACION";

	/** EMBARCACION.EMBNUMBER */
	public static String EMBARCACION_EMBNUMBER = "EMBNUMBER";

	/** EMBARCACION.EMBLOA */
	public static String EMBARCACION_EMBLOA = "EMBLOA";

	/** EMBARCACION.EMBBREATH */
	public static String EMBARCACION_EMBBREATH = "EMBBREATH";

	/** EMBARCACION.EMBDEPTH */
	public static String EMBARCACION_EMBDEPTH = "EMBDEPTH";

	/** EMBARCACION.EMBSD */
	public static String EMBARCACION_EMBSD = "EMBSD";

	/** EMBARCACION.EMBDW */
	public static String EMBARCACION_EMBDW = "EMBDW";

	/** EMBARCACION.EMBBY */
	public static String EMBARCACION_EMBBY = "EMBBY";

	/** EMBARCACION.EMBNAME */
	public static String EMBARCACION_EMBNAME = "EMBNAME";

	/** TANQUE */
	public static String TANQUE_TABLE = "TANQUE";

	/** TANQUE.TANCODE */
	public static String TANQUE_TANCODE = "TANCODE";

	/** TANQUE.TANNAME */
	public static String TANQUE_TANNAME = "TANNAME";
}
