package com.emsi.pfa;

import com.emsi.pfa.entity.Administrateur;
import com.emsi.pfa.repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@SpringBootApplication
public class PressAgencyApplication  implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(PressAgencyApplication.class, args);
	}


	@Autowired
	AdministrateurRepository administrateurRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (this.administrateurRepository.findByEmail("kay.technologie.gdr@gmail.com")==null)
		{
			Administrateur admin=new Administrateur();
			admin.setEmail("kay.technologie.gdr@gmail.com");
			admin.setPassword(bCryptPasswordEncoder.encode("12345"));
            this.administrateurRepository.save(admin);
		}
	}

	@Bean(name = "passwordEncoder")
	public static  BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}


}
