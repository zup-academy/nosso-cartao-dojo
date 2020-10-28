package br.com.zup.nossocartao.propostas.resource;

import br.com.zup.nossocartao.propostas.service.PropostaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/propostas")
public class PropostaResource {

    private final PropostaService propostaService;

    public PropostaResource(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping
    public ResponseEntity<PropostaCriada> nova(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder builder){

        final var proposta = this.propostaService.criar(request.getDocumento(), request.getEmail(),
                request.getNome(), request.getSalario(), request.getEndereco());

        final var propostaCriada = new PropostaCriada(proposta.getId());

        return ResponseEntity.created(builder.path("/api/propostas/{id}")
                .buildAndExpand(propostaCriada.getId()).toUri())
                .body(propostaCriada);
    }

}
