package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.springboot.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categoria/v1")
@Tag(name = "Categorias", description = "Endpoints de categorias") 
public class CategoriaController {
	
	@Autowired
	private CategoriaServices service;
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://rodrigo.com.br"})
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Busca uma categoria", description = "Busca um categoria pelo id", 
	tags = {"Categoria"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = CategoriaVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public CategoriaVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Busca uma categoria", description = "Busca um categoria pelo id", 
	tags = {"Categorias"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = CategoriaVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public List<CategoriaVO> findAll() {
		return service.findAll();
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://rodrigo.com.br"})
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Cria uma categoria", description = "Cria um categoria pelo id", 
	tags = {"Categoria"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = CategoriaVO.class))
			),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public CategoriaVO create(@RequestBody CategoriaVO categoria) {
		return service.create(categoria);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "atualiza uma categoria", description = "atualiza uma categoria pelo id", 
	tags = {"Categoria"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = CategoriaVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public CategoriaVO update(@RequestBody CategoriaVO categoria) {
		return service.update(categoria);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "deleta uma categoria", description = "Deleta uma categoria pelo id", 
	tags = {"Categoria"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = CategoriaVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}
}
