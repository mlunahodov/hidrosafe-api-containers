package br.com.HidroSafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.HidroSafe.model.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long>{}
