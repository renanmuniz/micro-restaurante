import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;
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

//        try {
//            // Mensagem
//            Message message = new MimeMessage(session);
//
//            //Remetente
//            message.setFrom(new InternetAddress("rdevlondrina@gmail.com"));
//
//            //Destinatário(s)
//            Address[] toUser = InternetAddress.parse(endereco);
//
//            message.setRecipients(Message.RecipientType.TO, toUser);
//            message.setSubject(assunto);
//            message.setText(conteudo);
//
//            //anexar
//
//
//            // Enviar
//            Transport.send(message);
//
//            System.out.println("Enviado!");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

        try {
            // cria a mensagem
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("rdevlondrina@gmail.com"));
            InternetAddress[] address = {new InternetAddress(endereco)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(assunto);

            // cria a primeira parte da mensagem
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(conteudo);

            // cria a segunda parte da mensage
            MimeBodyPart mbp2 = new MimeBodyPart();

            // anexa o arquivo na mensagem
            FileDataSource fds = new FileDataSource("/home/renan/Desktop/Ver_esse_video");
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());

            // cria a Multipart
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);

            // adiciona a Multipart na mensagem
            msg.setContent(mp);

            // configura a data: cabecalho
            msg.setSentDate(new Date());

            // envia a mensagem
            Transport.send(msg);

        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
        }
    }
}
