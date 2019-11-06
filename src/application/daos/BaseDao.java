package application.daos;

import java.util.List;

import application.errores.ErrorDao;

public interface BaseDao {

	Boolean alta(Object ob) throws ErrorDao;

	Boolean baja(Object ob) throws ErrorDao;

	Boolean modificacion(Object ob) throws ErrorDao;

	List<Object> consulta(Object ob) throws ErrorDao;

	List<Object> lista() throws ErrorDao;
}
