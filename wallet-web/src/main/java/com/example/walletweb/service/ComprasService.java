package com.example.walletweb.service;

import com.example.walletweb.model.Compras;
import com.example.walletweb.repository.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprasService {
    @Autowired
    private ComprasRepository repository;

    public Compras obterDetalheCompra(Long id){
        return repository.getReferenceById(id);


    }
}
