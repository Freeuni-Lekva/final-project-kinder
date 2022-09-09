package ge.kinder.Exceptions;

public class InvalidMailException extends RuntimeException {
    public InvalidMailException(String mail) {

        super(mail + " is invalid");
    }
}