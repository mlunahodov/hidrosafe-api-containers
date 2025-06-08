package br.com.HidroSafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.HidroSafe.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long>{}
