package com.example.walletweb.dto;

import com.example.walletweb.model.Ativo;
import com.example.walletweb.model.TipoAtivo;

public record ListagemAtivoDTO(Long id, String ticker, TipoAtivo tipo, Boolean ativado) {
    public ListagemAtivoDTO(Ativo ativo){
        this(ativo.getId(),ativo.getTicker(),ativo.getTipo(),ativo.getAtivado());

    }
}
