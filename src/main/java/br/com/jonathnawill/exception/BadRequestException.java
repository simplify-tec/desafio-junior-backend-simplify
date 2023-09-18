package br.com.jonathnawill.exception;

public class BadRequestException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public BadRequestException(String msg){
		super(msg);
	}
}
