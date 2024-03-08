package com.example.walletweb.dto;

import com.example.walletweb.model.TipoAtivo;
import jakarta.validation.constraints.NotBlank;

public record CadastroAtivoDTO(
        @NotBlank
        String ticker,
        @NotBlank
        TipoAtivo tipo) {
}
