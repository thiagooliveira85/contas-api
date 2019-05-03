package com.contas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contas.api.model.Conta;


@Repository
public interface ContasRepository extends JpaRepository<Conta, Long> {

}
