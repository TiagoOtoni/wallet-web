package com.example.walletweb.model;

import com.example.walletweb.ComprasDTO.CadastroComprasDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "compras")
@Setter
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
    @Getter(AccessLevel.NONE)
    private Ativo ativo;

    public Compras(){}

    public Compras(String data, double preco, int quantidade, double emolumentos) {
        this.data = data;
        this.preco = preco;
        this.quantidade = quantidade;
        this.emolumentos = emolumentos;
        calculaLiquidoEmolumentos();
    }
    public Compras(CadastroComprasDTO cadastroComprasDTO){
        this.data = cadastroComprasDTO.data();
        this.preco = cadastroComprasDTO.preco();
        this.quantidade = cadastroComprasDTO.quantidade();
        this.emolumentos = cadastroComprasDTO.emolumentos();

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
