package ks.roleplaying.security.controllers;

import io.swagger.annotations.ApiOperation;
import ks.roleplaying.security.dto.JwtAuthenticationDto;
import ks.roleplaying.security.dto.TokenDto;
import ks.roleplaying.security.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping()
	@ApiOperation("Autenticar")
	public ResponseEntity<TokenDto> gerarTokenJwt(@Valid @RequestBody JwtAuthenticationDto authenticationDto) throws AuthenticationException {

		log.info("Gerando token para o login {}.", authenticationDto.getLogin());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationDto.getLogin(), authenticationDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getLogin());
		String token = jwtTokenUtil.obterToken(userDetails);

		return ResponseEntity.ok(new TokenDto(token));
	}

	@PostMapping(value = "/refresh")
	public ResponseEntity<TokenDto> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh token JWT.");
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}

		String refreshedToken = jwtTokenUtil.refreshToken(token.get());

		return ResponseEntity.ok(new TokenDto(refreshedToken));
	}

}
