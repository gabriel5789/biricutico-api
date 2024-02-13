package com.biricutico.drink.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    List<Drink> findByNomeContainingIgnoreCase(String nome);
}
