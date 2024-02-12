package com.biricutico.drink.presentation;

import com.biricutico.base.BaseResponse;
import com.biricutico.drink.entity.Drink;
import com.biricutico.drink.interactor.DrinkSearchOutputPort;
import com.biricutico.drink.interactor.DrinkSearchResponse;

import java.util.List;

public class DrinkSearchPresenter implements DrinkSearchOutputPort {
    @Override
    public DrinkSearchResponse presentResults(List<Drink> drinkList) {
        /* Montar DrinkResponse */
        DrinkSearchResponse response = new DrinkSearchResponse();

        response.setHeader(
                new BaseResponse.ResponseHeader(
                        200,
                        null
                )
        );

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
                new BaseResponse.ResponseHeader(400, new BaseResponse.BaseError(0, "Missing Search Parameters"))
        );
        return response;
    }
}
