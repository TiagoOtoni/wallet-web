package com.example.walletweb.repository;


import com.example.walletweb.model.Ativo;
import com.example.walletweb.model.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    Ativo findByTickerContainingIgnoreCase(String ticker);



    @Query("SELECT a FROM Ativo a order by function('RANDOM') LIMIT 1")
    Ativo buscaAleatoria();


    List<Ativo> findAllByAtivadoTrue();
}
