package com.biricutico.drink.interactor;

import com.biricutico.drink.entity.Drink;

public interface DrinkRegisterOutputPort {
    DrinkRegisterResponse presentResponse(Drink drink);

    DrinkRegisterResponse presentDataGatewayPersistenceError(Exception e);

    DrinkRegisterResponse presentInvalidDomainObjectError(Exception e);
}
