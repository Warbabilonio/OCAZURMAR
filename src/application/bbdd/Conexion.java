package application.bbdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import application.utils.Constantes;
import biz.source_code.miniConnectionPoolManager.MiniConnectionPoolManager;

public class Conexion {

	private static final Logger log = Logger.getLogger(Conexion.class);

	private static Connection conn = null;

	private static List<Connection> conexiones = null;

	private static List<Connection> conexionesUsadas = null;

	private static SQLiteConnectionPoolDataSource dataSource = null;

	private static MiniConnectionPoolManager pollConexiones = null;

	private Conexion() {}

	public static void iniciar() {
		try {
			if (null == pollConexiones) {
				conexiones = new ArrayList<>(Constantes.MAX_CONEXIONES);
				conexionesUsadas = new ArrayList<>();
				dataSource = Conexion.getDataSource();
				pollConexiones = new MiniConnectionPoolManager(dataSource, Constantes.MAX_CONEXIONES);
				for (int i = 0; i < Constantes.MAX_CONEXIONES; i++) {
					conn = pollConexiones.getConnection();
					conexiones.add(conn);
				}
				log.info("Pool de conexiones realizada correctamente - " + dataSource.getUrl().toString());
			}
		} catch (SQLException e) {
			log.error("Error al intentar crear las conexiones - " + e.getMessage());
		} finally {
			conn = null;
		}
	}

	public static Connection getConnection() {
		if (!conexiones.isEmpty() && !conexiones.contains(conn))
			conn = conexiones.remove(conexiones.size() - 1);
		conexionesUsadas.add(conn);
		log.info("Conexion obtenida - " + conn.toString());
		return conn;
	}

	public static void open() {
		try {
			if (pollConexiones != null && conn == null) {
				conn = pollConexiones.getConnection();
				log.info("Conexion establecida correctamente - " + conn.toString());
			}
		} catch (SQLException e) {
			log.error("Error al intentar conectar - " + e.getMessage());
		}
	}

	public static void close() {
		try {
			if (pollConexiones != null && conn != null) {
				conn.close();
				conn = null;
				log.info("Conexion cerrada correctamente");
			} else {
				log.info("No se puede cerrar una conexion aun no establecida");
			}
		} catch (SQLException e) {
			log.error("Error al intentar cerrar la conexion - " + e.getMessage());
		}
	}

	public static void cerrar() {
		try {
			if (pollConexiones != null) {
				pollConexiones.dispose();
				pollConexiones = null;
				log.info("Se han cerrado todas las conexiones");
			}
		} catch (SQLException e) {
			log.error("Error al intentar cerrar el pool de conexiones");
		}
	}

	private static SQLiteConnectionPoolDataSource getDataSource() {
		dataSource = new SQLiteConnectionPoolDataSource();
		dataSource.setUrl(Constantes.JDBC + Constantes.BBDD);
		dataSource.setJournalMode("WAL");
		dataSource.getConfig().setBusyTimeout("10000");
		log.info("DataSource creado correctamente - " + dataSource.getUrl().toString());
		return dataSource;
	}

	public static Connection getConn() {
		return conn;
	}
}
