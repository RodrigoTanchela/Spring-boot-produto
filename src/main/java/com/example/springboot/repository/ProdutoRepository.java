package com.example.springboot.repository;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springboot.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	

	@Modifying
	@Query("UPDATE Produto SET value = value WHERE id = id")
	void updateValue(@Param("id") Long id, BigDecimal value);
	
//	Usuario findByUsername(@Param("username") String username);
//	@Query("UPDATE Usuario u SET u.enabled = false WHERE u.id = :id")
	
}
