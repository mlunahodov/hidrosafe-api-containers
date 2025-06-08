package br.com.HidroSafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.HidroSafe.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {}
