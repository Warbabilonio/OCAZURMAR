package application.facades.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import application.daos.impl.EmbarcacionDao;
import application.errores.ErrorDao;
import application.errores.ErrorFacade;
import application.facades.BaseFacade;

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
	public Boolean alta(Object ob) throws ErrorFacade {
		try {
			return dao.alta(ob);
		} catch (Exception e) {
			throw new ErrorFacade(e);
		}
	}

	@Override
	public Boolean baja(Object ob) throws ErrorFacade {
		try {
			return dao.baja(ob);
		} catch (Exception e) {
			throw new ErrorFacade(e);
		}
	}

	@Override
	public Boolean modificacion(Object ob) throws ErrorFacade {
		try {
			return modificacion(ob);
		} catch (Exception e) {
			throw new ErrorFacade(e);
		}
	}

	@Override
	public List<Object> consulta(Object ob) throws ErrorFacade {
		try {
			return dao.consulta(ob);
		} catch (Exception e) {
			throw new ErrorFacade(e);
		}
	}

	@Override
	public List<Object> lista() throws ErrorFacade {
		try {
			return dao.lista();
		} catch (Exception e) {
			throw new ErrorFacade(e);
		}
	}
}
