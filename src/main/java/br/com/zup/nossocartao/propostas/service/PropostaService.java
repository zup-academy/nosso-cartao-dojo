package br.com.zup.nossocartao.propostas.service;

import br.com.zup.nossocartao.propostas.Proposta;
import br.com.zup.nossocartao.propostas.repository.PropostaRepository;
import br.com.zup.nossocartao.propostas.resource.PropostaCriada;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @Transactional
    public PropostaCriada criar(Proposta proposta){
        final var propostaPersistida = this.propostaRepository.save(proposta);
        return new PropostaCriada(propostaPersistida.getId());
    }

}
