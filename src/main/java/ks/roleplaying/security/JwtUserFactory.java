package ks.roleplaying.security;

import ks.roleplaying.model.Usuario;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getLogin(), usuario.getSenha());
	}

}
