package ks.roleplaying;

import ks.roleplaying.enums.MoedaEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleplayingApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void deveConverterTibares() {
		final BigDecimal BASE = BigDecimal.valueOf(1000);
		final MoedaEnum moeda = MoedaEnum.T$;
		Assert.assertEquals(BASE, MoedaEnum.converterTibar(BASE, moeda));
	}

	@Test
	public void deveConverter() {
		final MoedaEnum moeda = MoedaEnum.TP;
		Assert.assertEquals(BigDecimal.valueOf(0.1), MoedaEnum.converterTibar(BigDecimal.ONE, moeda));
	}

}
