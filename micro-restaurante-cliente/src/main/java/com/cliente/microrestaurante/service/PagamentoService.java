package com.cliente.microrestaurante.service;

import com.cliente.microrestaurante.AutenticaCreditCardAPi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PagamentoService {

    WebClient webClient = WebClient.builder()
            .baseUrl("localhost:8086/microcreditcard")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public CompraDto pagar(Long idUsuario, String cartao, Double valor) {
        CompraForm compra = new CompraForm(idUsuario,cartao,valor);
        Mono<CompraDto> mono = this.webClient
                .method(HttpMethod.POST)
                .uri("localhost:8086/microcreditcard/compra")
                .body(Mono.just(compra), PedidoAutenticarTO.class)
                .header(HttpHeaders.AUTHORIZATION, AutenticaCreditCardAPi.token)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(Exception::new)
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(Exception::new)
                )
                .bodyToMono(CompraDto.class);
        CompraDto ret = mono.block();
        return ret;

    }

}
