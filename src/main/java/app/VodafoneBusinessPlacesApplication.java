package app;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("app")
public class VodafoneBusinessPlacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VodafoneBusinessPlacesApplication.class, args);
	}

}
