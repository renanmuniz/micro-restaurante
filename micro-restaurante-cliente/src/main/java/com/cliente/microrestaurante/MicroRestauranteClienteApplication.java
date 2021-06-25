package com.cliente.microrestaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
public class MicroRestauranteClienteApplication {

    public static void main(String[] args) {

        SpringApplication.run(MicroRestauranteClienteApplication.class, args);

        //Numero de cliente perante a operadora de cartao:
        final Long creditCardClientNumber = 1L;
        //TODO Aqui fazer o processo de autenticacao igual no projeto micro-pedido no meu notebook.

    }




}
