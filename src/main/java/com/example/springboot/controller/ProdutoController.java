package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.vo.v1.ProdutoVO;
import com.example.springboot.service.ProdutoServices;

@RestController
@RequestMapping("/api/produto/v1")
public class ProdutoController {
	
	@Autowired
	private ProdutoServices service;
	
	@GetMapping("/{id}")
	public ProdutoVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<ProdutoVO> findAll() {
		return service.findAll();
	}
	
	@PostMapping
	public ProdutoVO create(@RequestBody ProdutoVO produto) {
		return service.create(produto);
	}
	
	@PutMapping
	public ProdutoVO update(@RequestBody ProdutoVO produto) {
		return service.update(produto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}
}
