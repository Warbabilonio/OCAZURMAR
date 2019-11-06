package application.facades;

import java.util.List;

import application.errores.ErrorFacade;

public interface BaseFacade {

	Boolean alta(Object ob) throws ErrorFacade;

	Boolean baja(Object ob) throws ErrorFacade;

	Boolean modificacion(Object ob) throws ErrorFacade;

	List<Object> consulta(Object ob) throws ErrorFacade;

	List<Object> lista() throws ErrorFacade;
}
