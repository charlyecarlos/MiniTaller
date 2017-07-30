package es.charlye.coches.Exception;

/**
 * 
 * @author charlye
 *
 */
public class DAOException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8796259870172745746L;

	public DAOException(String message){
		super(message);
	}
	
	public DAOException(String message, Throwable cause){
		super(message,cause);
	}
	
	public DAOException(Throwable cause){
		super(cause);
	}
}
