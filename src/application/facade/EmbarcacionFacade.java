package application.facade;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import application.daos.EmbarcacionDao;

public class EmbarcacionFacade implements BaseFacade {

	private static final Logger log = Logger.getLogger(EmbarcacionFacade.class);

	private final EmbarcacionDao dao = EmbarcacionDao.getInstance();

	private static EmbarcacionFacade instance = null;

	private EmbarcacionFacade() {}

	public static EmbarcacionFacade getInistance() {
		if (instance == null) {
			instance = new EmbarcacionFacade();
		}
		return instance;
	}

	@Override
	public boolean alta(Object ob) throws SQLException {
		return dao.alta(ob);
	}

	@Override
	public boolean baja(Object ob) throws SQLException {
		return dao.baja(ob);
	}

	@Override
	public boolean modificacion(Object ob) throws SQLException {
		return modificacion(ob);
	}

	@Override
	public List<Object> consulta(Object ob) throws SQLException {
		return dao.consulta(ob);
	}

	@Override
	public List<Object> lista() throws SQLException {
		return dao.lista();
	}
}
