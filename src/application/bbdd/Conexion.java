package application.bbdd;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import application.utils.Constantes;
import biz.source_code.miniConnectionPoolManager.MiniConnectionPoolManager;

public class Conexion {

	private static final Logger log = Logger.getLogger(Conexion.class);

	private static Connection conn = null;

	private static SQLiteConnectionPoolDataSource dataSource = null;

	private static MiniConnectionPoolManager poolMgr = null;

	private Conexion() {}

	public static void iniciar() {
		if (null == poolMgr) {
			dataSource = Conexion.getDataSource();
			poolMgr = new MiniConnectionPoolManager(dataSource, Constantes.MAX_CONEXIONES);
			log.info("Pool de conexiones realizada correctamente - " + dataSource.getUrl().toString());
		}
	}

	public static void open() {
		try {
			if (poolMgr != null && conn == null) {
				conn = poolMgr.getConnection();
				log.info("Conexion establecida correctamente - " + conn.toString());
			}
		} catch (SQLException e) {
			log.error("Error al intentar conectar - " + e.getMessage());
		}
	}

	public static void close() {
		try {
			if (poolMgr != null && conn != null) {
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
			if (poolMgr != null) {
				poolMgr.dispose();
				poolMgr = null;
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
