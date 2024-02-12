package com.biricutico.drink.controller;

import com.biricutico.drink.database.DrinkDataMapper;
import com.biricutico.drink.database.DrinkRepository;
import com.biricutico.drink.interactor.DrinkSearchInputPort;
import com.biricutico.drink.interactor.DrinkSearchRequest;
import com.biricutico.drink.interactor.DrinkSearchResponse;
import com.biricutico.drink.interactor.DrinkSearchUseCase;
import com.biricutico.drink.presentation.DrinkSearchPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrinkSearchController {
    private DrinkSearchInputPort inputPort;

    @Autowired
    private DrinkRepository drinkRepository;

    @GetMapping("/drinks")
    public DrinkSearchResponse searchDrinks(@RequestBody DrinkSearchRequest request) {
        /* Inicialização de componentes - Seria ideal realizar isso no Main (BiricuticoApplication) futuramente */
        if (inputPort == null) {
            inputPort = new DrinkSearchUseCase(new DrinkSearchPresenter(), new DrinkDataMapper(drinkRepository));
        }

        return inputPort.searchDrinks(request);
    }
}