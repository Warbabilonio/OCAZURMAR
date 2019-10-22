package application.facade;

import java.sql.SQLException;
import java.util.List;

public interface BaseFacade {

	boolean alta(Object ob) throws SQLException;

	boolean baja(Object ob) throws SQLException;

	boolean modificacion(Object ob) throws SQLException;

	List<Object> consulta(Object ob) throws SQLException;

	List<Object> lista() throws SQLException;
}
