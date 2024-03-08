package com.example.walletweb.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "compras")
@Getter
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private double preco;
    private int quantidade;
    private double emolumentos;
    private double liquido = 0;
    @ManyToOne
    private Ativo ativo;

    public Compras(){}

    public Compras(String data, double preco, int quantidade, double emolumentos) {
        this.data = data;
        this.preco = preco;
        this.quantidade = quantidade;
        this.emolumentos = emolumentos;
        calculaLiquidoEmolumentos();
    }

    private void calculaLiquidoEmolumentos(){
        this.liquido = (this.quantidade * this.preco) + this.emolumentos;
    }

    @Override
    public String toString() {
        return
                "data = " + data  +
                ", preco = " + preco +
                ", quantidade = " + quantidade +
                ", emolumentos = " + emolumentos + "\n";

    }
}
