import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class EnviaEmail {
    public void enviar(String endereco, String assunto, String conteudo) {
        Properties props = new Properties();

        // Parâmetros de conexão com servidor Gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("rdevlondrina@gmail.com",
                                "38853885");
                    }
                });

        // Ativa Debug para sessão
        session.setDebug(false);

        try {
            // Mensagem
            Message message = new MimeMessage(session);

            //Remetente
            message.setFrom(new InternetAddress("rdevlondrina@gmail.com"));

            //Destinatário(s)
            Address[] toUser = InternetAddress.parse(endereco);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
            message.setText(conteudo);

            // Enviar
            Transport.send(message);

            System.out.println("Enviado!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
