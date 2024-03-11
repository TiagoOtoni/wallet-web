package com.example.walletweb.dto;

import com.example.walletweb.model.Ativo;
import com.example.walletweb.model.Compras;
import com.example.walletweb.model.TipoAtivo;

import java.util.List;

public record DetalhesAtivoDTO(String ticker, TipoAtivo tipo, Boolean ativado, List<Compras> compras) {
    public DetalhesAtivoDTO(Ativo ativo){
        this(ativo.getTicker(),ativo.getTipo(),ativo.getAtivado(),ativo.getListaCompras());

    }
}
