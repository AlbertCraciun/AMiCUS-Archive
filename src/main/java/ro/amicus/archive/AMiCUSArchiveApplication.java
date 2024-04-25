package ro.amicus.archive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class AMiCUSArchiveApplication {

    public static void main(String[] args) {
        System.out.println("Hello, AMiCUS Archive!");
        SpringApplication.run(AMiCUSArchiveApplication.class, args);
    }

}
