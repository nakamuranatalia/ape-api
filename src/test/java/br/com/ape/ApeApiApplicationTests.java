package br.com.ape;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = ApeApiApplication.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ApeApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
