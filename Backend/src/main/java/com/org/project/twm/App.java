package com.org.project.twm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.org.project.twm.model.Provider;
import com.org.project.twm.repository.ProviderRepository;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	CommandLineRunner init(ProviderRepository providerRepository) {
		return args -> {
			Provider provider1 = providerRepository.findByProvidername("weatherunlocked");
			if (provider1 == null) {
				provider1 = new Provider();
				provider1.setProvidername("weatherunlocked");
				providerRepository.save(provider1);
			}
			Provider provider2 = providerRepository.findByProvidername("weatherbit");
			if (provider2 == null) {
				provider2 = new Provider();
				provider2.setProvidername("weatherbit");
				providerRepository.save(provider2);
			}
			Provider provider3 = providerRepository.findByProvidername("darksky");
			if (provider3 == null) {
				provider3 = new Provider();
				provider3.setProvidername("darksky");
				providerRepository.save(provider3);
			}
			Provider provider4 = providerRepository.findByProvidername("openweathermap");
			if (provider4 == null) {
				provider4 = new Provider();
				provider4.setProvidername("openweathermap");
				providerRepository.save(provider4);
			}
		};
	}

}
