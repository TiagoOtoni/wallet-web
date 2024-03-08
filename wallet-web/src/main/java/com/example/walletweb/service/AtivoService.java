package com.example.walletweb.service;

import com.example.walletweb.dto.AtivoAtualizaDTO;
import com.example.walletweb.dto.CadastroAtivoDTO;
import com.example.walletweb.dto.ListagemAtivoDTO;
import com.example.walletweb.model.Ativo;
import com.example.walletweb.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class AtivoService {
    @Autowired
    private AtivoRepository repository;


    public List<ListagemAtivoDTO> obterTodosAtivos(){
        return converteDados(repository.findAllByAtivadoTrue());
    }

    public void cadastrarAtivo(CadastroAtivoDTO cadastroAtivoDTO){
          repository.save(new Ativo(cadastroAtivoDTO));

    }
    public void buscaAtivoTicker(AtivoAtualizaDTO ativoAtualizaDTO){
       var buscaTicker = repository.getReferenceById(ativoAtualizaDTO.id());
           buscaTicker.atualizarTipo(ativoAtualizaDTO);


    }
    public void excluiAtivoDefinitivo(Long id){
        var buscaTicker = repository.getReferenceById(id);
        buscaTicker.excluir();
    }

    private List<ListagemAtivoDTO> converteDados(List<Ativo> ativos){
        return ativos.stream()
                .map(a -> new ListagemAtivoDTO(a.getId(),a.getTicker(), a.getTipo(),a.getAtivado()))
                .collect(Collectors.toList());
    }
}
