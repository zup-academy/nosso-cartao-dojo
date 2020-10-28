package br.com.zup.nossocartao.propostas.repository;

import br.com.zup.nossocartao.propostas.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, String> {

}