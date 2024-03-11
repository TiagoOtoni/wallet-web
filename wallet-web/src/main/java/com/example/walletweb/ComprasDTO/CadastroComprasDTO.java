package com.example.walletweb.ComprasDTO;

import com.example.walletweb.model.Ativo;
import com.example.walletweb.model.Compras;
import com.example.walletweb.model.TipoAtivo;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CadastroComprasDTO(
        @NotBlank
        String ticker,
        @NotBlank
        String data,
        @NotBlank
        double preco,
        @NotBlank
        int quantidade,
        @NotBlank
        double emolumentos,
        Ativo ativo
) {
}
