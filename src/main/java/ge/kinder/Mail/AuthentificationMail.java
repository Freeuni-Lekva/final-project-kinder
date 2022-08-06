package ge.kinder.Mail;

public class AuthentificationMail extends Mail{

    private static final String subject = "Tinder";

    private static final String message = "Your Authentification Tinder code is: %s Don`t share.";


    public AuthentificationMail(String mail,String code) {
        super(message.formatted(code),subject,mail);
    }
}
