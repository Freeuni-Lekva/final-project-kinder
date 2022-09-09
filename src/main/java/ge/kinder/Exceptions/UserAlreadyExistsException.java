package ge.kinder.Exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String mail) {
        super("Mail: %s already exists ".formatted(mail));
    }
}
