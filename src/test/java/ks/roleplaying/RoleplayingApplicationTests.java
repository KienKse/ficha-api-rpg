package ks.roleplaying;

import ks.roleplaying.security.PasswordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleplayingApplicationTests {

	@Test
	public void contextLoads() {
	}
/*
	@Test
	public void generate() {
		// $2a$10$/AxDFOm8OWS1wcDAwxs2GO1RIzd.TjCQdMNy2RPtibaL/Z6XIwrEO
		System.out.println(PasswordUtils.gerarBCrypt("123"));
	}
*/
}
