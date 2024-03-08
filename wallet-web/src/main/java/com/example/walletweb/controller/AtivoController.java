package com.example.walletweb.controller;

import com.example.walletweb.dto.AtivoAtualizaDTO;
import com.example.walletweb.dto.CadastroAtivoDTO;
import com.example.walletweb.dto.ListagemAtivoDTO;
import com.example.walletweb.service.AtivoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ativos")
public class AtivoController {

    @Autowired
    private AtivoService service;

    @GetMapping
    public List<ListagemAtivoDTO> listarExibir (){
        return service.obterTodosAtivos();
    }
    @PostMapping
    public void cadastrarAtivo(@RequestBody CadastroAtivoDTO cadastroAtivoDTO){
        service.cadastrarAtivo(cadastroAtivoDTO);
    }
    @PutMapping
    @Transactional
    public void atualizaAtivo(@RequestBody AtivoAtualizaDTO ativoAtualizaDTO){
        service.buscaAtivoTicker(ativoAtualizaDTO);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluiAtivo(@PathVariable Long id){
        service.excluiAtivoDefinitivo(id);

    }
}
