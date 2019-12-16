package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
//@Table(name = "TALENTO", schema = "rpg")
@Table(name = "TALENTO")
@EntityListeners(AuditingEntityListener.class)
public class Talento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "PRE_REQUISITO", nullable = false)
    private String preRequisito;
    
    @Column(name = "BENEFICIO", nullable = false, columnDefinition="LONGTEXT")
    private String beneficio;
    
    @Column(name = "OBS")
    private String observacao;
    
    public Talento() {
        //EMPTY
    }

    public Talento(String nome, String preRequisito, String beneficio, String observacao) {
        this.nome = nome;
        this.preRequisito = preRequisito;
        this.beneficio = beneficio;
        this.observacao = observacao;
    }

    public Talento(Talento request) {
        this.nome = request.nome;
        this.preRequisito = request.preRequisito;
        this.beneficio = request.beneficio;
        this.observacao = request.observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreRequisito() {
        return preRequisito;
    }

    public void setPreRequisito(String preRequisito) {
        this.preRequisito = preRequisito;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
