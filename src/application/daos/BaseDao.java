package application.daos;

import java.util.List;

public interface BaseDao {

	boolean alta(Object ob);

	boolean baja(Object ob);

	boolean modificacion(Object ob);

	List<Object> consulta(Object ob);

	List<Object> lista();
}
