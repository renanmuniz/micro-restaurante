import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jms.*;
import javax.naming.InitialContext;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsumidorJms {
    public static void main(String[] args) throws Exception {

        System.out.println("Iniciando JMS Listener");

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("emailpedido");
        MessageConsumer consumer = session.createConsumer(fila);

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Pressione ENTER caso queira encerrar a execução.");
        consumer.setMessageListener(message -> {
            System.out.println("==================================");
            System.out.println("=> Processando Mensagem - " + "[" + LocalDateTime.now() + "]");
            TextMessage textMessage = (TextMessage) message;
            try {
                String json = textMessage.getText();
                Email email = objectMapper.readValue(json, Email.class);
                System.out.println("endereco: " + email.endereco);
                System.out.println("conteudo: " + email.conteudo);

                EnviaEmail enviaEmail = new EnviaEmail();
                enviaEmail.enviar(email.endereco, email.assunto, email.conteudo);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("==================================");
        });

        new Scanner(System.in).nextLine();
        System.out.println("Finalizando JMS Listener");

        session.close();
        connection.close();
        context.close();

    }
}
