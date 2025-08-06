package com.ipss.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	/*
	 * Deberías poder acceder a http://localhost:8080/login y ver la pantalla de inicio de sesión. 
	 * Podrás iniciar sesión con los usuarios de prueba (admin/admin o comensal/pass123)
	 *  o crear un nuevo usuario y registrarlo, lo cual te redirigirá a las páginas que
	 *  aún no hemos creado (pero que no causarán un error fatal).
	 */

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}