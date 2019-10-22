package application.bbdd;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import application.utils.Constantes;
import biz.source_code.miniConnectionPoolManager.MiniConnectionPoolManager;

public class Conexion {

	private static final Logger log = Logger.getLogger(Conexion.class);

	private static SQLiteConnectionPoolDataSource dataSource = null;

	private static MiniConnectionPoolManager poolConexiones = null;
	static {
		if (poolConexiones == null) {
			dataSource = getDataSource();
			poolConexiones = new MiniConnectionPoolManager(dataSource, Constantes.MAX_CONEXIONES);
			log.info("Pool de conexiones realizada correctamente - Conexiones activas: "
					+ poolConexiones.getActiveConnections());
		}
	}

	private Conexion() {}

	public static Connection getConnection() throws SQLException {
		if (poolConexiones.getActiveConnections() != Constantes.MAX_CONEXIONES)
			return poolConexiones.getConnection();
		else
			throw new SQLException("Maximo conexiones alcanzadas");
	}

	public static void clearPool() throws SQLException {
		poolConexiones.dispose();
	}

	private static SQLiteConnectionPoolDataSource getDataSource() {
		dataSource = new SQLiteConnectionPoolDataSource();
		dataSource.setUrl(Constantes.JDBC + Constantes.BBDD);
		dataSource.setJournalMode("WAL");
		dataSource.getConfig().setBusyTimeout("10000");
		log.info("DataSource creado correctamente - " + dataSource.getUrl().toString());
		return dataSource;
	}
}
