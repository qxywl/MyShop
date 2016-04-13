package com.zhaidou.common.exception;

/**
*<p>Title: DaoException.java </p>
*@Description:
*@Author:JERRY
*@version:1.0
*@DATE:2013-8-21上午11:25:15
*@see
*/
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = -1261538058740986796L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
