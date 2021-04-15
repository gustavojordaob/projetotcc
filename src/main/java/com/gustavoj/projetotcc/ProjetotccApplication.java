package com.gustavoj.projetotcc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gustavoj.projetotcc.domain.Categoria;
import com.gustavoj.projetotcc.domain.Cidade;
import com.gustavoj.projetotcc.domain.Estado;
import com.gustavoj.projetotcc.domain.Produto;
import com.gustavoj.projetotcc.repositories.CategoriaRepository;
import com.gustavoj.projetotcc.repositories.CidadeRepository;
import com.gustavoj.projetotcc.repositories.EstadoRepository;
import com.gustavoj.projetotcc.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetotccApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepositories;
	
	@Autowired
	private ProdutoRepository produtoRepositories;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired 
	private EstadoRepository estadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetotccApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(1,"Informatica");
		Categoria cat2 = new Categoria(2,"Comida");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().add(cat1);
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlancia",est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		categoriaRepositories.saveAll(Arrays.asList(cat1,cat2));
		produtoRepositories.saveAll(Arrays.asList(prod1,prod2,prod3));

	}
	
	
	
	

}
