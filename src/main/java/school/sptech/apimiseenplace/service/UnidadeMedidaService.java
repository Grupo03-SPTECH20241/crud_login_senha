package school.sptech.apimiseenplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apimiseenplace.dto.unidadeMedida.UnidadeMedidaCriacaoDto;
import school.sptech.apimiseenplace.dto.unidadeMedida.UnidadeMedidaMapper;
import school.sptech.apimiseenplace.entity.UnidadeMedida;
import school.sptech.apimiseenplace.exception.NaoEncontradoException;
import school.sptech.apimiseenplace.repository.UnidadeMedidaRepository;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {
    private final UnidadeMedidaRepository unidadeMedidaRepository;

    public UnidadeMedida buscarPorId(Integer id) {
        return unidadeMedidaRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Unidade de Medida")
        );
    }

    public UnidadeMedida criar(UnidadeMedidaCriacaoDto unidadeMedidaCriacaoDto) {
        UnidadeMedida unidadeMedida = UnidadeMedidaMapper.toEntity(unidadeMedidaCriacaoDto);
        return unidadeMedidaRepository.save(unidadeMedida);
    }
}