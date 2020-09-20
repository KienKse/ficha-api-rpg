package ks.roleplaying.security.dto;

import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {

	@NotEmpty(message = "Login é necessário")
	private String login;

	@NotEmpty(message = "Senha é necessário")
	private String senha;

	public JwtAuthenticationDto() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [login=" + login + ", senha=" + senha + "]";
	}

}
