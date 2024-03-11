package com.example.walletweb.controller;

import com.example.walletweb.ComprasDTO.CadastroComprasDTO;
import com.example.walletweb.ComprasDTO.DetalhesCompraDTO;
import com.example.walletweb.dto.*;
import com.example.walletweb.service.AtivoService;
import com.example.walletweb.service.ComprasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("ativos")
public class AtivoController {

    @Autowired
    private AtivoService service;

    @Autowired
    private ComprasService comprasService;

    @GetMapping
    public ResponseEntity<List<ListagemAtivoDTO>> listarExibir (){
        var todosAtivos = service.obterTodosAtivos();
        return ResponseEntity.ok(todosAtivos);
    }

    @GetMapping("/detalhes/{ticker}")
    public ResponseEntity detalhesAtivo(@PathVariable String ticker){
        var ativo = service.obterDetalhesAtivo(ticker);
        return ResponseEntity.ok(new DetalhesAtivoDTO(ativo));
    }

    @GetMapping("/detalhes/compra/{id}")
    public ResponseEntity detalhesCompra(@PathVariable Long id) {
        var compra = comprasService.obterDetalheCompra(id);
        return ResponseEntity.ok(new DetalhesCompraDTO(compra));
    }
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAtivo(@RequestBody CadastroAtivoDTO cadastroAtivoDTO, UriComponentsBuilder uriBuilder){
        var ativo = service.cadastrarAtivo(cadastroAtivoDTO);
        var uri = uriBuilder.path("/ativos/{ticker}").buildAndExpand(ativo.getTicker()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesAtivoDTO(ativo));
    }


    //isso me cheira a gambiarra
    @PostMapping("/cadastrar/compra")
    @Transactional
    public ResponseEntity cadastrarCompra(@RequestBody CadastroComprasDTO cadastroComprasDTO, UriComponentsBuilder uriBuilder){
       var ativo = service.cadastrarCompra(cadastroComprasDTO);

       var lista = ativo.getListaCompras().size() -1;
       var lista2 = ativo.getListaCompras().get(lista);
       var uri = uriBuilder.path("/detalhes/compra/{id}").buildAndExpand(ativo.getListaCompras().get(lista).getId()).toUri();

       return ResponseEntity.created(uri).body(new DetalhesCompraDTO(lista2));
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizaTipoAtivo(@RequestBody AtualizaAtivoDTO atualizaAtivoDTO){
       var ativo = service.atualizaTipo(atualizaAtivoDTO);
        return ResponseEntity.ok(new ListagemAtivoDTO(ativo) );
    }
    @DeleteMapping("/desativa")
    @Transactional
    public ResponseEntity excluiAtivo(@RequestBody ExcluiAtivoDTO excluiAtivoDTO){
        service.excluiDesativa(excluiAtivoDTO);
        return ResponseEntity.noContent().build();

    }
}
