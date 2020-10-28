package br.com.zup.nossocartao.propostas;

import br.com.zup.nossocartao.propostas.service.AnalisePropostaRequest;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "proposta")
public class Proposta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotBlank
    @CPF
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal salario;

    @NotBlank
    private String endereco;

    @Deprecated
    public Proposta(){}

    public Proposta(@NotBlank @CPF String documento, @NotBlank @Email String email, @NotBlank String nome, @NotNull @Positive BigDecimal salario, @NotBlank String endereco) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getEndereco() {
        return endereco;
    }

    public AnalisePropostaRequest toAnalisePropostaRequest() {
        return new AnalisePropostaRequest(this.documento, this.nome, this.id);
    }

}
