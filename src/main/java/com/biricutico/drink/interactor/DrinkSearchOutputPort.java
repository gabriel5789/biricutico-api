package com.biricutico.drink.interactor;

import com.biricutico.drink.entity.Drink;

import java.util.List;

public interface DrinkSearchOutputPort {
    DrinkSearchResponse presentResults(List<Drink> drinkSearchResponse);
    DrinkSearchResponse presentMissingSearchParametersError();
}
