package com.example.springboot.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.vo.v1.ProdutoVO;
import com.example.springboot.service.ProdutoServices;
import com.example.springboot.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/produto/v1")
@Tag(name = "Produtos", description = "Endpoints de produtos") 
public class ProdutoController {
	
	@Autowired
	private ProdutoServices service;
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://rodrigo.com.br"})
	@GetMapping(value = "/{id}", 
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Busca um produto", description = "Busca um produto pelo id", 
	tags = {"Produto"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ProdutoVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public ProdutoVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Busca todos os produtos", description = "Traz uma lista com todos os produtos", 
	tags = {"Produtos"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ProdutoVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public List<ProdutoVO> findAll() {
		return service.findAll();
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://rodrigo.com.br"})
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "cria um produto", description = "Cria um produto", 
	tags = {"Produto"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ProdutoVO.class))
			),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public ProdutoVO create(@RequestBody ProdutoVO produto) {
		return service.create(produto);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	@Operation(summary = "Atualiza dados de um produto", description = "Atualiza os dados de um produto", 
	tags = {"Produto"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ProdutoVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
			)
	public ProdutoVO update(@RequestBody ProdutoVO produto) {
		return service.update(produto);
	}
	
	@PatchMapping(value = "/{id}",
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
		@Operation(summary = "Update value produto", description = "Update value produto",
			tags = {"Produto"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ProdutoVO.class))
				),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
		)
		public ProdutoVO updateValue(@PathVariable(value = "id") Long id, @RequestBody BigDecimal value) {
			return service.updateValue(id, value);
		}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um produto", description = "Deleta um produto pelo id", 
	tags = {"Produto"},
	responses = {
			@ApiResponse (description = "Sucess", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ProdutoVO.class))
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
