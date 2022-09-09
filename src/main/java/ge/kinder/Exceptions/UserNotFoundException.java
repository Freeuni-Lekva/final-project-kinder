package ge.kinder.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String mail) {
        super("User with %s mail not found".formatted(mail));
    }
}
