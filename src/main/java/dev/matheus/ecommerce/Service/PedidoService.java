package dev.matheus.ecommerce.Service;

import dev.matheus.ecommerce.DTO.PedidoCreateDTO;
import dev.matheus.ecommerce.Exceptions.BusinessException;
import dev.matheus.ecommerce.Model.Pedido;
import dev.matheus.ecommerce.Model.PedidoItem;
import dev.matheus.ecommerce.Model.Produto;
import dev.matheus.ecommerce.Repository.PedidoRepository;
import dev.matheus.ecommerce.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository repository, ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    // Cria um novo pedido
    @Transactional
    public Pedido create(PedidoCreateDTO dto) {
        Pedido pedido = new Pedido();

        BigDecimal total = BigDecimal.ZERO;

        for (PedidoCreateDTO.PedidoItemDTO itemDto : dto.items()) {
            Produto produto = produtoService.findById(itemDto.produtoId());

            if (itemDto.quantidade() <= 0) {
                throw new BusinessException("Quantidade deve ser maior que zero");
            }

            if (produto.getQuantidade() < itemDto.quantidade()) {
                throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
            }

            BigDecimal precoUnitario = produto.getPreco();
            BigDecimal quantidade = BigDecimal.valueOf(itemDto.quantidade());
            BigDecimal subtotal = precoUnitario.multiply(quantidade);

            PedidoItem pedidoItem = new PedidoItem();
            pedidoItem.setProduto(produto);
            pedidoItem.setQuantidade(itemDto.quantidade());
            pedidoItem.setPrecoUnitario(precoUnitario);
            pedidoItem.setPedido(pedido);

            pedido.getItems().add(pedidoItem);

            produto.setQuantidade(produto.getQuantidade() - itemDto.quantidade());
            produtoRepository.save(produto);

            total = total.add(subtotal);
        }

        pedido.setPrecoTotal(total);
        Pedido save = repository.save(pedido);

        return save;
    }
}
