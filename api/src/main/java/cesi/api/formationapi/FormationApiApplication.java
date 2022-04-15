package cesi.api.formationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class FormationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormationApiApplication.class, args);
    }

}
