package com.example.tpDevOps;

import com.example.tpDevOps.entities.Demandeur;
import com.example.tpDevOps.sec.service.SecurityService;
import com.example.tpDevOps.service.DemandeurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TpDevOpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpDevOpsApplication.class, args);
	}

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner saveUser(SecurityService securityService){
        return args -> {
            //securityService.saveNewUser("kasse","11111","11111");
            //securityService.saveNewUser("abi","12345","12345");
            //securityService.saveNewUser("adi","12345","12345");

            //securityService.saveNewRole("USER","");
            //securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("kasse","USER");
            securityService.addRoleToUser("kasse","ADMIN");
            securityService.addRoleToUser("abi","USER");
            securityService.addRoleToUser("adi","ADMIN");


        };
    }

   //@Bean
    CommandLineRunner start(DemandeurService demandeurService){
        return args -> {
            Demandeur demandeur = new Demandeur();
            demandeur.setNom("ALI");
            demandeur.setPrenom("Mohamed");
            demandeur.setEmail("al@gmail.com");
            demandeurService.addDemandeur(demandeur);
        };
    }


}
