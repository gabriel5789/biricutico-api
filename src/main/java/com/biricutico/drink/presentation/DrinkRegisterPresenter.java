package com.biricutico.drink.presentation;

import com.biricutico.base.BaseError;
import com.biricutico.base.ResponseHeader;
import com.biricutico.drink.entity.Drink;
import com.biricutico.drink.entity.Utensilio;
import com.biricutico.drink.interactor.DrinkRegisterOutputPort;
import com.biricutico.drink.interactor.DrinkRegisterResponse;

import java.util.LinkedHashMap;
import java.util.Map;

public class DrinkRegisterPresenter implements DrinkRegisterOutputPort {
    @Override
    public DrinkRegisterResponse presentResponse(Drink drink) {
        DrinkRegisterResponse response = new DrinkRegisterResponse();

        response.setHeader(new ResponseHeader(
                200, null
        ));

        Map<String, String> tags = new LinkedHashMap<>();
        drink.getTags().forEach(tag -> tags.put(tag.getKey(), tag.getValue()));

        response.setData(new DrinkRegisterResponse.DrinkDTO(
                drink.getId(),
                drink.getNome(),
                drink.getFoto(),
                drink.getDescricao(),
                drink.getInstrucoesPreparo(),
                drink.getUtensilios().stream().map(
                        Utensilio::getNome
                ).toList(),
                drink.getIngredientes().stream().map(
                        ingrediente -> new DrinkRegisterResponse.IngredienteDTO(
                                ingrediente.getNome(),
                                ingrediente.getUnidadeMedida().name(),
                                ingrediente.getQuantidade()
                        )
                ).toList(),
                tags,
                drink.getTeorAlcoolico(),
                drink.getTaca()
        ));

        return response;
    }

    @Override
    public DrinkRegisterResponse presentDataGatewayPersistenceError(Exception e) {
        DrinkRegisterResponse response = new DrinkRegisterResponse();

        response.setHeader(new ResponseHeader(
                500,
                new BaseError(
                        0,
                        "Persistence Operation Error"
                )
        ));

        return response;
    }

    @Override
    public DrinkRegisterResponse presentInvalidDomainObjectError(Exception e) {
        DrinkRegisterResponse response = new DrinkRegisterResponse();

        response.setHeader(
                new ResponseHeader(400, new BaseError(0, "Invalid Input Data")));

        return response;
    }
}
