package ks.roleplaying.security.impl;

import ks.roleplaying.model.Usuario;
import ks.roleplaying.repository.UsuarioRepository;
import ks.roleplaying.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> findByLogin(String login) {
		return Optional.ofNullable(this.usuarioRepository.findByLogin(login));
	}
}
