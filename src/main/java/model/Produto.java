package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Produto")
@Table(name = "produto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull(message = "Código é obrigatório")
    @NotEmpty(message = "Código não pode estar vazio")
    @Column(nullable = false, length = 50, unique = true)
    private String codigo;
    
    @NotNull(message = "Descrição é obrigatória")
    @NotEmpty(message = "Descrição não pode estar vazia")
    @Column(nullable = false, length = 255)
    private String descricao;
    
    @NotNull(message = "Data de entrada é obrigatória")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_entrada;
    
    @NotNull(message = "Data de validade é obrigatória")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_validade;
    
    @NotNull(message = "Quantidade é obrigatória")
    @PositiveOrZero(message = "Quantidade deve ser zero ou positiva")
    @Column(nullable = false)
    private Integer quantidade;


    public Produto(Long id, String codigo, String descricao,
                   Date data_entrada, Date data_validade,
                   Integer quantidade) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.data_entrada = data_entrada;
        this.data_validade = data_validade;
        this.quantidade = quantidade;
    }

    public Produto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
