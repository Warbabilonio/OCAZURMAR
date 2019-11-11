package application.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import application.bbdd.Conexion;
import application.beans.Embarcacion;
import application.daos.BaseDao;
import application.errores.ErrorDao;
import application.utils.Constantes;

public class EmbarcacionDao implements BaseDao {

	private static final Logger log = Logger.getLogger(EmbarcacionDao.class);

	private static EmbarcacionDao instancia = null;

	private PreparedStatement pst = null;

	private ResultSet rs = null;

	private Connection conn = null;

	private EmbarcacionDao() {
	}

	public static EmbarcacionDao getInstance() {
		if (instancia == null) {
			instancia = new EmbarcacionDao();
		}
		return instancia;
	}

	@Override
	public Boolean alta(Object ob) throws ErrorDao {
		try {
			final Embarcacion embarcacion = (Embarcacion) ob;
			log.info("Entrando en alta - Objeto: " + embarcacion.toString());
			conn = Conexion.getConnection();
			final String sql = "INSERT INTO "
								+ Constantes.EMBARCACION_TABLE
								+ "("
								+ Constantes.EMBARCACION_EMBNUMBER
								+ ","
								+ Constantes.EMBARCACION_EMBLOA
								+ ","
								+ Constantes.EMBARCACION_EMBBREATH
								+ ","
								+ Constantes.EMBARCACION_EMBDEPTH
								+ ","
								+ Constantes.EMBARCACION_EMBSD
								+ ","
								+ Constantes.EMBARCACION_EMBDW
								+ ","
								+ Constantes.EMBARCACION_EMBBY
								+ ","
								+ Constantes.EMBARCACION_EMBNAME
								+ ")"
								+ " VALUES(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, embarcacion.getImoNumber());
			pst.setDouble(2, embarcacion.getLoa());
			pst.setDouble(3, embarcacion.getBreath());
			pst.setDouble(4, embarcacion.getDepth());
			pst.setDouble(5, embarcacion.getSummerDraft());
			pst.setDouble(6, embarcacion.getDw());
			pst.setInt(7, embarcacion.getBuildYear());
			pst.setString(8, embarcacion.getPreviousName());
			final int status = pst.executeUpdate();
			conn.close();
			return status != 0;
		} catch (Exception e) {
			log.error("Error al insertar la nueva embarcacion - " + e.getMessage());
			throw new ErrorDao(e);
		} finally {
			pst = null;
		}
	}

	@Override
	public Boolean baja(Object ob) throws ErrorDao {
		try {
			final Embarcacion embarcacion = (Embarcacion) ob;
			log.info("Entrando en baja - Objecto: " + embarcacion.toString());
			conn = Conexion.getConnection();
			final String sql = "DELETE FROM "
								+ Constantes.EMBARCACION_TABLE
								+ " WHERE "
								+ Constantes.EMBARCACION_EMBNUMBER
								+ " = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, embarcacion.getImoNumber());
			final int status = pst.executeUpdate();
			conn.close();
			return status != 0;
		} catch (Exception e) {
			log.error("Error al dar de baja la embarcacion - " + e.getMessage());
			throw new ErrorDao(e);
		} finally {
			pst = null;
		}
	}

	@Override
	public Boolean modificacion(Object ob) throws ErrorDao {
		try {
			final Embarcacion embarcacion = (Embarcacion) ob;
			log.info("Entrando en modificacion - Objeto: " + embarcacion.toString());
			conn = Conexion.getConnection();
			final String sql = "UPDATE "
								+ Constantes.EMBARCACION_TABLE
								+ " SET "
								+ Constantes.EMBARCACION_EMBLOA
								+ "=?, "
								+ Constantes.EMBARCACION_EMBBREATH
								+ "=?, "
								+ Constantes.EMBARCACION_EMBDEPTH
								+ "=?, "
								+ Constantes.EMBARCACION_EMBSD
								+ "=?, "
								+ Constantes.EMBARCACION_EMBDW
								+ "=?, "
								+ Constantes.EMBARCACION_EMBBY
								+ "=?, "
								+ Constantes.EMBARCACION_EMBNAME
								+ "=? WHERE "
								+ Constantes.EMBARCACION_EMBNUMBER
								+ "=?";
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, embarcacion.getLoa());
			pst.setDouble(2, embarcacion.getBreath());
			pst.setDouble(3, embarcacion.getDepth());
			pst.setDouble(4, embarcacion.getSummerDraft());
			pst.setDouble(5, embarcacion.getDw());
			pst.setInt(6, embarcacion.getBuildYear());
			pst.setString(7, embarcacion.getPreviousName());
			pst.setInt(8, embarcacion.getImoNumber());
			final int status = pst.executeUpdate();
			conn.close();
			return status != 0;
		} catch (Exception e) {
			log.error("Error al dar de baja la embarcacion - " + e.getMessage());
			throw new ErrorDao(e);
		} finally {
			pst = null;
		}
	}

	@Override
	public List<Object> consulta(Object ob) throws ErrorDao {
		try {
			final Embarcacion emb = (Embarcacion) ob;
			log.info("Entrando en consulta - Objeto: " + emb.toString());
			List<Object> embarcaciones = null;
			final String sql = "SELECT * FROM "
								+ Constantes.EMBARCACION_TABLE
								+ " WHERE "
								+ Constantes.EMBARCACION_EMBNUMBER
								+ "=?";
			conn = Conexion.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, emb.getImoNumber());
			rs = pst.executeQuery();
			if (rs != null) {
				embarcaciones = new ArrayList<>();
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
			}
			conn.close();
			return embarcaciones;
		} catch (Exception e) {
			log.error("Error al consultar la embarcacion - " + e.getMessage());
			throw new ErrorDao(e);
		} finally {
			pst = null;
			rs = null;
		}
	}

	@Override
	public List<Object> lista() throws ErrorDao {
		try {
			log.info("Entrando en lista");
			List<Object> embarcaciones = null;
			final String sql = "SELECT * FROM " + Constantes.EMBARCACION_TABLE;
			conn = Conexion.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs != null) {
				embarcaciones = new ArrayList<Object>();
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
			}
			conn.close();
			return embarcaciones;
		} catch (Exception e) {
			log.error("Error al listar las embarcaciones - " + e.getMessage());
			throw new ErrorDao(e);
		} finally {
			pst = null;
			rs = null;
		}
	}
}
