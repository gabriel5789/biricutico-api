package com.biricutico.drink.interactor;

import com.biricutico.drink.entity.Drink;

public interface DrinkRegisterDataGateway {
    public Drink saveDrink(Drink drink) throws DataGatewayPersistenceError;
}
