package br.com.zup.nossocartao.propostas.resource;

import javax.validation.constraints.NotBlank;

public class PropostaCriada {

    private String id;

    public PropostaCriada(@NotBlank String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}