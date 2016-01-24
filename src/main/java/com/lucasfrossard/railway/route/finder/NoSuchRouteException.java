package com.lucasfrossard.railway.route.finder;

/**
 * Exception thrown when a route is not found
 * 
 * @author <a href="mailto:lucasfrossard@gmail.com">Lucas Frossard</d>
 *
 */
public class NoSuchRouteException extends Exception {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -1626146508189787144L;

	public NoSuchRouteException(String string) {
		super(string);
	}

}
