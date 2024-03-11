package com.example.walletweb.dto;

import com.example.walletweb.model.TipoAtivo;
import org.antlr.v4.runtime.misc.NotNull;

public record ExcluiAtivoDTO(@NotNull String ticker, TipoAtivo tipo) {
}
