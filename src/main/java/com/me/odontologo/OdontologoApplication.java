package com.me.odontologo;

import com.me.odontologo.repository.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OdontologoApplication {

	public static void main(String[] args) {
		DB.crearTablas();
		SpringApplication.run(OdontologoApplication.class, args);
	}

}
