package application.errores;

public class ErrorDao extends Exception {

	private static final long serialVersionUID = 4431336526576605877L;

	public ErrorDao() {
		super();
	}

	public ErrorDao(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorDao(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorDao(String message) {
		super(message);
	}

	public ErrorDao(Throwable cause) {
		super(cause);
	}
}
