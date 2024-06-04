package school.sptech.apimiseenplace.poc.fila;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.apimiseenplace.dto.pedido.PedidoListagemDTO;
import school.sptech.apimiseenplace.dto.pedido.PedidoMapper;
import school.sptech.apimiseenplace.entity.Pedido;
import school.sptech.apimiseenplace.repository.PedidoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/fila")
@RequiredArgsConstructor
public class FilaPedidoController {

    private final PedidoRepository pedidoRepository;
    private final FilaPedido filaNovos = new FilaPedido();

    @PostMapping
    public ResponseEntity<Void> atualizarFila() {
        Optional<Pedido> ultimoPedido = pedidoRepository.getUltimoPedido();
        if (ultimoPedido.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (filaNovos.isFull()) {
            this.filaNovos.poll();
        }
        this.filaNovos.insert(ultimoPedido.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PedidoListagemDTO[]> listar() {
        PedidoListagemDTO[] pedidoListagemDTOS = PedidoMapper.toDto(this.filaNovos.getFila());
        if (pedidoListagemDTOS == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidoListagemDTOS);
    }

}
