package com.example.walletweb.ComprasDTO;

import com.example.walletweb.model.Compras;
import jakarta.validation.constraints.NotBlank;

public record DetalhesCompraDTO(Long id,String data, double preco, int quantidade,double emolumentos,double liquido) {

    public DetalhesCompraDTO(Compras compras){
        this(compras.getId(),compras.getData(),compras.getPreco(),compras.getQuantidade(),compras.getEmolumentos(),compras.getLiquido());
    }

}
