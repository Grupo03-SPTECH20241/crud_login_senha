package school.sptech.crudloginsenha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.crudloginsenha.dto.usuario.UsuarioDetalhesDto;
import school.sptech.crudloginsenha.entity.Usuario;
import school.sptech.crudloginsenha.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuario: %s nao encontrado", username));
        }

        return new UsuarioDetalhesDto(usuarioOpt.get().getNome(), usuarioOpt.get().getEmail(), usuarioOpt.get().getSenha());

    }
}
