package com.example.walletweb.dto;

import com.example.walletweb.model.TipoAtivo;
import org.antlr.v4.runtime.misc.NotNull;

public record AtualizaAtivoDTO(@NotNull String ticker, TipoAtivo tipo, boolean ativado) {
}
