package com.example.hotel.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapperconfig {
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

}
