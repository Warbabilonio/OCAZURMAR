package application.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.utils.Constantes;

public class EmbarcacionDAO implements BaseDao {

	private static final Logger log = Logger.getLogger(EmbarcacionDAO.class);

	private static EmbarcacionDAO instancia = null;

	private PreparedStatement pst = null;

	private ResultSet rs = null;

	private Connection conn = null;

	private EmbarcacionDAO() {}

	public static EmbarcacionDAO getInstance() {
		if (instancia == null) {
			instancia = new EmbarcacionDAO();
		}
		return instancia;
	}

	@Override
	public boolean alta(Object ob) {
		log.info("Entrando en alta");
		final Embarcacion embarcacion = (Embarcacion) ob;
		try {
			Conexion.open();
			conn = Conexion.getConn();
			final String sql = "INSERT INTO " + Constantes.EMBARCACION_TABLE + "(" + Constantes.EMBARCACION_EMBNUMBER
					+ "," + Constantes.EMBARCACION_EMBLOA + "," + Constantes.EMBARCACION_EMBBREATH + ","
					+ Constantes.EMBARCACION_EMBDEPTH + "," + Constantes.EMBARCACION_EMBSD + ","
					+ Constantes.EMBARCACION_EMBDW + "," + Constantes.EMBARCACION_EMBBY + ")"
					+ "VALUES(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, embarcacion.getImoNumber());
			pst.setDouble(2, embarcacion.getLoa());
			pst.setDouble(3, embarcacion.getBreath());
			pst.setDouble(4, embarcacion.getDepth());
			pst.setDouble(5, embarcacion.getSummerDraft());
			pst.setDouble(6, embarcacion.getDw());
			pst.setInt(7, embarcacion.getBuildYear());
			pst.setString(8, embarcacion.getPreviousName());
			if (pst.executeUpdate() != 0) {
				log.info("La insersion se ha realizado satisfactoriamente");
				return true;
			} else {
				log.info("No se ha podido Realizar la insercion");
				return false;
			}
		} catch (SQLException e) {
			log.error("Error al insertar la nueva embarcacion - " + e.getMessage());
			return false;
		} finally {
			pst = null;
			Conexion.close();
		}
	}

	@Override
	public boolean baja(Object ob) {
		log.info("Entrando en baja");
		final Embarcacion embarcacion = (Embarcacion) ob;
		try {
			Conexion.open();
			conn = Conexion.getConn();
			final String sql = "DELETE FROM " + Constantes.EMBARCACION_TABLE + " WHERE "
					+ Constantes.EMBARCACION_EMBNUMBER + " = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, embarcacion.getImoNumber());
			if (pst.executeUpdate() != 0) {
				log.info("La baja se ha realizado satisfactoriamente");
				return true;
			} else {
				log.info("No se ha podido Realizar la baja");
				return false;
			}
		} catch (SQLException e) {
			log.error("Error al dar de baja la embarcacion - " + e.getMessage());
			return false;
		} finally {
			pst = null;
			Conexion.close();
		}
	}

	@Override
	public boolean modificacion(Object ob) {
		log.info("Entrando en modificacion");
		final Embarcacion embarcacion = (Embarcacion) ob;
		try {
			Conexion.open();
			conn = Conexion.getConn();
			final String sql = "UPDATE " + Constantes.EMBARCACION_TABLE + " SET " + Constantes.EMBARCACION_EMBLOA
					+ "=?," + Constantes.EMBARCACION_EMBBREATH + "=?," + Constantes.EMBARCACION_EMBDEPTH + "=?,"
					+ Constantes.EMBARCACION_EMBSD + "=?," + Constantes.EMBARCACION_EMBDW + "=?,"
					+ Constantes.EMBARCACION_EMBBY + "=?," + Constantes.EMBARCACION_EMBNAME + "=? WHERE "
					+ Constantes.EMBARCACION_EMBNUMBER + "=?";
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, embarcacion.getLoa());
			pst.setDouble(2, embarcacion.getBreath());
			pst.setDouble(3, embarcacion.getDepth());
			pst.setDouble(4, embarcacion.getSummerDraft());
			pst.setDouble(5, embarcacion.getDw());
			pst.setInt(6, embarcacion.getBuildYear());
			pst.setString(7, embarcacion.getPreviousName());
			pst.setInt(8, embarcacion.getImoNumber());
			if (pst.executeUpdate() != 0) {
				log.info("La baja se ha realizado satisfactoriamente");
				return true;
			} else {
				log.info("No se ha podido Realizar la baja");
				return false;
			}
		} catch (SQLException e) {
			log.error("Error al dar de baja la embarcacion - " + e.getMessage());
			return false;
		} finally {
			pst = null;
			Conexion.close();
		}
	}

	@Override
	public List<Object> consulta(Object ob) {
		log.info("Entrando en consulta");
		final Embarcacion emb = (Embarcacion) ob;
		final List<Object> embarcaciones = new ArrayList<Object>();
		try {
			final String sql = "SELECT * FROM " + Constantes.EMBARCACION_TABLE + " WHERE "
					+ Constantes.EMBARCACION_EMBNUMBER + "=?";
			Conexion.open();
			conn = Conexion.getConn();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, emb.getImoNumber());
			rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					final Embarcacion embarcacion = new Embarcacion();
					embarcacion.setBreath(rs.getDouble(Constantes.EMBARCACION_EMBBREATH));
					embarcacion.setBuildYear(rs.getInt(Constantes.EMBARCACION_EMBBY));
					embarcacion.setDepth(rs.getDouble(Constantes.EMBARCACION_EMBDEPTH));
					embarcacion.setDw(rs.getDouble(Constantes.EMBARCACION_EMBDW));
					embarcacion.setImoNumber(rs.getInt(Constantes.EMBARCACION_EMBNUMBER));
					embarcacion.setLoa(rs.getDouble(Constantes.EMBARCACION_EMBLOA));
					embarcacion.setPreviousName(rs.getString(Constantes.EMBARCACION_EMBNAME));
					embarcacion.setSummerDraft(rs.getDouble(Constantes.EMBARCACION_EMBSD));
					embarcaciones.add(embarcacion);
				}
			} else {
				log.info("No se ha podido conseguir la embarcacion");
			}
			return embarcaciones;
		} catch (SQLException e) {
			log.error("Error al consultar la embarcacion - " + e.getMessage());
			return null;
		} finally {
			pst = null;
			rs = null;
			Conexion.close();
		}
	}

	@Override
	public List<Object> lista() {
		log.info("Entrando en consulta");
		final List<Object> embarcaciones = new ArrayList<Object>();
		try {
			final String sql = "SELECT * FROM " + Constantes.EMBARCACION_TABLE;
			Conexion.open();
			conn = Conexion.getConn();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					final Embarcacion embarcacion = new Embarcacion();
					embarcacion.setBreath(rs.getDouble(Constantes.EMBARCACION_EMBBREATH));
					embarcacion.setBuildYear(rs.getInt(Constantes.EMBARCACION_EMBBY));
					embarcacion.setDepth(rs.getDouble(Constantes.EMBARCACION_EMBDEPTH));
					embarcacion.setDw(rs.getDouble(Constantes.EMBARCACION_EMBDW));
					embarcacion.setImoNumber(rs.getInt(Constantes.EMBARCACION_EMBNUMBER));
					embarcacion.setLoa(rs.getDouble(Constantes.EMBARCACION_EMBLOA));
					embarcacion.setPreviousName(rs.getString(Constantes.EMBARCACION_EMBNAME));
					embarcacion.setSummerDraft(rs.getDouble(Constantes.EMBARCACION_EMBSD));
					embarcaciones.add(embarcacion);
				}
			} else {
				log.info("No se ha podido conseguir las embarcaciones");
			}
			return embarcaciones;
		} catch (SQLException e) {
			log.error("Error al listar las embarcaciones - " + e.getMessage());
			return null;
		} finally {
			pst = null;
			rs = null;
			Conexion.close();
		}
	}
}
