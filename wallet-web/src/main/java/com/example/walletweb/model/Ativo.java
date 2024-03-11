package com.example.walletweb.model;

import com.example.walletweb.dto.AtualizaAtivoDTO;
import com.example.walletweb.dto.CadastroAtivoDTO;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ativos")
@Getter
public class Ativo {

    @Id
    @Column(unique = true)
    private String ticker;

    @Enumerated(EnumType.STRING)
    private TipoAtivo tipo;

    @OneToMany(mappedBy = "ativo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Compras> listaCompras = new ArrayList<>();

    private Boolean ativado;
    public Ativo(){}

    public Ativo(String ticker, TipoAtivo tipo) {
        this.ticker = ticker;
        this.tipo = tipo;
    }

    public Ativo(CadastroAtivoDTO cadastroAtivoDTO) {
        this.ativado = true;
        this.ticker = cadastroAtivoDTO.ticker();
        this.tipo = cadastroAtivoDTO.tipo();
    }


    public void atualizarTipo(AtualizaAtivoDTO atualizaAtivoDTO){
        if (atualizaAtivoDTO.tipo() != null){
            this.tipo = atualizaAtivoDTO.tipo();
        }

        // somente o id em PUT, remove da lixeira
        this.ativado = true;

    }

    public void excluir() {
        this.ativado = false;
    }


    @Override
    public String toString() {
        return
                "ticker =" + ticker +
                ", tipo = " + tipo +
                ", compras = " + listaCompras
                ;

    }

}
