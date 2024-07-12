package com.datadoghq.workshops.samplevulnerablejavaapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleVulnerableJavaAppApplicationTests {

	@Autowired
	private MainController mainController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(mainController);
	}
}
