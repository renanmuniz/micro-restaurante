package com.microrestaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Timer;

@SpringBootApplication
public class MicroRestauranteApplication {

    public static void main(String[] args) {

        SpringApplication.run(MicroRestauranteApplication.class, args);

        //Autentica Api externa de pagamento CreditCardApi:
        Timer timer = new Timer();
        AutenticaCreditCardAPi autentica = new AutenticaCreditCardAPi();
        timer.schedule(autentica, new Date(), 500000); //milliseconds
    }

}
