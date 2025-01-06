package ai.OpenAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OpenAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAiApplication.class, args);
	}

}
