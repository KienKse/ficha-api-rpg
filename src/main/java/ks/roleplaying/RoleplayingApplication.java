package ks.roleplaying;

import ks.roleplaying.model.Usuario;
import ks.roleplaying.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RoleplayingApplication {

	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(RoleplayingApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void event() {
		Usuario usuario = usuarioRepository.findByLogin("123");
		if(usuario == null) {
			usuarioRepository.save(new Usuario("123","$2a$10$/AxDFOm8OWS1wcDAwxs2GO1RIzd.TjCQdMNy2RPtibaL/Z6XIwrEO"));
		}
	}

}
