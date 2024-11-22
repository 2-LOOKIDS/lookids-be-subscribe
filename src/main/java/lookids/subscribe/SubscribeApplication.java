package lookids.subscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableCaching
//@EnableDiscoveryClient
public class SubscribeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscribeApplication.class, args);
	}

}
