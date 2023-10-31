package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
