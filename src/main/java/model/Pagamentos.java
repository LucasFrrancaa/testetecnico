package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Pagamentos")
@Table(name = "pagamentos")
public class Pagamentos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;
    
    @NotNull(message = "Produto é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    @Column(nullable = false)
    private Integer quantidade;
    
    @NotNull(message = "Data de pagamento é obrigatória")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_pagamento;
    
    @Temporal(TemporalType.DATE)
    private Date data_entrega;

    public Pagamentos(Long id, Usuarios usuario, Produto produto,
                     Integer quantidade, Date data_pagamento, Date data_entrega) {
        this.id = id;
        this.usuario = usuario;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data_pagamento = data_pagamento;
        this.data_entrega = data_entrega;
    }

    public Pagamentos() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public Date getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }
}
