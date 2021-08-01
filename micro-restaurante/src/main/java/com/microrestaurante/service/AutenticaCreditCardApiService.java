package com.microrestaurante.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AutenticaCreditCardApiService {

    WebClient webClient = WebClient.builder()
            .baseUrl("localhost:8086/microcreditcard")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public RetornoAutenticarTO autenticar(PedidoAutenticarTO pedido) {
        Mono<RetornoAutenticarTO> monoAutenticado = this.webClient
                .method(HttpMethod.POST)
                .uri("http://localhost:8086/microcreditcard/auth")
                .body(Mono.just(pedido), PedidoAutenticarTO.class)
                .retrieve()
                .bodyToMono(RetornoAutenticarTO.class)
                .doOnError(error -> System.out.println("And error has occurred: " + error.getMessage()));

        RetornoAutenticarTO ret = monoAutenticado.block();
        //AutenticaCreditCardAPi.token = ret.getToken();
        return ret;
    }

}
