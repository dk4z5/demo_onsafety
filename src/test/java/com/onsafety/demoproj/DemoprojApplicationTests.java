package com.onsafety.demoproj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.onsafety.demoproj.services.ValidaService;

@SpringBootTest
class DemoprojApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(ValidaService.ValidaCPF("54605170600"), "54605170600");
		assertEquals(ValidaService.ValidaCPF("546.051.706-00"), "54605170600");
	}

}
