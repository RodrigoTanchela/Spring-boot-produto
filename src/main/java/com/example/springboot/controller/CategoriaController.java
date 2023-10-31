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

import com.example.springboot.model.vo.v1.CategoriaVO;
import com.example.springboot.service.CategoriaServices;

@RestController
@RequestMapping("/api/categoria/v1")
public class CategoriaController {
	
	@Autowired
	private CategoriaServices service;
	
	@GetMapping("/{id}")
	public CategoriaVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<CategoriaVO> findAll() {
		return service.findAll();
	}
	
	@PostMapping
	public CategoriaVO create(@RequestBody CategoriaVO categoria) {
		return service.create(categoria);
	}
	
	@PutMapping
	public CategoriaVO update(@RequestBody CategoriaVO categoria) {
		return service.update(categoria);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}
}
