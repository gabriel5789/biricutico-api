package com.biricutico.drink.presentation;

import com.biricutico.base.BaseError;
import com.biricutico.base.ResponseHeader;
import com.biricutico.drink.entity.Drink;
import com.biricutico.drink.interactor.DrinkSearchOutputPort;
import com.biricutico.drink.interactor.DrinkSearchResponse;

import java.util.LinkedList;
import java.util.List;

public class DrinkSearchPresenter implements DrinkSearchOutputPort {
    @Override
    public DrinkSearchResponse presentResults(List<Drink> drinkList) {
        DrinkSearchResponse response = new DrinkSearchResponse();

        response.setHeader(new ResponseHeader(200, null));

        if (drinkList == null || drinkList.isEmpty()) {
            response.setData(new LinkedList<>());
            return response;
        }

        List<DrinkSearchResponse.DrinkDTO> drinkDTOList = drinkList
                .stream()
                .map(drink -> new DrinkSearchResponse.DrinkDTO(
                        drink.getId(),
                        drink.getNome(),
                        drink.getFoto(),
                        drink.getDescricao()))
                .toList();

        response.setData(drinkDTOList);
        return response;
    }

    @Override
    public DrinkSearchResponse presentMissingSearchParametersError() {
        DrinkSearchResponse response = new DrinkSearchResponse();
        response.setHeader(
                new ResponseHeader(400, new BaseError(0, "Missing Search Parameters"))
        );
        return response;
    }

    @Override
    public DrinkSearchResponse presentDataAccessError() {
        DrinkSearchResponse response = new DrinkSearchResponse();
        response.setHeader(
                new ResponseHeader(500, new BaseError(0, "Data Access Error"))
        );
        return response;
    }
}
