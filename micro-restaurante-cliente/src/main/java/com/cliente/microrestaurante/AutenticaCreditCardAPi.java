package com.cliente.microrestaurante;

import com.cliente.microrestaurante.service.AutenticaCreditCardApiService;
import com.cliente.microrestaurante.service.PedidoAutenticarTO;
import com.cliente.microrestaurante.service.RetornoAutenticarTO;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.Date;
import java.util.TimerTask;

public class AutenticaCreditCardAPi extends TimerTask {

    public static String token = "Bearer ";
    private final String usuario = "admin";
    private final String senha = "admin";

    private String tokenResponse;

    @Override
    public void run() {
        AutenticaCreditCardApiService serv = new AutenticaCreditCardApiService();
        while (tokenResponse == null) {
            try {
                System.out.println("Autenticando com API de pagamento - " + new Date());
                RetornoAutenticarTO ret = serv.autenticar(new PedidoAutenticarTO(usuario, senha));
                tokenResponse = ret.getToken();
            } catch (WebClientRequestException e) {
                System.out.println("[ERRO] - Falha ao solicitar token do serviço de pagamento!");
                System.out.println("Tentando novamente...\n");
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        token = token + tokenResponse;
        System.out.println("AUTENTICADO COM O SERVICO DE PAGAMENTO - " + new Date());
    }
}
