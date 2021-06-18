package com.microcreditcard.service;

import com.microcreditcard.controller.dto.CartaoDto;
import com.microcreditcard.controller.dto.UsuarioDto;
import com.microcreditcard.controller.form.CartaoForm;
import com.microcreditcard.controller.form.UsuarioForm;
import com.microcreditcard.modelo.Cartao;
import com.microcreditcard.modelo.Usuario;
import com.microcreditcard.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Page<CartaoDto> listar(String nome, Pageable paginacao) {
        Page<Cartao> cartoes;
        if (nome == null) {
            cartoes = cartaoRepository.findAll(paginacao);
        } else {
            cartoes = cartaoRepository.findByNome(nome, paginacao);
        }
        return CartaoDto.converter(cartoes);
    }

    public CartaoDto cadastrar(CartaoForm form) {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("0000");
        //df.format((random.nextInt(1000)));
        Cartao cartao = form.converter();
        String numeroCartao = df.format((random.nextInt(1000))) +
                '-' + df.format((random.nextInt(1000))) +
                '-' + df.format((random.nextInt(1000))) +
                '-' + df.format((random.nextInt(1000)));
        cartao.setNumeroCartao(numeroCartao);
        cartao.setCartaoBloqueado(true);
        cartaoRepository.save(cartao);
        return new CartaoDto(cartao);
    }

    public CartaoDto detalhar(Long id) {
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if (cartaoOptional.isPresent()) {
            return new CartaoDto(cartaoOptional.get());
        }
        return null;
    }

    public CartaoDto atualizarLimite(Long id, Double valor) {
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if (cartaoOptional.isPresent()) {
            Cartao cartao = cartaoOptional.get();
            cartao.setValorCreditoDisponivel(valor);
            cartaoRepository.save(cartao);
            return new CartaoDto(cartao);
        }
        return null;
    }

    public Boolean remover(Long id) {
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if (cartaoOptional.isPresent()) {
            cartaoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CartaoDto bloquear(Long id) {
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if (cartaoOptional.isPresent()) {
            cartaoOptional.get().setCartaoBloqueado(true);
            cartaoRepository.save(cartaoOptional.get());
            return new CartaoDto(cartaoOptional.get());
        }
        return null;
    }

    public CartaoDto desbloquear(Long id) {
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if (cartaoOptional.isPresent()) {
            cartaoOptional.get().setCartaoBloqueado(false);
            cartaoRepository.save(cartaoOptional.get());
            return new CartaoDto(cartaoOptional.get());
        }
        return null;
    }

}
