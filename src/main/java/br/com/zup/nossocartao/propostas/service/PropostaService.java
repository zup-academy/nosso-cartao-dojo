package br.com.zup.nossocartao.propostas.service;

import br.com.zup.nossocartao.propostas.Proposta;
import br.com.zup.nossocartao.propostas.repository.PropostaRepository;
import org.hibernate.validator.constraints.br.CPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Service
public class PropostaService {

    private final Logger log = LoggerFactory.getLogger(PropostaService.class);
    private final PropostaRepository propostaRepository;
    private final RestTemplate restTemplate;
    private final String analiseHost;

    public PropostaService(PropostaRepository propostaRepository, RestTemplate restTemplate,
                           @Value("${analise.host}") String analiseHost) {
        this.propostaRepository = propostaRepository;
        this.restTemplate = restTemplate;
        this.analiseHost = analiseHost;
    }

    @Transactional
    public Proposta criar(@NotBlank @CPF String documento, @NotBlank @Email String email, @NotBlank String nome,
                          @NotNull @Positive BigDecimal salario, @NotBlank String endereco) {

        final var proposta = new Proposta(documento, email, nome, salario, endereco);
        final var propostaPersistida = this.propostaRepository.save(proposta);

        final var url = analiseHost + "/api/solicitacao";
        final var analisePropostaRequest = propostaPersistida.toAnalisePropostaRequest();

        log.info("Chamando {} {}", url, analisePropostaRequest);
        final var analisePropostaResponse = this.restTemplate.postForObject(url, analisePropostaRequest, AnalisePropostaResponse.class);
        propostaPersistida.setStatus(analisePropostaResponse.getResultadoSolicitacao().toPropostaStatus());

        return propostaPersistida;
    }

}
