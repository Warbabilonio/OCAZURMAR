package application.errores;

public class ErrorFacade extends Exception {

	private static final long serialVersionUID = 5359489636171652195L;

	public ErrorFacade() {}

	public ErrorFacade(String message) {
		super(message);
	}

	public ErrorFacade(Throwable cause) {
		super(cause);
	}

	public ErrorFacade(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorFacade(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
