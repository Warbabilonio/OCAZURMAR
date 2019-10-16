package application.daos;

import java.util.List;

public interface BaseDao {

	public boolean alta(Object ob);

	public boolean baja(Object ob);

	public boolean modificacion(Object ob);

	public List<Object> consulta(Object ob);

	public List<Object> lista();
}
