package com.gustavoj.projetotcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gustavoj.projetotcc.domain.Categoria;
import com.gustavoj.projetotcc.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjetotccApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetotccApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(1,"Informatica");
		Categoria cat2 = new Categoria(2,"Comida");
		
		repo.saveAll(Arrays.asList(cat1,cat2));
	}
	
	
	
	

}
