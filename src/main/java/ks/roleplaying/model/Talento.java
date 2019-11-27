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
    
    @Column(name = "BENEFICIO", nullable = false)
    private String beneficio;
    
    @Column(name = "NORMAL")
    private String normal;
    
    @Column(name = "ESPECIAL")
    private String especial;

    public Talento() {
        //EMPTY
    }

    public Talento(String nome, String preRequisito, String beneficio, String normal, String especial) {
        this.nome = nome;
        this.preRequisito = preRequisito;
        this.beneficio = beneficio;
        this.normal = normal;
        this.especial = especial;
    }

    public Talento(String nome, String preRequisito, String beneficio) {
        this.nome = nome;
        this.preRequisito = preRequisito;
        this.beneficio = beneficio;
    }
    
    public Talento(Talento request) {
        this.nome = request.nome;
        this.preRequisito = request.preRequisito;
        this.beneficio = request.beneficio;
        this.normal = request.normal;
        this.especial = request.especial;
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
                                            
}
