package ge.kinder.Mail;

public class RegistrationMail extends Mail{

    private static final String subject = "Tinder";

    private static final String message = "Your Registration Tinder code is: %s Don`t share.";

    public RegistrationMail(String mail, String code) {
        super(message.formatted(code),subject,mail);
    }
}
