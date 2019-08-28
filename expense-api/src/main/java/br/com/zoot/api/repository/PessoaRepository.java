package br.com.zoot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zoot.api.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

}
