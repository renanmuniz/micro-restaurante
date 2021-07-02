package com.cliente.microrestaurante;

import com.cliente.microrestaurante.config.jdbc.TestaConexao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.Timer;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
public class MicroRestauranteClienteApplication {

    public static void main(String[] args) {

        SpringApplication.run(MicroRestauranteClienteApplication.class, args);

        //Testa conexão via JDBC para consultas especificas ao banco de dados:
        TestaConexao.testarConexao();

        //Autentica Api externa de pagamento CreditCardApi:
        Timer timer = new Timer();
        AutenticaCreditCardAPi autentica = new AutenticaCreditCardAPi();
        timer.schedule(autentica, new Date(), 500000); //milliseconds



    }
}
