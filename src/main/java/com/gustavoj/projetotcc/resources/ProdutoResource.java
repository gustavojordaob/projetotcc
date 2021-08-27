package com.gustavoj.projetotcc.resources;

import com.gustavoj.projetotcc.domain.Categoria;
import com.gustavoj.projetotcc.domain.Pedido;
import com.gustavoj.projetotcc.domain.Produto;
import com.gustavoj.projetotcc.dto.CategoriaDTO;
import com.gustavoj.projetotcc.dto.ProdutoDTO;
import com.gustavoj.projetotcc.resources.utils.URL;
import com.gustavoj.projetotcc.services.PedidoService;
import com.gustavoj.projetotcc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id){
		Produto obj = produtoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findpage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderyBy", defaultValue="nome") String orderBy,
			@RequestParam(value="diection", defaultValue="ASC") String direction) {
		String nomeDecoded =  URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> produtos = produtoService.search(nomeDecoded, ids,page,linesPerPage, orderBy,direction);
		Page<ProdutoDTO> produtoDTOS = produtos
				.map(prod -> new ProdutoDTO(prod));
		return ResponseEntity.ok().body(produtoDTOS);
	}
	
}
