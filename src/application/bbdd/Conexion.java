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

	private static MiniConnectionPoolManager poolConexiones = null;

	private Conexion() {}

	public static void iniciar() {
		try {
			if (null == poolConexiones) {
				conexiones = new ArrayList<>(Constantes.MAX_CONEXIONES);
				conexionesUsadas = new ArrayList<>();
				dataSource = Conexion.getDataSource();
				poolConexiones = new MiniConnectionPoolManager(dataSource, Constantes.MAX_CONEXIONES);
				for (int i = 0; i < Constantes.MAX_CONEXIONES; i++) {
					conexiones.add(poolConexiones.getConnection());
					log.info("Conexion actual : " +conexiones.get(i));
				}
				log.info("Pool de conexiones realizada correctamente - " + dataSource.getUrl().toString());
			}
		} catch (SQLException e) {
			log.error("Error al intentar crear las conexiones - " + e.getMessage());
		} finally {
			conn = null;
		}
	}

	public static Connection getConnection() throws SQLException {
		if (conexiones.isEmpty()) {
			if (conexionesUsadas.size() < Constantes.MAX_CONEXIONES) {
				conexiones.add(poolConexiones.getConnection());
			} else {
				throw new RuntimeException("Se ha alcanzado el numero maximo de conexiones permitidas");
			}
		}
		final Connection conn = conexiones.remove(conexiones.size() - 1);
		conexionesUsadas.add(conn);
		log.info("Conexion obtenida - " + conn.toString());
		return conn;
	}

	public static boolean dropConnection(Connection conn) {
		if (conexionesUsadas.isEmpty())
			return false;
		conexiones.add(conn);
		return conexionesUsadas.remove(conn);
	}

	public static void shutdown() throws SQLException {
		conexionesUsadas.forEach(Conexion::dropConnection);
		for (Connection c : conexiones) {
			c.close();
		}
		conexiones.clear();
	}

	public static void open() {
		try {
			if (poolConexiones != null && conn == null) {
				conn = poolConexiones.getConnection();
				log.info("Conexion establecida correctamente - " + conn.toString());
			}
		} catch (SQLException e) {
			log.error("Error al intentar conectar - " + e.getMessage());
		}
	}

	public static void close() {
		try {
			if (poolConexiones != null && conn != null) {
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
			if (poolConexiones != null) {
				poolConexiones.dispose();
				poolConexiones = null;
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
