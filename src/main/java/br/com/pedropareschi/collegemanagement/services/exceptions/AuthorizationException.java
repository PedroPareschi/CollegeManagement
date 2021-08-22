package br.com.pedropareschi.collegemanagement.services.exceptions;

public class AuthorizationException extends RuntimeException{
    private static final long serialVersionUID = 4220274184177836246L;

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
