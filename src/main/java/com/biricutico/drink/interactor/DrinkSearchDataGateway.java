package com.biricutico.drink.interactor;

import com.biricutico.drink.entity.Drink;

import java.util.List;

public interface DrinkSearchDataGateway {
    List<Drink> searchByName(String name);

    List<Drink> listAll();
}
