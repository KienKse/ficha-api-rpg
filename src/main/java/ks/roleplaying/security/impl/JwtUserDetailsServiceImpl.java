package ks.roleplaying.security.impl;

import ks.roleplaying.model.Usuario;
import ks.roleplaying.security.JwtUserFactory;
import ks.roleplaying.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioService.getByLogin(login));

		if (usuario.isPresent()) {
			return JwtUserFactory.create(usuario.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}
}
