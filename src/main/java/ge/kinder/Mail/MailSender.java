package ge.kinder.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MailSender {

    private static final String login = "megi.shashikadze.99@gmail.com"; // enter your mail
    private static final String password = "jviwshxcgswtosku"; // enter your password

    public static boolean sendMail(String msg,String subject,String receiver) {
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();


        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });


        session.setDebug(true);

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            return true;

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;

    }

}
