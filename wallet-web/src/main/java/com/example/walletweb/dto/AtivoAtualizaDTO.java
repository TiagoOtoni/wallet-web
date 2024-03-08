package com.example.walletweb.dto;

import com.example.walletweb.model.TipoAtivo;
import org.antlr.v4.runtime.misc.NotNull;

public record AtivoAtualizaDTO(
        @NotNull
        Long id,
        String ticker,
        TipoAtivo tipo,
        boolean ativado) {
}
