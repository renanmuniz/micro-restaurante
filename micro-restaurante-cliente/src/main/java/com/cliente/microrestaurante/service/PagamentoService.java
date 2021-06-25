package com.cliente.microrestaurante.service;

import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    public boolean pagar(Long idUsuario, String cartao, Double valor) {
        return true;
        //TODO Aqui fazer a chamada via Spring WebClient como na classe ServicoCarroImp do projeto micro-pedido
    }

}
