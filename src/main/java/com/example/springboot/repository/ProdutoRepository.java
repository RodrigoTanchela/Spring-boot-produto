package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Produto;



public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
