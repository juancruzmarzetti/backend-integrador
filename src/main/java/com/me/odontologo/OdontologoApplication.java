package com.me.odontologo;

import com.me.odontologo.dao.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OdontologoApplication {

	public static void main(String[] args) {
		DB.crearTablas();
		SpringApplication.run(OdontologoApplication.class, args);
	}

}
