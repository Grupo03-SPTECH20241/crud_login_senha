package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProdutoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdutoPedido;
    private Integer qtProduto;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "fkProduto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fkPersonalizacao")
    private Personalizacao personalizacao;

    @ManyToOne
    @JoinColumn(name = "fkPedido")
    private Pedido pedido;
}
