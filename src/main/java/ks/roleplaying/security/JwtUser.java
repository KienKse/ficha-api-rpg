package ks.roleplaying.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -268046329085485932L;

	private Long id;
	private String login;
	private String senha;

	public JwtUser(Long id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//TODO: VERIFICAR TRETA
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
