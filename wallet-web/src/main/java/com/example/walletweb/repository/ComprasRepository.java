package com.example.walletweb.repository;

import com.example.walletweb.model.Ativo;
import com.example.walletweb.model.Compras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprasRepository extends JpaRepository<Compras, Long> {
}
