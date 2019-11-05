package application.facade;

import java.sql.SQLException;
import java.util.List;

public interface BaseFacade {

	Boolean alta(Object ob) throws SQLException;

	Boolean baja(Object ob) throws SQLException;

	Boolean modificacion(Object ob) throws SQLException;

	List<Object> consulta(Object ob) throws SQLException;

	List<Object> lista() throws SQLException;
}
