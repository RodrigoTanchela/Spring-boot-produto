package com.example.springboot.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.controller.CategoriaController;
import com.example.springboot.controller.ProdutoController;
import com.example.springboot.exceptions.ResourceNotFoundException;
import com.example.springboot.mapper.DozerMapper;
import com.example.springboot.model.Categoria;
import com.example.springboot.model.vo.v1.CategoriaVO;
import com.example.springboot.repository.CategoriaRepository;

@Service
public class CategoriaServices {
private Logger logger = Logger.getLogger(CategoriaServices.class.getName());
	
	@Autowired
	private CategoriaRepository repository;
	
	public List<CategoriaVO> findAll() {
		logger.info("Finding all people");
		var categorias =  DozerMapper.parseListObject(repository.findAll(), CategoriaVO.class);
		categorias.stream().forEach(p-> p.add(linkTo(methodOn(CategoriaController.class).findById(p.getKey())).withSelfRel()));
		return categorias;
	}

	public CategoriaVO findById(Long id) {
		logger.info("Finding one categoria!");
		
		var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records Found for this Id"));
		var vo = DozerMapper.parseObject(entity, CategoriaVO.class);
		vo.add(linkTo(methodOn(ProdutoController.class).findAll()).withSelfRel());
		return vo;
	}
	
	public CategoriaVO create(CategoriaVO categoria) {
		logger.info("Creating one categoria!");
		var entity = DozerMapper.parseObject(categoria, Categoria.class);
		var vo = DozerMapper.parseObject(repository.save(entity), CategoriaVO.class);
		vo.add(linkTo(methodOn(ProdutoController.class).findById(categoria.getKey())).withSelfRel());
		return vo;
	}
	
	public CategoriaVO update(CategoriaVO categoria) {
		logger.info("Alterando uma categoria");
		var entity = repository.findById(categoria.getKey()).orElseThrow(() -> new ResourceNotFoundException("Not found"));
		entity.setNome(categoria.getNome());
		var vo = DozerMapper.parseObject(repository.save(entity), CategoriaVO.class);
		vo.add(linkTo(methodOn(ProdutoController.class).findById(categoria.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deletando um categoria");
		Categoria entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
		repository.delete(entity);
	}
}
