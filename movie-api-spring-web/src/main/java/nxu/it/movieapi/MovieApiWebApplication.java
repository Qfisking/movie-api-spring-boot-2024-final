package nxu.it.movieapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MovieApiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiWebApplication.class, args);
	}

}
