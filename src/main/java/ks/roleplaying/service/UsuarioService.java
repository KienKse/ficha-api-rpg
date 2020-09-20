package ks.roleplaying.service;

import ks.roleplaying.model.Usuario;
import ks.roleplaying.repository.UsuarioRepository;
import ks.roleplaying.security.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario request) {
        return usuarioRepository.save(new Usuario(request.getLogin(), PasswordUtils.gerarBCrypt(request.getSenha())));
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.getOne(id);
    }

    public Usuario getByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public ResponseEntity<Object> updateUsuario(Usuario usuarioDetails, Long usuarioId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

        if(!usuarioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioDetails.setId(usuarioId);

        usuarioRepository.save(usuarioDetails);

        return ResponseEntity.noContent().build();
    }


}
