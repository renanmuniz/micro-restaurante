package com.microcreditcard.controller;

import com.microcreditcard.controller.dto.CompraDto;
import com.microcreditcard.controller.dto.UsuarioDto;
import com.microcreditcard.controller.form.CompraForm;
import com.microcreditcard.controller.form.UsuarioForm;
import com.microcreditcard.modelo.Compra;
import com.microcreditcard.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/listar/todas/poridcartao")
    public Page<CompraDto> listarTodasPorIdCartao(@RequestParam(required = true) Long id,
                                                  @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                          Pageable paginacao) {
        return compraService.listarTodasPorIdCartao(id, paginacao);
    }

    @GetMapping("/listar/todas/poridusuario")
    public Page<CompraDto> listarTodasPorIdUsuario(@RequestParam(required = true) Long id,
                                                  @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                          Pageable paginacao) {
        return compraService.listarTodasPorIdUsuario(id, paginacao);
    }

    @GetMapping("/listar/aprovadas/poridcartao")
    public Page<CompraDto> listarAprovadasPorIdCartao(@RequestParam(required = true) Long id, String dtInicio, String dtFim,
                                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                           Pageable paginacao) {
        return compraService.listarComprasAprovadasPorIdCartao(id, dtInicio, dtFim, paginacao);
    }

    @GetMapping("/listar/recusadas/poridcartao")
    public Page<CompraDto> listarRecusadasPorIdCartao(@RequestParam(required = true) Long id, String dtInicio, String dtFim,
                                                      @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                              Pageable paginacao) {
        return compraService.listarComprasRecusadasPorIdCartao(id, dtInicio, dtFim, paginacao);
    }

    @GetMapping("/listar/estornadas/poridcartao")
    public Page<CompraDto> listarEstornadasPorIdCartao(@RequestParam(required = true) Long id, String dtInicio, String dtFim,
                                                      @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                              Pageable paginacao) {
        return compraService.listarComprasEstornadasPorIdCartao(id, dtInicio, dtFim, paginacao);
    }

    @GetMapping("/listar/pendentespgto/poridcartao")
    public Page<CompraDto> listarPendentesPgtoPorIdCartao(@RequestParam(required = true) Long id, String dtInicio, String dtFim,
                                                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                               Pageable paginacao) {
        return compraService.listarComprasPendentesPgtoPorIdCartao(id, dtInicio, dtFim, paginacao);
    }

    @GetMapping("/listar/pagas/poridcartao")
    public Page<CompraDto> listarPagasPorIdCartao(@RequestParam(required = true) Long id, String dtInicio, String dtFim,
                                                          @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                                  Pageable paginacao) {
        return compraService.listarComprasPagasPorIdCartao(id, dtInicio, dtFim, paginacao);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompraDto> cadastrar(@RequestBody @Valid CompraForm form,
                                            UriComponentsBuilder uriBuilder) {
        CompraDto novaCompra = compraService.cadastrar(form);
        if (novaCompra != null) {
            URI uri = uriBuilder.path("/compras/{id}").buildAndExpand(novaCompra.getId()).toUri();
            return ResponseEntity.created(uri).body(novaCompra);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDto> detalhar(@PathVariable Long id) {
        CompraDto compra = compraService.detalhar(id);
        if (compra != null) {
            return ResponseEntity.ok(compra);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/estornar/{uuidpagamento}")
    @Transactional
    public ResponseEntity<CompraDto> estornar(@PathVariable String uuidpagamento) {
        CompraDto compra = compraService.estornar(uuidpagamento);
        if (compra != null) {
            return ResponseEntity.ok().body(compra);
        }
        return ResponseEntity.notFound().build();
    }

}
