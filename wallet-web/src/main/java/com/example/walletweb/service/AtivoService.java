package com.example.walletweb.service;

import com.example.walletweb.ComprasDTO.CadastroComprasDTO;
import com.example.walletweb.dto.AtualizaAtivoDTO;
import com.example.walletweb.dto.CadastroAtivoDTO;
import com.example.walletweb.dto.ExcluiAtivoDTO;
import com.example.walletweb.dto.ListagemAtivoDTO;
import com.example.walletweb.model.Ativo;
import com.example.walletweb.model.Compras;
import com.example.walletweb.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtivoService {
    @Autowired
    private AtivoRepository repository;


    public List<ListagemAtivoDTO> obterTodosAtivos() {
        return converteDados(repository.findAllByAtivadoTrue());
    }

    public Ativo obterDetalhesAtivo(String ticker) {
        return repository.findByTickerContainingIgnoreCase(ticker);
    }


    public Ativo cadastrarAtivo(CadastroAtivoDTO cadastroAtivoDTO) {
        var cadastro = new Ativo(cadastroAtivoDTO);
        repository.save(cadastro);
        return cadastro;
    }


    public Ativo cadastrarCompra(CadastroComprasDTO cadastroComprasDTO) {
        var ativoBuscado = repository.findByTickerContainingIgnoreCase(cadastroComprasDTO.ticker());
        var novaCompra = new Compras(cadastroComprasDTO.data(), cadastroComprasDTO.preco(), cadastroComprasDTO.quantidade(), cadastroComprasDTO.emolumentos());
        novaCompra.setAtivo(ativoBuscado);
        ativoBuscado.getListaCompras().add(novaCompra);
        repository.save(ativoBuscado);
        return ativoBuscado;
    }

    public Ativo atualizaTipo(AtualizaAtivoDTO atualizaAtivoDTO) {
        var buscaTicker = repository.findByTickerContainingIgnoreCase(atualizaAtivoDTO.ticker());
        buscaTicker.atualizarTipo(atualizaAtivoDTO);
        return buscaTicker;
    }

    public void excluiDesativa(ExcluiAtivoDTO excluiAtivoDTO) {
        var buscaTicker = repository.findByTickerContainingIgnoreCase(excluiAtivoDTO.ticker());
        Optional<ExcluiAtivoDTO> a = Optional.of(excluiAtivoDTO);
        if (excluiAtivoDTO.tipo().name() == "EXCLUIR"){
            repository.delete(buscaTicker);
       }
        buscaTicker.excluir();
    }

    private List<ListagemAtivoDTO> converteDados(List<Ativo> ativos) {
        return ativos.stream()
                .map(a -> new ListagemAtivoDTO(a.getTicker(), a.getTipo(), a.getAtivado()))
                .collect(Collectors.toList());
    }




}



